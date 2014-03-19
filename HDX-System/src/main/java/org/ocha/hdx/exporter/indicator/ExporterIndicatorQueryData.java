package org.ocha.hdx.exporter.indicator;

import org.ocha.hdx.exporter.QueryData;

/**
 * Data query for an indicator.
 * 
 * @author bmichiels
 */
public class ExporterIndicatorQueryData extends QueryData {

	private String indicatorTypeCode;
	private String sourceCode;
	private Long fromYear;
	private Long toYear;
	private String language;

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
}
