/**
 *
 */
package org.ocha.hdx.importer.coltransformer;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.ocha.hdx.config.ConfigurationConstants;
import org.ocha.hdx.config.ConfigurationConstants.MultiplicationValues;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorImportConfig;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author alexandru-m-g
 * 
 */
public class ScraperColumnsTransformer extends AbstractColumnsTransformer {

	private static Logger logger = LoggerFactory.getLogger(ScraperColumnsTransformer.class);

	public static final String NO_DATE = "none";

	public static final LocalDate DUMMY_DATE = new LocalDate(2511, 5, 1);

	public static final String EXPECTED_TIME_PATTERN = "YYYY(/P(1|2|3|5|10)Y)?";
	public static final int PERIODICITY_GROUP = 2;

	public static final String EXPECTED_START_TIME_PATTERN = "YYYY(\\((-|\\+)?([0-9]{1,2})\\))?(-([0-9]{2})-([0-9]{2}))?";
	public static final int SIGN_GROUP = 2;
	public static final int OFFSET_GROUP = 3;
	public static final int DAY_GROUP = 5;
	public static final int MONTH_GROUP = 6;

	public static final String ACTUAL_DATE_PATTERN = "([0-9]{4})(/P(1|2|3|5|10)Y)?";
	public static final int YEAR_GROUP = 1;
	public static final int PERIOD_YEAR_GROUP = 3;
	public static final Pattern ACTUAL_TIME_PATTERN_COMPILED = Pattern.compile(ACTUAL_DATE_PATTERN);

	private final Map<String, String> sourcesMap;

	private Periodicity periodicity;
	private int yearLength;
	private DateTimeFormatter dateTimeFormat;

	private int offset;
	private int day;
	private int month;

	private double multiplyBy;

	private final String valueType;

	private TYPE_OF_DATE typeOfDate;

	public ScraperColumnsTransformer(final Map<String, AbstractConfigEntry> generalConfig, final Map<String, AbstractConfigEntry> indConfig, final Map<String, String> sourcesMap) {
		super(generalConfig, indConfig);
		this.sourcesMap = sourcesMap;

		final AbstractConfigEntry valueTypeEntry = indConfig.get(ConfigurationConstants.IndicatorConfiguration.INDICATOR_VALUE_TYPE.getLabel());
		if (valueTypeEntry != null) {
			this.valueType = valueTypeEntry.getEntryValue();
		} else {
			this.valueType = null;
		}

		this.discoverPeriodicity(indConfig);

		if (this.typeOfDate != null) {
			this.discoverExpectedStartTime(indConfig);
		}

		this.discoverMultiplication(indConfig);

	}

	/**
	 * @param indConfig
	 */
	private void discoverExpectedStartTime(final Map<String, AbstractConfigEntry> indConfig) {
		this.offset = 0;
		this.day = 1;
		this.month = 1;
		final AbstractConfigEntry expectedStartTimeFormatEntry = indConfig.get(ConfigurationConstants.IndicatorConfiguration.EXPECTED_START_TIME_FORMAT.getLabel());
		final Pattern expectedStartTimeFormatPattern = Pattern.compile(EXPECTED_START_TIME_PATTERN);

		final Matcher matcher = expectedStartTimeFormatPattern.matcher(expectedStartTimeFormatEntry.getEntryValue());
		if (matcher.matches()) {
			final String sign = matcher.group(SIGN_GROUP);
			final String offset = matcher.group(OFFSET_GROUP);
			final String month = matcher.group(MONTH_GROUP);
			final String day = matcher.group(DAY_GROUP);
			try {
				if (offset != null) {
					this.offset = Integer.parseInt(offset);
					if ("-".equals(sign)) {
						this.offset = -1 * this.offset;
					}
				}
				if (day != null) {
					this.day = Integer.parseInt(day);
				}
				if (month != null) {
					this.month = Integer.parseInt(month);
				}
			} catch (final NumberFormatException e) {
				logger.warn("Something wrong with the following expected start time format: " + expectedStartTimeFormatEntry.getEntryValue());
			}

		}
	}

	/**
	 * @param indConfig
	 */
	private void discoverPeriodicity(final Map<String, AbstractConfigEntry> indConfig) {
		final AbstractConfigEntry expectedTimeFormatEntry = indConfig.get(ConfigurationConstants.IndicatorConfiguration.EXPECTED_TIME_FORMAT.getLabel());
		if (expectedTimeFormatEntry == null || NO_DATE.equals(expectedTimeFormatEntry.getEntryValue())) {
			this.dateTimeFormat = null;
			this.periodicity = Periodicity.NONE;
			this.typeOfDate = null;
		} else {
			final Pattern expectedTimeFormatPattern = Pattern.compile(EXPECTED_TIME_PATTERN);

			final Matcher matcher = expectedTimeFormatPattern.matcher(expectedTimeFormatEntry.getEntryValue());
			if (matcher.matches()) {

				final String period = matcher.group(PERIODICITY_GROUP);
				if (period == null) {
					this.yearLength = 1;
				} else {
					try {
						this.yearLength = Integer.parseInt(period);
					} catch (final NumberFormatException e) {
						throw new IllegalArgumentException("Wrong pattern for expected time: " + expectedTimeFormatEntry.getEntryValue(), e);
					}
				}
				this.periodicity = Periodicity.findPeriodicityByCode(this.yearLength + "Y");

				if (this.periodicity == null) {
					throw new IllegalArgumentException("Specified periodicity doesn't exist in Periodicity enum: " + this.yearLength + "Y");
				}

				this.typeOfDate = TYPE_OF_DATE.YEAR;

			} else {
				this.dateTimeFormat = DateTimeFormat.forPattern(expectedTimeFormatEntry.getEntryValue());
				this.periodicity = Periodicity.NONE;
				this.typeOfDate = TYPE_OF_DATE.FULL_DATE;
			}
		}
	}

