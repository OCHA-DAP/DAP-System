/**
 *
 */
package org.ocha.hdx.importer.coltransformer;

import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.ocha.hdx.config.ConfigurationConstants;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author alexandru-m-g
 *
 */
public class ScraperColumnsTransformer extends AbstractColumnsTransformer {

	private static Logger logger = LoggerFactory.getLogger(ScraperColumnsTransformer.class);

	public static final String EXPECTED_TIME_PATTERN = "YYYY(/P(1|5|10)Y)?";
	public static final int PERIODICITY_GROUP = 2;

	public static final String EXPECTED_START_TIME_PATTERN = "YYYY(\\((-|\\+)?([0-9]{1,2})\\))?(-([0-9]{2})-([0-9]{2}))?";
	public static final int SIGN_GROUP = 2;
	public static final int OFFSET_GROUP = 3;
	public static final int DAY_GROUP = 5;
	public static final int MONTH_GROUP = 6;

	public static final String ACTUAL_DATE_PATTERN = "([0-9]{4})(/P(1|5|10)Y)?";
	public static final int YEAR_GROUP = 1;
	public static final Pattern ACTUAL_TIME_PATTERN_COMPILED = Pattern.compile(ACTUAL_DATE_PATTERN);

	private final Map<String, String> sourcesMap;

	private Periodicity periodicity;
	private int yearLength;
	private DateTimeFormatter dateTimeFormat;

	private int offset;
	private int day;
	private int month;

	private TYPE_OF_DATE typeOfDate;

	public ScraperColumnsTransformer(final Map<String, AbstractConfigEntry> generalConfig, final Map<String, AbstractConfigEntry> indConfig,
			final Map<String, String> sourcesMap) {
		super(generalConfig, indConfig);
		this.sourcesMap = sourcesMap;

		this.discoverPeriodicity(indConfig);

		this.discoverExpectedStartTime(indConfig);

	}

	/**
	 * @param indConfig
	 */
	private void discoverExpectedStartTime(final Map<String, AbstractConfigEntry> indConfig) {
		this.offset = 0;
		this.day = 1;
		this.month = 1;
		final AbstractConfigEntry expectedStartTimeFormatEntry = indConfig.get(ConfigurationConstants.EXPECTED_START_TIME_FORMAT);
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

		} else {
			logger.info("Configuration for expected start time not set or could not be read. Using defaults !");

		}
	}

	/**
	 * @param indConfig
	 */
	private void discoverPeriodicity(final Map<String, AbstractConfigEntry> indConfig) {
		final AbstractConfigEntry expectedTimeFormatEntry = indConfig.get(ConfigurationConstants.EXPECTED_TIME_FORMAT);
		final Pattern expectedTimeFormatPattern = Pattern.compile(EXPECTED_TIME_PATTERN);

		final Matcher matcher = expectedTimeFormatPattern.matcher(expectedTimeFormatEntry.getEntryValue());
		if (matcher.matches()) {

			final String period = matcher.group(PERIODICITY_GROUP);
			if (period == null || "1".equals(period)) {
				this.periodicity = Periodicity.YEAR;
				this.yearLength = 1;
			} else if ("5".equals(period)) {
				this.periodicity = Periodicity.FIVE_YEARS;
				this.yearLength = 5;
			} else if ("10".equals(period)) {
				this.periodicity = Periodicity.TEN_YEARS;
				this.yearLength = 10;
			} else {
				throw new IllegalArgumentException("Wrong pattern for expected time: " + expectedTimeFormatEntry.getEntryValue());
			}
			this.typeOfDate = TYPE_OF_DATE.YEAR;

		} else {
			this.dateTimeFormat = DateTimeFormat.forPattern(expectedTimeFormatEntry.getEntryValue());
			this.periodicity = Periodicity.NONE;
			this.typeOfDate = TYPE_OF_DATE.FULL_DATE;
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
				localDate = new LocalDate(year + this.offset, this.month, this.day);
			} else {
				throw new IllegalArgumentException(String.format("Could read string '%s' with the pattern '%s'", actualDateStr, ACTUAL_DATE_PATTERN));
			}
		} else if (TYPE_OF_DATE.FULL_DATE.equals(this.typeOfDate)) {
			localDate = LocalDate.parse(actualDateStr, this.dateTimeFormat);
		} else {
			throw new IllegalArgumentException("The type of data is not set for this transformer");
		}
		return localDate.toDate();
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
			throw new IllegalArgumentException("The type of data is not set for this transformer");
		}
		return localDate.toDate();
	}

	@Override
	public Periodicity getPeriodicity(final String[] line) {
		return this.periodicity;
	}

	@Override
	public String getInitialValue(final String[] line) {
		return line[4];
	}

	@Override
	public IndicatorValue getValue(final String[] line) {
		final Double valueAsDouble = Double.parseDouble(line[4]);
		// FIXME we should deal about units later, here for
		// population we must X1000
		if ("PSP010".equals(line[2])) {
			return new IndicatorValue(valueAsDouble * 1000);
		} else {
			return new IndicatorValue(valueAsDouble);
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
