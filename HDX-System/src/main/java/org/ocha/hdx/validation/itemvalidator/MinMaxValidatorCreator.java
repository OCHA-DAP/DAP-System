/**
 *
 */
package org.ocha.hdx.validation.itemvalidator;

import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.ocha.hdx.config.ConfigurationConstants;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorImportConfig;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.validation.Response;
import org.ocha.hdx.validation.exception.WrongParametersForValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author alexandru-m-g
 *
 */
@Component
public class MinMaxValidatorCreator implements IValidatorCreator {

	private static Logger logger = LoggerFactory.getLogger(MinMaxValidator.class);

	public static final String NAME = "Min/Max Validator";

	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("dd/MM/yyyy");

	@Override
	public String getValidatorName() {
		return NAME;
	}

	@Override
	public IValidator create(final Map<String, AbstractConfigEntry> generalConfig, final Map<String, AbstractConfigEntry> indConfig) {
		return new MinMaxValidator(generalConfig, indConfig);
	}

	public class MinMaxValidator implements IValidator {

		private final AbstractConfigEntry minValueEntry;
		private final AbstractConfigEntry maxValueEntry;

		boolean minValIsUsable=false;
		boolean maxValIsUsable=false;

		private IComparableHelper<Double> numericHelper;
		private IComparableHelper<ReadablePartial> dateHelper;
		private IComparableHelper<ReadablePartial> datetimeHelper;


		public MinMaxValidator(final Map<String, AbstractConfigEntry> generalConfig, final Map<String, AbstractConfigEntry> indConfig) {

			this.minValueEntry = indConfig.get(ConfigurationConstants.IndicatorConfiguration.MIN_VALUE.getLabel());
			this.maxValueEntry = indConfig.get(ConfigurationConstants.IndicatorConfiguration.MAX_VALUE.getLabel());

			if ( this.minValueEntry != null && this.minValueEntry.getEntryValue() != null
					&& !this.minValueEntry.getEntryValue().trim().isEmpty() ) {
				this.minValIsUsable	= true;
			}

			if ( this.maxValueEntry != null && this.maxValueEntry.getEntryValue() != null
					&& !this.maxValueEntry.getEntryValue().trim().isEmpty() ) {
				this.maxValIsUsable	= true;
			}

			this.numericHelper	= new IComparableHelper<Double>() {
				@Override
				public Comparable<Double> transform(final String string) {
					try{
						return Double.parseDouble(string);
					} catch (final NumberFormatException e) {
						throw new WrongParametersForValidationException(e);
					}
				}

				@Override
				public Comparable<Double> getValueFromIndicator(final Indicator indicator) {
					return indicator.getValue().getNumberValue();
				}
			};

			this.dateHelper	= new IComparableHelper<ReadablePartial>() {

				@Override
				public Comparable<ReadablePartial> transform(final String string) {
					try{
						return LocalDate.parse(string, DATE_FORMATTER);
					}
					catch(final IllegalArgumentException e) {
						throw new WrongParametersForValidationException(e);
					}
				}

				@Override
				public Comparable<ReadablePartial> getValueFromIndicator(final Indicator indicator) {
					try{
						return new LocalDate(indicator.getValue().getDateValue());
					}
					catch( final IllegalArgumentException e) {
						throw new WrongParametersForValidationException(e);
					}
				}
			};

			this.datetimeHelper	= new IComparableHelper<ReadablePartial>() {

				@Override
				public Comparable<ReadablePartial> transform(final String string) {
					try{
						return LocalDateTime.parse(string, DATE_TIME_FORMATTER);
					}
					catch(final IllegalArgumentException e) {
						throw new WrongParametersForValidationException(e);
					}
				}

				@Override
				public Comparable<ReadablePartial> getValueFromIndicator(final Indicator indicator) {
					try{
						return new LocalDateTime(indicator.getValue().getDateValue());
					}
					catch( final IllegalArgumentException e) {
						throw new WrongParametersForValidationException(e);
					}
				}
			};
		}

		@Override
		public String getValidatorName() {
			return NAME;
		}

