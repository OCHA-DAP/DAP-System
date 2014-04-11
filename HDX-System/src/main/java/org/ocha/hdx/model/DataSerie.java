package org.ocha.hdx.model;

public class DataSerie {

	private final String indicatorCode;
	private final String sourceCode;

	public DataSerie(final String indicatorCode, final String sourceCode) {
		super();
		this.indicatorCode = indicatorCode;
		this.sourceCode = sourceCode;
	}

	public String getIndicatorCode() {
		return indicatorCode;
	}

	public String getSourceCode() {
		return sourceCode;
	}

}
