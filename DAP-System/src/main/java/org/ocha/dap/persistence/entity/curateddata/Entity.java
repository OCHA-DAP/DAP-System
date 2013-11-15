package org.ocha.dap.persistence.entity.curateddata;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * 
 * @author Samuel Eustachi
 * @author David Megginson
 * 
 *         a table listing specific entities, with a reference to each entity's
 *         type in the {@link EntityType} table.
 * 
 */
@javax.persistence.Entity
@Table(name = "entity")
public class Entity {

	@Id
	private long id;

	@ManyToOne
	@JoinColumn(name = "entity_type_id")
	@ForeignKey(name = "fk_entity_to_type")
	private EntityType type;

	@Column(name = "code", nullable = false, updatable = false)
	private String code;
	@Column(name = "name", nullable = false, updatable = false)
	private String name;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public EntityType getType() {
		return type;
	}

	public void setType(final EntityType type) {
		this.type = type;
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
