/**
 *
 */
package org.ocha.hdx.validation.itemvalidator;

import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorImportConfig;
import org.ocha.hdx.validation.Response;

/**
 * @author alexandru-m-g
 *
 */
public interface IValidator {

	String getValidatorName();

	/**
	 *
	 * @param preparedIndicator - the indicator that needs to be validated
	 * @return {@link ValidationStatus#ERROR} if the validation fails and the indicator should not be allowed to pass to the curated set
	 * {@link ValidationStatus#WARNING} if the validation fails but the indicator should still be allowed to pass to the curated set
	 * {@link ValidationStatus#SUCCESS} if the validation succeeds
	 *
	 */

	Response validate(Indicator indicator);

	void populateImportConfig(final IndicatorImportConfig importConfig, final Response response);
}
