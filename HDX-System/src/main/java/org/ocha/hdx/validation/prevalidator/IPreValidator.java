/**
 *
 */
package org.ocha.hdx.validation.prevalidator;

import java.util.Map;

import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.validation.Response;

/**
 * @author alexandru-m-g
 *
 */
public interface IPreValidator {

	Response validate(String[] line, Map<String, AbstractConfigEntry> generalConfig);

	String getPreValidatorName();
}
