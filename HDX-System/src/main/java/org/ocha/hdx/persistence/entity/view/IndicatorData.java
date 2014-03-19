package org.ocha.hdx.persistence.entity.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A view to give the data for an indicator type.
	 i.id as "indicator_id"
	,EXTRACT(YEAR FROM i.end_time) as "indicator_year"
	,i.number_value as "indicator_value"
	,it.code as "indicator_type_code"
	,s.code as "source_code"
	,e.code as "country_code"

 * @author Benoît Michiels
 */
@Entity
@Table(name = "hdx_view_report_indicator_data")
public class IndicatorData {

	@Id
	@Column(name = "indicator_id")
	private Long indicatorId;

	@Column(name = "indicator_year")
	private Long indicatorYear;

	@Column(name = "indicator_value")
	private Double indicatorValue;

	@Column(name = "indicator_type_code")
	private String indicatorTypeCode;

	@Column(name = "source_code")
	private String sourceCode;

	@Column(name = "country_code")
	private String countryCode;

	public Long getIndicatorId() {
		return indicatorId;
	}

	public void setIndicatorId(final Long indicatorId) {
		this.indicatorId = indicatorId;
	}

	public Long getIndicatorYear() {
		return indicatorYear;
	}

	public void setIndicatorYear(final Long indicatorYear) {
		this.indicatorYear = indicatorYear;
	}

	public Double getIndicatorValue() {
		return indicatorValue;
	}

	public void setIndicatorValue(final Double indicatorValue) {
		this.indicatorValue = indicatorValue;
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}
}
