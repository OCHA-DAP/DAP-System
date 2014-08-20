package org.ocha.hdx.model.api2;

public class ApiValue {

	private double value;

	private String indicatorTypeCode;

	private String indicatorTypeName;

	private String locationCode;

	private String locationName;

	private String sourceCode;

	private String sourceName;

	private String time;

	public double getValue() {
		return value;
	}

	public void setValue(final double value) {
		this.value = value;
	}

	public String getIndicatorTypeCode() {
		return indicatorTypeCode;
	}

	public void setIndicatorTypeCode(final String indicatorTypeCode) {
		this.indicatorTypeCode = indicatorTypeCode;
	}

	public String getIndicatorTypeName() {
		return indicatorTypeName;
	}

	public void setIndicatorTypeName(final String indicatorTypeName) {
		this.indicatorTypeName = indicatorTypeName;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(final String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(final String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(final String sourceName) {
		this.sourceName = sourceName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(final String time) {
		this.time = time;
	}

}
