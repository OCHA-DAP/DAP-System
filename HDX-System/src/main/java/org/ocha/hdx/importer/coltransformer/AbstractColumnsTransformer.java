/**
 *
 */
package org.ocha.hdx.importer.coltransformer;

import java.util.Date;
import java.util.Map;

import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorImportConfig;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;

/**
 * @author alexandru-m-g
 *
 */
public abstract class AbstractColumnsTransformer {

	protected final Map<String, AbstractConfigEntry> generalConfig;
	protected final Map<String, AbstractConfigEntry> indConfig;

	protected boolean disabled;

	public AbstractColumnsTransformer(final Map<String, AbstractConfigEntry> generalConfig, final Map<String, AbstractConfigEntry> indConfig) {
		super();
		this.generalConfig = generalConfig;
		this.indConfig = indConfig;
		this.disabled = false;
	}

	public boolean isDisabled() {
		return this.disabled;
	}

	public abstract Date getStartDate(String[] line);

	public abstract Date getEndDate(String[] line);

	public abstract Periodicity getPeriodicity(String[] line);

	public abstract IndicatorValue getValue(String[] line);

	public abstract String getSourceCode(String[] line);

	public abstract String getEntityCode(String[] line);

	public abstract String getEntityTypeCode(String[] line);

	public abstract String getIndicatorTypeCode(String[] line);

	public abstract IndicatorImportConfig getIndicatorImportConfig(String[] line);

	public abstract String getInitialValue(String[] line);

}
