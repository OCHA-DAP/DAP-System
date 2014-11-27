package org.ocha.hdx.model.api2util;

import java.util.Date;

public class IntermediaryIndicatorValue {
	private double value;

	private String indicatorTypeCode;

	private String indicatorTypeName;

	private Long indicatorTypeNameId;

	private String unitCode;

	private String unitName;

	private Long unitNameId;

	private String locationCode;

	private String locationName;

	private Long locationNameId;

	private String sourceCode;

	private String sourceName;

	private Long sourceNameId;

	private Date startDate;

	public IntermediaryIndicatorValue(final double value, final String indicatorTypeCode, final String indicatorTypeName, final Long indicatorTypeNameId,
			final String unitCode, final String unitName, final Long unitNameId,
			final String locationCode, final String locationName, final Long locationNameId,
			final String sourceCode, final String sourceName, final Long sourceNameId, final Date startDate) {
		super();
		this.value = value;
		this.indicatorTypeCode = indicatorTypeCode;
		this.indicatorTypeName = indicatorTypeName;
		this.indicatorTypeNameId = indicatorTypeNameId;
		this.unitCode = unitCode;
		this.unitName = unitName;
		this.unitNameId = unitNameId;
		this.locationCode = locationCode;
		this.locationName = locationName;
		this.locationNameId = locationNameId;
		this.sourceCode = sourceCode;
		this.sourceName = sourceName;
		this.sourceNameId = sourceNameId;
		this.startDate = startDate;
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

	public Long getIndicatorTypeNameId() {
		return this.indicatorTypeNameId;
	}

	public void setIndicatorTypeNameId(final Long indicatorTypeNameId) {
		this.indicatorTypeNameId = indicatorTypeNameId;
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

	public Long getLocationNameId() {
		return this.locationNameId;
	}

	public void setLocationNameId(final Long locationNameId) {
		this.locationNameId = locationNameId;
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

	public Long getSourceNameId() {
		return this.sourceNameId;
	}

	public void setSourceNameId(final Long sourceNameId) {
		this.sourceNameId = sourceNameId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public Long getUnitNameId() {
		return this.unitNameId;
	}

	public void setUnitNameId(final Long unitNameId) {
		this.unitNameId = unitNameId;
	}

	public String getUnitCode() {
		return this.unitCode;
	}

	public void setUnitCode(final String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}



}
