/**
 *
 */
package org.ocha.hdx.importer.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocha.hdx.importer.coltransformer.AbstractColumnsTransformer;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.validation.itemvalidator.IValidator;

/**
 * @author alexandru-m-g
 *
 */
public class IndicatorTypeInformationHolder {

	/**
	 * Configurations for this indicator type
	 */
	private Map<String, AbstractConfigEntry> indicatorEntries	= new HashMap<String, AbstractConfigEntry>();

	/**
	 * Cache of validators per ind type and source. The key is the indicator type code + source code.
	 */
	private List<IValidator> validators		= null;

	private boolean errorDisplayed	= false;

	private AbstractColumnsTransformer colTransformers;


	public Map<String, AbstractConfigEntry> getIndicatorEntries() {
		return this.indicatorEntries;
	}

	public void setIndicatorEntries(final Map<String, AbstractConfigEntry> indicatorEntries) {
		this.indicatorEntries = indicatorEntries;
	}

	public List<IValidator> getValidators() {
		return this.validators;
	}

	public void setValidators(final List<IValidator> validators) {
		this.validators = validators;
	}

	public AbstractColumnsTransformer getColTransformers() {
		return this.colTransformers;
	}

	public void setColTransformers(final AbstractColumnsTransformer colTransformers) {
		this.colTransformers = colTransformers;
	}

	public boolean isErrorDisplayed() {
		return this.errorDisplayed;
	}

	public void setErrorDisplayed(final boolean errorDisplayed) {
		this.errorDisplayed = errorDisplayed;
	}



}
