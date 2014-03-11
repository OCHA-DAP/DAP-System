package org.ocha.hdx.exporter.helper;

import java.util.HashMap;
import java.util.Map;

public class ReportRow {

	private final String indicatorCode;
	private final String indicatorName;
	private final String sourceCode;
	private final String unit;
	private int minYear = Integer.MAX_VALUE;
	private int maxYear = Integer.MIN_VALUE;

	/**
	 * Metadata from AdditionalData
	 */
	private final String datasetSummary;

	private final Map<Integer, String> valuesForYears;

	public ReportRow(final String indicatorCode, final String indicatorName, final String sourceCode, final String unit, final String datasetSummary) {
		super();
		this.indicatorCode = indicatorCode;
		this.indicatorName = indicatorName;
		this.sourceCode = sourceCode;
		this.unit = unit;
		this.datasetSummary = datasetSummary;
		valuesForYears = new HashMap<Integer, String>();
	}

	public String getIndicatorCode() {
		return indicatorCode;
	}

	public String getIndicatorName() {
		return indicatorName;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public String getUnit() {
		return unit;
	}

	public String getDatasetSummary() {
		return datasetSummary;
	}

	public void addValue(final Integer year, final String value) {
		valuesForYears.put(year, value);
		if(minYear > year) {
			minYear = year;
		}
		if(maxYear < year) {
			maxYear = year;
		}
	}

	public String getValue(final Integer year) {
		return valuesForYears.get(year);
	}

	public Integer getIntegerValue(final Integer year) {
		if(null == valuesForYears.get(year)) {
			return null;
		}
		return Integer.valueOf(valuesForYears.get(year));
	}

	public Double getDoubleValue(final Integer year) {
		if(null == valuesForYears.get(year)) {
			return null;
		}
		return Double.valueOf(valuesForYears.get(year));
	}

	public int getMinYear() {
		return minYear;
	}

	public int getMaxYear() {
		return maxYear;
	}
}
