package org.ocha.hdx.exporter.indicator;

import org.ocha.hdx.exporter.QueryData;

/**
 * Data query for an indicator metadata.
 * 
 * @author bmichiels
 */
public class ExporterIndicatorMetadataQueryData extends QueryData {

	/**
	 * The indicator type code (e.g. PVF020).
	 */
	private String indicatorTypeCode;

	/**
	 * The language into which the report will be generated. TODO Not implemented yet. All texts are set in the default value.
	 */
	private String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(final String language) {
		this.language = language;
	}

	public String getIndicatorTypeCode() {
		return indicatorTypeCode;
	}

	public void setIndicatorTypeCode(final String indicatorTypeCode) {
		this.indicatorTypeCode = indicatorTypeCode;
	}
}
