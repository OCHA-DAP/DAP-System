package org.ocha.hdx.persistence.entity.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hdx_view_indicator_max_date")
public class IndicatorMaxDate {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "values")
	private double value;

	@Column(name = "indicator_type_codes")
	private String indicatorTypeCode;

	@Column(name = "indicator_type_name")
	private String indicatorTypeName;

	@Column(name = "location_code")
	private String locationCode;

	@Column(name = "location_name")
	private String locationName;

	@Column(name = "source_code")
	private String sourceCode;

	@Column(name = "source_name")
	private String sourceName;

	@Column(name = "start_time")
	private String time;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

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
