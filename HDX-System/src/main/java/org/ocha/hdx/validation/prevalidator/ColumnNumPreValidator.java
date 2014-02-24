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
 * @author alexandru-m-g
 * Simple pre-validator testing the number of columns on each line
 */
@Component
public class ColumnNumPreValidator implements IPreValidator {

	public static final String NAME	= "Number of columns validator";

	/* (non-Javadoc)
	 * @see org.ocha.hdx.validation.prevalidator.IPreValidator#validate(java.lang.String[], java.util.Map)
	 */
	@Override
	public Response validate(final String[] line, final Map<String, AbstractConfigEntry> generalConfig) throws WrongParametersForValidationException {
		final AbstractConfigEntry numOfColumnsAllowedEntry	= generalConfig.get(ConfigurationConstants.NUM_OF_ALLOWED_COLUMNS);
		if ( numOfColumnsAllowedEntry == null ) {
			throw new WrongParametersForValidationException("NUM_OF_ALLOWED_COLUMNS config value cannot be null");
		}
		else {
			try {
				final int numOfAllowedCols	= Integer.parseInt(numOfColumnsAllowedEntry.getEntryValue());
				final Response response 	= new Response();

				if ( line == null ) {
					response.setStatus(ValidationStatus.ERROR);
					response.setDescription("Encountered empty line");
				}
				else if ( line.length != numOfAllowedCols ) {
					response.setStatus(ValidationStatus.ERROR);
					response.setDescription( String.format("Line %s has %d columns instead of %d", Arrays.toString(line), line.length, numOfAllowedCols) );
				}
				else {
					response.setDescription("Success");
					response.setStatus(ValidationStatus.SUCCESS);
				}
				return response;
			}
			catch(final NumberFormatException e) {
				throw new WrongParametersForValidationException(e);
			}

		}
	}

	/* (non-Javadoc)
	 * @see org.ocha.hdx.validation.prevalidator.IPreValidator#getPreValidatorName()
	 */
	@Override
	public String getPreValidatorName() {
		return NAME;
	}

}
