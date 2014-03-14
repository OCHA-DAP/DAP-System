/**
 *
 */
package org.ocha.hdx.validation.itemvalidator;

import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.ocha.hdx.config.ConfigurationConstants;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.validation.Response;
import org.ocha.hdx.validation.exception.WrongParametersForValidationException;
import org.springframework.stereotype.Component;

/**
 * @author alexandru-m-g
 * 
 */
@Component
public class MinMaxValidatorCreator implements IValidatorCreator {

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

		public MinMaxValidator(final Map<String, AbstractConfigEntry> generalConfig, final Map<String, AbstractConfigEntry> indConfig) {

			this.minValueEntry = indConfig.get(ConfigurationConstants.IndicatorConfiguration.MIN_VALUE.getLabel());
			this.maxValueEntry = indConfig.get(ConfigurationConstants.IndicatorConfiguration.MAX_VALUE.getLabel());

			if (this.minValueEntry == null || this.maxValueEntry == null) {
				throw new WrongParametersForValidationException("Min value and Max value cannot be null");
			}
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

				this.checkDateTime(this.minValueEntry, this.maxValueEntry, response, indicator);

			} else if (value.getDateValue() != null) {
				this.checkDate(this.minValueEntry, this.maxValueEntry, response, indicator);

			} else if (value.getNumberValue() != null) {
				this.checkNumber(this.minValueEntry, this.maxValueEntry, response, indicator);
			}

			return response;
		}

		private void checkNumber(final AbstractConfigEntry minValueEntry, final AbstractConfigEntry maxValueEntry, final Response response, final Indicator indicator)
				throws WrongParametersForValidationException {
			try {
				final Double doubleValue = indicator.getValue().getNumberValue();
				final Double min = Double.parseDouble(minValueEntry.getEntryValue());
				final Double max = Double.parseDouble(maxValueEntry.getEntryValue());
				if (min <= doubleValue && doubleValue <= max) {
					response.setDescription("Success");
					response.setStatus(ValidationStatus.SUCCESS);
				} else {
					this.populateErrorResponse(minValueEntry, maxValueEntry, response, indicator);
				}
			} catch (final NumberFormatException e) {
				throw new WrongParametersForValidationException(e);
			}
		}

		private void checkDate(final AbstractConfigEntry minValueEntry, final AbstractConfigEntry maxValueEntry, final Response response, final Indicator indicator) {
			final LocalDate localDate = new LocalDate(indicator.getValue().getDateValue());

			final LocalDate minDate = LocalDate.parse(minValueEntry.getEntryValue(), DATE_FORMATTER);
			final LocalDate maxDate = LocalDate.parse(maxValueEntry.getEntryValue(), DATE_FORMATTER);
			if (minDate.compareTo(localDate) <= 0 && maxDate.compareTo(localDate) >= 0) {
				response.setDescription("Success");
				response.setStatus(ValidationStatus.SUCCESS);
			} else {
				this.populateErrorResponse(minValueEntry, maxValueEntry, response, indicator);
			}
		}

		private void checkDateTime(final AbstractConfigEntry minValueEntry, final AbstractConfigEntry maxValueEntry, final Response response, final Indicator indicator) {
			final LocalDateTime localDateTime = new LocalDateTime(indicator.getValue().getDatetimeValue());

			final LocalDateTime minDateTime = LocalDateTime.parse(minValueEntry.getEntryValue(), DATE_TIME_FORMATTER);
			final LocalDateTime maxDateTime = LocalDateTime.parse(maxValueEntry.getEntryValue(), DATE_TIME_FORMATTER);
			if (minDateTime.compareTo(localDateTime) <= 0 && maxDateTime.compareTo(localDateTime) >= 0) {
				response.setDescription("Success");
				response.setStatus(ValidationStatus.SUCCESS);
			} else {
				this.populateErrorResponse(minValueEntry, maxValueEntry, response, indicator);
			}
		}

		private void populateErrorResponse(final AbstractConfigEntry minValueEntry, final AbstractConfigEntry maxValueEntry, final Response response, final Indicator indicator) {
			response.setDescription(String.format("Value is not between %s and %s for %s", minValueEntry.getEntryValue(), maxValueEntry.getEntryValue(), indicator.toString()));
			response.setStatus(ValidationStatus.ERROR);
		}
	}

}
