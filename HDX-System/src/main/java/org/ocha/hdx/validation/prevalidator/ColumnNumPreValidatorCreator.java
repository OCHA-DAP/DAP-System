/**
 *
 */
package org.ocha.hdx.validation.prevalidator;

import java.util.Arrays;
import java.util.Map;

import org.ocha.hdx.config.ConfigurationConstants;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.validation.Response;
import org.ocha.hdx.validation.exception.WrongParametersForValidationException;
import org.springframework.stereotype.Component;

/**
 * @author alexandru-m-g Simple pre-validator testing the number of columns on each line
 */
@Component
public class ColumnNumPreValidatorCreator implements IPreValidatorCreator {

	public static final String NAME = "Number of columns validator";

	@Override
	public String getPreValidatorName() {
		return NAME;
	}

	@Override
	public IPreValidator create(final Map<String, AbstractConfigEntry> generalConfig) throws WrongParametersForValidationException {
		return new ColumnNumPreValidator(generalConfig);
	}

	public class ColumnNumPreValidator implements IPreValidator {

		private int numOfAllowedCols	= -1;

		public ColumnNumPreValidator(final Map<String, AbstractConfigEntry> generalConfig) {
			final AbstractConfigEntry numOfColumnsAllowedEntry = generalConfig.get(ConfigurationConstants.GeneralConfiguration.MIN_NUM_OF_COLUMNS.getLabel());
			if (numOfColumnsAllowedEntry != null) {
				try {
					this.numOfAllowedCols = Integer.parseInt(numOfColumnsAllowedEntry.getEntryValue());
				} catch (final NumberFormatException e) {
					throw new WrongParametersForValidationException(e);
				}
			}

		}

		@Override
		public String getPreValidatorName() {
			return NAME;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see org.ocha.hdx.validation.prevalidator.IPreValidator#validate(java.lang.String[], java.util.Map)
		 */
		@Override
		public Response validate(final String[] line) {

			final Response response = new Response();

			if (line == null) {
				response.setStatus(ValidationStatus.ERROR);
				response.setDescription("Encountered empty line");
			} else if (line.length < this.numOfAllowedCols) {
				response.setStatus(ValidationStatus.ERROR);
				response.setDescription(String.format("Line %s has %d columns instead of %d", Arrays.toString(line), line.length, this.numOfAllowedCols));
			} else {
				response.setDescription("Success");
				response.setStatus(ValidationStatus.SUCCESS);
			}
			return response;
		}

		@Override
		public boolean useable() {
			return this.numOfAllowedCols > 0;
		}

	}

}
