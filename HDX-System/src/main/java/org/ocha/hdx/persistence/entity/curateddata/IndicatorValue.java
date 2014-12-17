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

	@Column(name = "string_value", nullable = true)
	private final String stringValue;

	@Column(name = "number_value", nullable = true)
	private final Double numberValue;

	@Column(name = "date_value", nullable = true)
	@Temporal(TemporalType.DATE)
	private final Date dateValue;

	@Column(name = "datetime_value", nullable = true)
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
		return this.textValue;
	}

	public String getStringValue() {
		return this.stringValue;
	}

	public Double getNumberValue() {
		return this.numberValue;
	}

	public Date getDateValue() {
		return this.dateValue;
	}

	public Date getDatetimeValue() {
		return this.datetimeValue;
	}

	@Override
	public String toString() {
		if (this.textValue != null) {
			return this.textValue.getDefaultValue();
		} else if (this.stringValue != null) {
			return this.stringValue;
		} else if (this.numberValue != null) {
			return this.numberValue.toString();
		} else if (this.dateValue != null) {
			final DateTimeFormatter fmt = ISODateTimeFormat.date();
			return fmt.print(this.dateValue.getTime());
		} else {
			final DateTimeFormatter fmt = ISODateTimeFormat.date();
			return fmt.print(this.datetimeValue.getTime());
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.dateValue == null) ? 0 : this.dateValue.hashCode());
		result = prime * result + ((this.datetimeValue == null) ? 0 : this.datetimeValue.hashCode());
		result = prime * result + ((this.numberValue == null) ? 0 : this.numberValue.hashCode());
		result = prime * result + ((this.stringValue == null) ? 0 : this.stringValue.hashCode());
		result = prime * result + ((this.textValue == null) ? 0 : this.textValue.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof IndicatorValue)) {
			return false;
		}
		final IndicatorValue other = (IndicatorValue) obj;
		if (this.dateValue == null) {
			if (other.dateValue != null) {
				return false;
			}
		} else if (!this.dateValue.equals(other.dateValue)) {
			return false;
		}
		if (this.datetimeValue == null) {
			if (other.datetimeValue != null) {
				return false;
			}
		} else if (!this.datetimeValue.equals(other.datetimeValue)) {
			return false;
		}
		if (this.numberValue == null) {
			if (other.numberValue != null) {
				return false;
			}
		} else if (!this.numberValue.equals(other.numberValue)) {
			return false;
		}
		if (this.stringValue == null) {
			if (other.stringValue != null) {
				return false;
			}
		} else if (!this.stringValue.equals(other.stringValue)) {
			return false;
		}
		if (this.textValue == null) {
			if (other.textValue != null) {
				return false;
			}
		} else if (!this.textValue.equals(other.textValue)) {
			return false;
		}
		return true;
	}


}