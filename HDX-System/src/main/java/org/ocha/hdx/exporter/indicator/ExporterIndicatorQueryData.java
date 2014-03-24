package org.ocha.hdx.exporter.indicator;

import org.ocha.hdx.exporter.QueryData;
import org.ocha.hdx.exporter.helper.ReadmeHelper;

/**
 * Data query for an indicator.
 * 
 * @author bmichiels
 */
public class ExporterIndicatorQueryData extends QueryData {

	/**
	 * The indicator type code (e.g. PVF020).
	 */
	private String indicatorTypeCode;
	
	/**
	 * The source code (e.g. faostat3).
	 */
	private String sourceCode;
	
	/**
	 * The year from which the report will start (e.g. 1998).
	 * If set to 0, the report will start with the earliest data available.
	 */
	private Long fromYear;

	/**
	 * The year to which the report will go (e.g. 2010).
	 * If set to 0, the report go to the latest data available.
	 */
	private Long toYear;
	
	/**
	 * The language into which the report will be generated.
	 * TODO Not implemented yet. All texts are set in the default value.
	 */
	private String language;
	
	/**
	 * The helper to build the readme part of the report.
	 */
	private ReadmeHelper readmeHelper;

	public Long getFromYear() {
		return fromYear;
	}

	public void setFromYear(final Long fromYear) {
		this.fromYear = fromYear;
	}

	public Long getToYear() {
		return toYear;
	}

	public void setToYear(final Long toYear) {
		this.toYear = toYear;
	}

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

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(final String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public ReadmeHelper getReadmeHelper() {
		return readmeHelper;
	}

	public void setReadmeHelper(final ReadmeHelper readmeHelper) {
		this.readmeHelper = readmeHelper;
	}
}
