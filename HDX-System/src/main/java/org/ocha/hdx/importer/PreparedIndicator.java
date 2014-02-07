package org.ocha.hdx.importer;

import java.util.Date;

import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;

public class PreparedIndicator {

	private String sourceCode;
	private String entityCode;
	private String entityTypeCode;
	private String indicatorTypeCode;
	private Date start;
	private Date end;
	private Periodicity periodicity;
	private IndicatorValue value;
	private String initialValue;

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(final String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(final String entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityTypeCode() {
		return entityTypeCode;
	}

	public void setEntityTypeCode(final String entityTypeCode) {
		this.entityTypeCode = entityTypeCode;
	}

	public String getIndicatorTypeCode() {
		return indicatorTypeCode;
	}

	public void setIndicatorTypeCode(final String indicatorTypeCode) {
		this.indicatorTypeCode = indicatorTypeCode;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(final Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(final Date end) {
		this.end = end;
	}

	public Periodicity getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(final Periodicity periodicity) {
		this.periodicity = periodicity;
	}

	public IndicatorValue getValue() {
		return value;
	}

	public void setValue(final IndicatorValue value) {
		this.value = value;
	}

	public String getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(final String initialValue) {
		this.initialValue = initialValue;
	}

	@Override
	public String toString() {
		return "PreparedIndicator [sourceCode=" + sourceCode + ", entityCode=" + entityCode + ", entityTypeCode=" + entityTypeCode + ", indicatorTypeCode=" + indicatorTypeCode + ", start=" + start
				+ ", end=" + end + ", periodicity=" + periodicity + ", value=" + value + ", initialValue=" + initialValue + "]";
	}
}
