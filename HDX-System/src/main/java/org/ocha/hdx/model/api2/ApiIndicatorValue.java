package org.ocha.hdx.model.api2;

public class ApiIndicatorValue {

	private double value;

	private String indicatorTypeCode;

	private String indicatorTypeName;

	private String locationCode;

	private String locationName;

	private String sourceCode;

	private String sourceName;

	private String time;


	public ApiIndicatorValue(final double value, final String indicatorTypeCode, final String indicatorTypeName, final String locationCode, final String locationName, final String sourceCode,
			final String sourceName, final String time) {
		super();
		this.value = value;
		this.indicatorTypeCode = indicatorTypeCode;
		this.indicatorTypeName = indicatorTypeName;
		this.locationCode = locationCode;
		this.locationName = locationName;
		this.sourceCode = sourceCode;
		this.sourceName = sourceName;
		this.time = time;
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(final double value) {
		this.value = value;
	}

	public String getIndicatorTypeCode() {
		return this.indicatorTypeCode;
	}

	public void setIndicatorTypeCode(final String indicatorTypeCode) {
		this.indicatorTypeCode = indicatorTypeCode;
	}

	public String getIndicatorTypeName() {
		return this.indicatorTypeName;
	}

	public void setIndicatorTypeName(final String indicatorTypeName) {
		this.indicatorTypeName = indicatorTypeName;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(final String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	public String getSourceCode() {
		return this.sourceCode;
	}

	public void setSourceCode(final String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceName() {
		return this.sourceName;
	}

	public void setSourceName(final String sourceName) {
		this.sourceName = sourceName;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(final String time) {
		this.time = time;
	}

}
