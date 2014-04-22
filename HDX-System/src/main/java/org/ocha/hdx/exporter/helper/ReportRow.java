package org.ocha.hdx.exporter.helper;

import java.util.HashMap;
import java.util.Map;

import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata.MetadataName;

/**
 * A mapping of data coming from the database to a structure more easy to use for the report generation.
 * 
 * @author seustachi
 * 
 */
public class ReportRow {

	private final String indicatorTypeCode;
	private final String indicatorName;
	private final String sourceCode;
	private final String unit;
	private int minYear = Integer.MAX_VALUE;
	private int maxYear = Integer.MIN_VALUE;

	/**
	 * Metadata from DataSerieMetadata
	 */
	private final Map<MetadataName, String> metadata;

	/**
	 * Values for years.
	 */
	private final Map<Integer, String> valuesForYears;

	public ReportRow(final String indicatorTypeCode, final String indicatorName, final String sourceCode, final String unit) {
		super();
		this.indicatorTypeCode = indicatorTypeCode;
		this.indicatorName = indicatorName;
		this.sourceCode = sourceCode;
		this.unit = unit;
		metadata = new HashMap<DataSerieMetadata.MetadataName, String>();
		valuesForYears = new HashMap<Integer, String>();
	}

	public String getIndicatorTypeCode() {
		return indicatorTypeCode;
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

	public Map<MetadataName, String> getMetadata() {
		return metadata;
	}

	public void addMetadata(final MetadataName key, final String value) {
		metadata.put(key, value);
	}

	public void addValue(final Integer year, final String value) {
		valuesForYears.put(year, value);
		if (minYear > year) {
			minYear = year;
		}
		if (maxYear < year) {
			maxYear = year;
		}
	}

	public String getValue(final Integer year) {
		return valuesForYears.get(year);
	}

	public Integer getIntegerValue(final Integer year) {
		if (null == valuesForYears.get(year)) {
			return null;
		}
		return Integer.valueOf(valuesForYears.get(year));
	}

	public Double getDoubleValue(final Integer year) {
		if (null == valuesForYears.get(year)) {
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

	@Override
	public String toString() {
		return "ReportRow [indicatorTypeCode=" + indicatorTypeCode + ", indicatorName=" + indicatorName + ", sourceCode=" + sourceCode + ", unit=" + unit + ", minYear=" + minYear + ", maxYear="
				+ maxYear + ", metadata=" + metadata + ", valuesForYears=" + valuesForYears + "]";
	}
}
