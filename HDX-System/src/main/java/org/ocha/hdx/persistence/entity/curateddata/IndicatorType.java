package org.ocha.hdx.persistence.entity.curateddata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.ocha.hdx.persistence.entity.i18n.Text;

/**
 *
 * @author Samuel Eustachi
 * @author David Megginson
 *
 *         a table listing the types of {@link Indicator} supported in the curated data repository (e.g. mortality rate), using the CHD codes as the primary keys.
 *
 */
@Entity
@Table(name = "indicator_type")
@SequenceGenerator(name = "indicator_type_seq", sequenceName = "indicator_type_seq")
public class IndicatorType {

	public enum ValueType {
		TEXT("Text"), STRING("String"), NUMBER("Number"), DATE("Date"), DATETIME("Datetime");

		private final String label;

		private ValueType(final String label) {
			this.label = label;
		}

		public String getLabel() {
			return this.label;
		}

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "indicator_type_seq")
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "code", unique = true, nullable = false, updatable = false)
	private String code;

	@ManyToOne
	@JoinColumn(name = "text_id")
	@ForeignKey(name = "fk_indicator_type_to_name_text")
	private Text name;

	@ManyToOne
	@JoinColumn(name = "unit_id", nullable = true, updatable = true)
	@ForeignKey(name = "fk_indicator_type_to_unit")
	private Unit unit;

	@Column(name = "value_type", nullable = true, updatable = true)
	@Enumerated(EnumType.STRING)
	private ValueType valueType;

	public long getId() {
		return this.id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public Text getName() {
		return this.name;
	}

	public void setName(final Text name) {
		this.name = name;
	}

	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(final Unit unit) {
		this.unit = unit;
	}

	public String getDisplayableTitle() {
		return this.name.getDefaultValue() + " in " + this.unit.getName().getDefaultValue();
	}

	public ValueType getValueType() {
		return this.valueType;
	}

	public void setValueType(final ValueType valueType) {
		this.valueType = valueType;
	}

}
