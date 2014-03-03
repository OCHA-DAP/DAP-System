/**
 *
 */
package org.ocha.hdx.validation.itemvalidator;

import java.util.Map;

import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.validation.exception.WrongParametersForValidationException;

/**
 * @author alexandru-m-g
 *
 */
public interface IValidatorCreator {
	String getValidatorName();

	/**
	 * @param generalConfig - general configuration
	 * @param indConfig - configurations specific for the indicator's type and source
	 * @return a new validator
	 * @throws WrongParametersForValidationException in case the needed validator's configuration is not found
	 */
	IValidator create(final Map<String, AbstractConfigEntry> generalConfig, final Map<String, AbstractConfigEntry> indConfig);
}
