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
import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.validation.Response;
import org.ocha.hdx.validation.exception.WrongParametersForValidationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author alexandru-m-g
 *
 */
@Component
public class MinMaxValidator implements IValidator {

	public static final String NAME = "Min/Max Validator";

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("dd/MM/yyyy");

	@Override
	public String getValidatorName() {
		return NAME;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ocha.hdx.validation.itemvalidator.IValidator#validate(org.ocha.hdx.importer.PreparedIndicator, java.util.Map, java.util.Map)
	 */
	@Override
	public Response validate(final PreparedIndicator preparedIndicator, final Map<String, AbstractConfigEntry> generalConfig,
			final Map<String, AbstractConfigEntry> indConfig) throws WrongParametersForValidationException {

		final AbstractConfigEntry minValueEntry = indConfig.get(ConfigurationConstants.MIN_VALUE);
		final AbstractConfigEntry maxValueEntry = indConfig.get(ConfigurationConstants.MAX_VALUE);

		if (minValueEntry == null || maxValueEntry == null) {
			throw new WrongParametersForValidationException("Min value and Max value cannot be null");
		}

		final Response response = new Response();

		final IndicatorValue value = preparedIndicator.getValue();
		if (value == null) {
			response.setDescription("The value is null for the following indicator: " + preparedIndicator);
			response.setStatus(ValidationStatus.ERROR);
		} else if (value.getStringValue() != null || value.getTextValue() != null) {
			response.setDescription("The text value cannot be compared to min/max values for indicator: " + preparedIndicator);
			response.setStatus(ValidationStatus.ERROR);

		} else if (value.getDatetimeValue() != null) {

			this.checkDateTime(minValueEntry, maxValueEntry, response, preparedIndicator);

		} else if (value.getDateValue() != null) {
			this.checkDate(minValueEntry, maxValueEntry, response, preparedIndicator);

		} else if (value.getNumberValue() != null) {
			this.checkNumber(minValueEntry, maxValueEntry, response, preparedIndicator);
		}

		return response;
	}

	private void checkNumber(final AbstractConfigEntry minValueEntry, final AbstractConfigEntry maxValueEntry, final Response response,
			final PreparedIndicator preparedIndicator) throws WrongParametersForValidationException {
		try {
			final Double doubleValue = preparedIndicator.getValue().getNumberValue();
			final Double min = Double.parseDouble(minValueEntry.getEntryValue());
			final Double max = Double.parseDouble(maxValueEntry.getEntryValue());
			if (min < doubleValue && doubleValue < max) {
				response.setDescription("Success");
				response.setStatus(ValidationStatus.SUCCESS);
			} else {
				this.populateErrorResponse(minValueEntry, maxValueEntry, response, preparedIndicator);
			}
		} catch (final NumberFormatException e) {
			throw new WrongParametersForValidationException(e);
		}
	}

	private void checkDate(final AbstractConfigEntry minValueEntry, final AbstractConfigEntry maxValueEntry, final Response response,
			final PreparedIndicator preparedIndicator) {
		final LocalDate localDate = new LocalDate(preparedIndicator.getValue().getDateValue());

		final LocalDate minDate = LocalDate.parse(minValueEntry.getEntryValue(), DATE_FORMATTER);
		final LocalDate maxDate = LocalDate.parse(maxValueEntry.getEntryValue(), DATE_FORMATTER);
		if (minDate.isBefore(localDate) && maxDate.isAfter(localDate)) {
			response.setDescription("Success");
			response.setStatus(ValidationStatus.SUCCESS);
		}
		else {
			this.populateErrorResponse(minValueEntry, maxValueEntry, response, preparedIndicator);
		}
	}

	private void checkDateTime(final AbstractConfigEntry minValueEntry, final AbstractConfigEntry maxValueEntry, final Response response,
			final PreparedIndicator preparedIndicator) {
		final LocalDateTime localDateTime = new LocalDateTime(preparedIndicator.getValue().getDatetimeValue());

		final LocalDateTime minDateTime = LocalDateTime.parse(minValueEntry.getEntryValue(), DATE_TIME_FORMATTER);
		final LocalDateTime maxDateTime = LocalDateTime.parse(maxValueEntry.getEntryValue(), DATE_TIME_FORMATTER);
		if (minDateTime.isBefore(localDateTime) && maxDateTime.isAfter(localDateTime)) {
			response.setDescription("Success");
			response.setStatus(ValidationStatus.SUCCESS);
		} else {
			this.populateErrorResponse(minValueEntry, maxValueEntry, response, preparedIndicator);
		}
	}

	private void populateErrorResponse(final AbstractConfigEntry minValueEntry, final AbstractConfigEntry maxValueEntry, final Response response,
			final PreparedIndicator preparedIndicator) {
		response.setDescription(String.format("Value is not between %s and %s for %s", minValueEntry.getEntryValue(), maxValueEntry.getEntryValue(),
				preparedIndicator.toString()));
		response.setStatus(ValidationStatus.ERROR);
	}

}
