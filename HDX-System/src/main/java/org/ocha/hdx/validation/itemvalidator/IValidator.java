/**
 *
 */
package org.ocha.hdx.validation.itemvalidator;

import java.util.Map;

import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.validation.Response;

/**
 * @author alexandru-m-g
 *
 */
public interface IValidator {

	String getValidatorName();

	Response validate(PreparedIndicator preparedIndicator, Map<String,AbstractConfigEntry> generalConfig, Map<String,AbstractConfigEntry> indConfig);
}
