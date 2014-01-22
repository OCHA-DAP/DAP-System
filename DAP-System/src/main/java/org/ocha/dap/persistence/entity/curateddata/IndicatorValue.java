package org.ocha.dap.persistence.entity.curateddata;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.dap.persistence.entity.i18n.Text;

@Embeddable
public class IndicatorValue {

	@ManyToOne
	@JoinColumn(name = "text_id")
	@ForeignKey(name = "fk_indicator_value_to_text")
	private final Text textValue;

	@Column(name = "string_value", nullable = true, updatable = false)
	private final String stringValue;

	@Column(name = "number_value", nullable = true, updatable = false)
	private final Double numberValue;

	@Column(name = "date_value", columnDefinition = "timestamp", nullable = true, updatable = false)
	private final Date dateValue;

	@Column(name = "datetime_value", columnDefinition = "timestamp", nullable = true, updatable = false)
	private final Date datetimeValue;

	/**
	 * Should not be used, only needed
	 */
	private IndicatorValue() {
		super();
		this.textValue = null;
		this.stringValue = null;
		this.numberValue = null;
		this.dateValue = null;
		this.datetimeValue = null;
	}

	public IndicatorValue(final Text textValue) {
		super();
		this.textValue = textValue;
		this.stringValue = null;
		this.numberValue = null;
		this.dateValue = null;
		this.datetimeValue = null;
	}

	public IndicatorValue(final String stringValue) {
		super();
		this.textValue = null;
		this.stringValue = stringValue;
		this.numberValue = null;
		this.dateValue = null;
		this.datetimeValue = null;
	}

	public IndicatorValue(final Double numberValue) {
		super();
		this.textValue = null;
		this.stringValue = null;
		this.numberValue = numberValue;
		this.dateValue = null;
		this.datetimeValue = null;
	}

	public IndicatorValue(final Date value, final ValueType valueType) {
		super();
		this.textValue = null;
		this.stringValue = null;
		this.numberValue = null;

		switch (valueType) {
		case DATE:
			this.dateValue = value;
			this.datetimeValue = null;
			break;

		case DATETIME:
			this.dateValue = null;
			this.datetimeValue = value;
			break;

		default:
			throw new IllegalArgumentException("");
		}
	}

	public Text getTextValue() {
		return textValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public Double getNumberValue() {
		return numberValue;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public Date getDatetimeValue() {
		return datetimeValue;
	}

	@Override
	public String toString() {
		if (textValue != null) {
			return textValue.getDefaultValue();
		} else if (stringValue != null) {
			return stringValue;
		} else if (numberValue != null) {
			return numberValue.toString();
		} else if (dateValue != null) {
			final DateTimeFormatter fmt = ISODateTimeFormat.date();
			return fmt.print(dateValue.getTime());
		} else {
			final DateTimeFormatter fmt = ISODateTimeFormat.date();
			return fmt.print(datetimeValue.getTime());
		}
	}
}