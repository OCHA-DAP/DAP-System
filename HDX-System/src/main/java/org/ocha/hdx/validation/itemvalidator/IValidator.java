/**
 *
 */
package org.ocha.hdx.validation.itemvalidator;

import java.util.Map;

import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.validation.Response;
import org.ocha.hdx.validation.exception.WrongParametersForValidationException;

/**
 * @author alexandru-m-g
 *
 */
public interface IValidator {

	String getValidatorName();

	/**
	 * 
	 * @param preparedIndicator - the indicator that needs to be validated
	 * @param generalConfig - general configuration 
	 * @param indConfig - configurations specific for the indicator's type and source
	 * @return {@link ValidationStatus#ERROR} if the validation fails and the indicator should not be allowed to pass to the curated set
	 * {@link ValidationStatus#WARNING} if the validation fails but the indicator should still be allowed to pass to the curated set 
	 * {@link ValidationStatus#SUCCESS} if the validation succeeds  
	 * 
	 * @throws WrongParametersForValidationException in case the needed validator's configuration is not found
	 */
	Response validate(PreparedIndicator preparedIndicator, Map<String,AbstractConfigEntry> generalConfig, Map<String,AbstractConfigEntry> indConfig) throws WrongParametersForValidationException;
}
