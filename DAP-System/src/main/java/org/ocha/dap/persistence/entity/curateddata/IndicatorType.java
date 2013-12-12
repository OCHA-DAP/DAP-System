package org.ocha.dap.persistence.entity.curateddata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Samuel Eustachi
 * @author David Megginson
 * 
 *         a table listing the types of {@link Indicator} supported in the
 *         curated data repository (e.g. mortality rate), using the CHD codes as
 *         the primary keys.
 * 
 */
@Entity
@Table(name = "indicator_type")
@SequenceGenerator(name = "indicator_type_seq", sequenceName = "indicator_type_seq")
public class IndicatorType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "indicator_type_seq")
	@Column(name = "id", nullable = false)
	private long id;
	@Column(name = "code", nullable = false, updatable = false)
	private String code;
	@Column(name = "name", nullable = false, updatable = false)
	private String name;

	/**
	 * this is a simple text field, just giving a hint about what the unit should be for this type of indicator
	 * might be modelized more strictly later 
	 */
	@Column(name = "unit", nullable = true, updatable = true)
	private String unit;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(final String unit) {
		this.unit = unit;
	}

	public String getDisplayableTitle() {
		return name + " in " + unit;

	}

}
