package org.ocha.hdx.exporter.helper;

import java.util.HashMap;
import java.util.Map;

public class ReportRow {

	private final String indicatorCode;
	private final String indicatorName;
	private final String datasetId;
	private final String unit;

	private final Map<Integer, String> valuesForYears;

	public ReportRow(final String indicatorCode, final String indicatorName, final String datasetId, final String unit) {
		super();
		this.indicatorCode = indicatorCode;
		this.indicatorName = indicatorName;
		this.datasetId = datasetId;
		this.unit = unit;
		valuesForYears = new HashMap<Integer, String>();
	}

	public String getIndicatorCode() {
		return indicatorCode;
	}

	public String getIndicatorName() {
		return indicatorName;
	}

	public String getDatasetId() {
		return datasetId;
	}

	public String getUnit() {
		return unit;
	}

	public void addValue(final Integer year, final String value) {
		valuesForYears.put(year, value);
	}

	public String getValue(final Integer year) {
		return valuesForYears.get(year);
	}

}
