package org.ocha.dap.importer;

import java.util.Date;

import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;

public class PreparedIndicator {

	private String sourceCode;
	private String entityCode;
	private String entityTypeCode;
	private String indicatorTypeCode;
	private Date start;
	private Date end;
	private Periodicity periodicity;
	private boolean numeric;
	private String value;
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

	public boolean isNumeric() {
		return numeric;
	}

	public void setNumeric(final boolean numeric) {
		this.numeric = numeric;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
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
				+ ", end=" + end + ", periodicity=" + periodicity + ", numeric=" + numeric + ", value=" + value + ", initialValue=" + initialValue + "]";
	}
}
