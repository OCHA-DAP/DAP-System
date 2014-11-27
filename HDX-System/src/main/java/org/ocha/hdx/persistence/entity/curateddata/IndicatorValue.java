package org.ocha.hdx.persistence.entity.curateddata;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.i18n.Text;

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

	@Column(name = "date_value", nullable = true, updatable = false)
	@Temporal(TemporalType.DATE)
	private final Date dateValue;

	@Column(name = "datetime_value", nullable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateValue == null) ? 0 : dateValue.hashCode());
		result = prime * result + ((datetimeValue == null) ? 0 : datetimeValue.hashCode());
		result = prime * result + ((numberValue == null) ? 0 : numberValue.hashCode());
		result = prime * result + ((stringValue == null) ? 0 : stringValue.hashCode());
		result = prime * result + ((textValue == null) ? 0 : textValue.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof IndicatorValue)) {
			return false;
		}
		IndicatorValue other = (IndicatorValue) obj;
		if (dateValue == null) {
			if (other.dateValue != null) {
				return false;
			}
		} else if (!dateValue.equals(other.dateValue)) {
			return false;
		}
		if (datetimeValue == null) {
			if (other.datetimeValue != null) {
				return false;
			}
		} else if (!datetimeValue.equals(other.datetimeValue)) {
			return false;
		}
		if (numberValue == null) {
			if (other.numberValue != null) {
				return false;
			}
		} else if (!numberValue.equals(other.numberValue)) {
			return false;
		}
		if (stringValue == null) {
			if (other.stringValue != null) {
				return false;
			}
		} else if (!stringValue.equals(other.stringValue)) {
			return false;
		}
		if (textValue == null) {
			if (other.textValue != null) {
				return false;
			}
		} else if (!textValue.equals(other.textValue)) {
			return false;
		}
		return true;
	}
	
	
}