		/**
		 *
		 * @see IValidator#validate(Indicator)
		 *
		 */
		@Override
		public Response validate(final Indicator indicator) throws WrongParametersForValidationException {

			final Response response = new Response();

			final IndicatorValue value = indicator.getValue();
			if (value == null) {
				response.setDescription("The value is null for the following indicator: " + indicator);
				response.setStatus(ValidationStatus.ERROR);
			} else if (value.getStringValue() != null || value.getTextValue() != null) {
				response.setDescription("The text value cannot be compared to min/max values for indicator: " + indicator);
				response.setStatus(ValidationStatus.ERROR);

			} else if (value.getDatetimeValue() != null) {

				this.check(this.minValueEntry, this.maxValueEntry, response, indicator, this.datetimeHelper);

			} else if (value.getDateValue() != null) {
				this.check(this.minValueEntry, this.maxValueEntry, response, indicator, this.dateHelper);

			} else if (value.getNumberValue() != null) {
				this.check(this.minValueEntry, this.maxValueEntry, response, indicator, this.numericHelper);
			}

			return response;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void check(final AbstractConfigEntry minValueEntry, final AbstractConfigEntry maxValueEntry, final Response response,
				final Indicator indicator, final IComparableHelper<? extends Object> comparableHelper)
				throws WrongParametersForValidationException {

			/*
			 * If we don't need to check for one of the boundaries then we assume the test
			 * passes for that specific boundary
			 */
			boolean minCheckPass	= !this.minValIsUsable ;
			boolean maxCheckPass	= !this.maxValIsUsable ;

			final Comparable indValue = comparableHelper.getValueFromIndicator(indicator);
			if ( this.minValIsUsable ) {
				final Comparable min = comparableHelper.transform(minValueEntry.getEntryValue());
				if (min.compareTo(indValue) <= 0 ) {
					minCheckPass	= true;
				}
			}

			if ( this.maxValIsUsable ) {
				final Comparable max = comparableHelper.transform(maxValueEntry.getEntryValue());
				if (max.compareTo(indValue) >= 0 ) {
					maxCheckPass	= true;
				}

			}

			if ( minCheckPass && maxCheckPass ) {
				this.populateSuccessResponse(response);
			} else {
				this.populateErrorResponse(minValueEntry, maxValueEntry, response, indicator);
			}

		}

		private void populateErrorResponse(final AbstractConfigEntry minValueEntry, final AbstractConfigEntry maxValueEntry, final Response response, final Indicator indicator) {
			response.setDescription(String.format("Value is not between %s and %s for %s", minValueEntry.getEntryValue(), maxValueEntry.getEntryValue(), indicator.toString()));
			response.setStatus(ValidationStatus.ERROR);
		}

		private void populateSuccessResponse(final Response response) {
			response.setDescription("Success");
			response.setStatus(ValidationStatus.SUCCESS);
		}

		/**
		 * @param indicator
		 * @param ret
		 * @param response
		 * @return
		 */
		@Override
		public void populateImportConfig(final IndicatorImportConfig importConfig, final Response response) {
			/* Populate the indicator's importConfig details with validation result */
			importConfig.setValidationStatus(response.getStatus() );
			if ( !ValidationStatus.SUCCESS.equals(response.getStatus()) ) {
				if ( importConfig.getValidationMessage() == null ) {
					importConfig.setValidationMessage( response.getDescription() );
				} else {
					importConfig.setValidationMessage( importConfig.getValidationMessage() + " -- "  + response.getDescription());
				}
			}

			importConfig.setUpperBoundary( this.discoverNumericConfig(this.maxValueEntry) );
			importConfig.setLowerBoundary( this.discoverNumericConfig(this.minValueEntry) );

		}
		/**
		 *
		 */
		private Double discoverNumericConfig(final AbstractConfigEntry entry) {

			try {
				if ( entry != null && entry.getEntryValue() != null ) {
					return Double.parseDouble(entry.getEntryValue());
				}
			} catch (final NumberFormatException e) {
				logger.error(
						String.format("Couldn't parse double %s value %s",
								entry.getEntryKey(), entry.getEntryValue())
					);
			}
			return null;
		}

		@Override
		public boolean useable() {
			return this.minValIsUsable || this.maxValIsUsable;

		}
	}

	private interface IComparableHelper<T extends Object> {
		Comparable<T> transform(String string);
		Comparable<T> getValueFromIndicator(Indicator indicator);
	}

}