	private void discoverMultiplication(final Map<String, AbstractConfigEntry> indConfig) {
		this.multiplyBy = 1;
		final AbstractConfigEntry multiplicationEntry = indConfig.get(ConfigurationConstants.IndicatorConfiguration.MULTIPLICATION.getLabel());
		if (multiplicationEntry != null) {
			final MultiplicationValues value = MultiplicationValues.findByLabel(multiplicationEntry.getEntryValue());
			if (value != null) {
				this.multiplyBy = value.getFactor();
			}
		}
	}

	@Override
	public Date getStartDate(final String[] line) {
		LocalDate localDate;
		final String actualDateStr = line[3];
		if (TYPE_OF_DATE.YEAR.equals(this.typeOfDate)) {
			final Matcher matcher = ACTUAL_TIME_PATTERN_COMPILED.matcher(actualDateStr);
			if (matcher.matches()) {
				final int year = Integer.parseInt(matcher.group(YEAR_GROUP));
				final String readDataPeriodicityStr = matcher.group(PERIOD_YEAR_GROUP);
				this.compareReadDataPeriodicity(readDataPeriodicityStr, line);

				localDate = new LocalDate(year + this.offset, this.month, this.day);
			} else {
				throw new IllegalArgumentException(String.format("Couldn't read string '%s' with the pattern '%s' for indicator entry: %s", actualDateStr, ACTUAL_DATE_PATTERN, Arrays.toString(line)));
			}
		} else if (TYPE_OF_DATE.FULL_DATE.equals(this.typeOfDate)) {
			try {
				localDate = LocalDate.parse(actualDateStr, this.dateTimeFormat);
			} catch (final IllegalArgumentException e) {
				logger.warn(String.format("Couldn't parse date '%s' with format '%s' for indicator entry: %s ", actualDateStr, this.dateTimeFormat.toString(), Arrays.toString(line)));
				throw e;
			}
		} else {
			return DUMMY_DATE.toDate();
		}
		return localDate.toDate();
	}

	private void compareReadDataPeriodicity(final String readDataPeriodicityStr, final String[] line) {
		if (readDataPeriodicityStr != null) {
			try {
				final Integer yearPeriod = Integer.parseInt(readDataPeriodicityStr);
				final Periodicity tempPeriodicity = Periodicity.findPeriodicityByCode(yearPeriod + "Y");
				if (!tempPeriodicity.equals(this.periodicity)) {
					logger.warn(String.format("Periodicity different in read values compared to metadata. Read: %s, metadata: %s. For line: %s", tempPeriodicity, this.periodicity,
							Arrays.toString(line)));
				}
			} catch (final NumberFormatException e) {
				logger.error(e.getMessage());
			}
		}

	}

	@Override
	public Date getEndDate(final String[] line) {
		LocalDate localDate;
		final String actualDateStr = line[3];
		if (TYPE_OF_DATE.YEAR.equals(this.typeOfDate)) {
			final Matcher matcher = ACTUAL_TIME_PATTERN_COMPILED.matcher(actualDateStr);
			if (matcher.matches()) {
				final int year = Integer.parseInt(matcher.group(YEAR_GROUP));
				localDate = new LocalDate(year + this.offset + this.yearLength, this.month, this.day);
			} else {
				throw new IllegalArgumentException(String.format("Could read string '%s' with the pattern '%s'", actualDateStr, ACTUAL_DATE_PATTERN));
			}
		} else if (TYPE_OF_DATE.FULL_DATE.equals(this.typeOfDate)) {
			localDate = LocalDate.parse(actualDateStr, this.dateTimeFormat);
		} else {
			return null;
			// throw new IllegalArgumentException("The type of data is not set for this transformer");
		}
		return localDate.toDate();
	}

	@Override
	public Periodicity getPeriodicity(final String[] line) {
		return this.periodicity;
	}

	// @Override
	// public String getInitialValue(final String[] line) {
	// return line[4];
	// }

	@Override
	public IndicatorImportConfig getIndicatorImportConfig(final String[] line) {
		// FIXME this should not be mocked to get only the initialValue, but more
		return new IndicatorImportConfig(line[4], ValidationStatus.SUCCESS);
	}

	@Override
	public IndicatorValue getValue(final String[] line) {
		final String value = line[4];
		if (ValueType.NUMBER.getLabel().equals(this.valueType) || this.valueType == null) {
			final Double valueAsDouble = Double.parseDouble(value);
			// FIXME we should deal about units later, here for
			// population we must X1000
			if (this.multiplyBy > 1) {
				return new IndicatorValue(valueAsDouble * this.multiplyBy);
			} else {
				return new IndicatorValue(valueAsDouble);
			}
		} else {
			return new IndicatorValue(value);
		}
	}

	@Override
	public String getSourceCode(final String[] line) {
		if (this.sourcesMap.containsKey(line[0])) {
			return this.sourcesMap.get(line[0]);
		} else {
			return line[0];
		}
	}

	@Override
	public String getEntityCode(final String[] line) {
		return line[1];
	}

	@Override
	public String getEntityTypeCode(final String[] line) {
		return "country";
	}

	@Override
	public String getIndicatorTypeCode(final String[] line) {
		return line[2];
	}

	private enum TYPE_OF_DATE {
		YEAR, FULL_DATE
	}

}
