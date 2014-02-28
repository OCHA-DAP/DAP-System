/**
 *
 */
package org.ocha.hdx.validation.prevalidator;

import java.util.Map;

import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.validation.exception.WrongParametersForValidationException;

/**
 * @author alexandru-m-g
 *
 */
public interface IPreValidatorCreator {
	String getPreValidatorName();

	/**
	 * @param generalConfig - general configuration
	 * @return a new pre-validator
	 * @throws WrongParametersForValidationException in case the needed validator's configuration is not found
	 */
	IPreValidator create(final Map<String, AbstractConfigEntry> generalConfig)  throws WrongParametersForValidationException;
}
