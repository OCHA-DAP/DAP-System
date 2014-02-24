/**
 *
 */
package org.ocha.hdx.validation.prevalidator;

import java.util.Map;

import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.validation.Response;
import org.ocha.hdx.validation.exception.WrongParametersForValidationException;

/**
 * @author alexandru-m-g
 *
 */
public interface IPreValidator {

	/**
	 * 
	 * @param line - the line that should be interpreted as an indicator
	 * @param generalConfig - general configuration 
	 * @return {@link ValidationStatus#ERROR} if the validation fails and the indicator should not be allowed to pass to the curated set
	 * {@link ValidationStatus#WARNING} if the validation fails but the indicator should still be allowed to pass to the curated set 
	 * {@link ValidationStatus#SUCCESS} if the validation succeeds  
	 * 
	 * @throws WrongParametersForValidationException in case the needed validator's configuration is not found
	 */
	Response validate(String[] line, Map<String, AbstractConfigEntry> generalConfig) throws WrongParametersForValidationException;

	String getPreValidatorName();
}
