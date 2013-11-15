package org.ocha.dap.persistence.entity.curateddata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Samuel Eustachi
 * @author David Megginson
 * 
 *         a table listing the types of
 *         {@link org.ocha.dap.persistence.entity.curateddata.Entity} supported
 *         in the curated data repository (e.g. countries, crises, etc.).
 * 
 */
@Entity
@Table(name = "entity_type")
public class EntityType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;
	@Column(name = "code", nullable = false, updatable = false, unique=true)
	private String code;
	@Column(name = "name", nullable = false, updatable = false)
	private String name;

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

}
