package org.ocha.hdx.persistence.entity.curateddata;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;
import org.ocha.hdx.persistence.entity.i18n.Text;

/**
 * 
 * @author Samuel Eustachi
 * @author David Megginson
 * 
 *         a table listing specific entities, with a reference to each entity's type in the {@link EntityType} table.
 * 
 */
@javax.persistence.Entity
@Table(name = "entity", uniqueConstraints = @UniqueConstraint(columnNames = { "code", "entity_type_id" }))
@SequenceGenerator(name = "entity_seq", sequenceName = "entity_seq")
public class Entity implements Comparable<Entity> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_seq")
	@Column(name = "id", nullable = false)
	private long id;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	@ForeignKey(name = "fk_entity_to_parent")
	private Entity parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private Set<Entity> children;

	@ManyToOne
	@JoinColumn(name = "entity_type_id")
	@ForeignKey(name = "fk_entity_to_type")
	private EntityType type;

	@Column(name = "code", nullable = false, updatable = false)
	private String code;

	@ManyToOne
	@JoinColumn(name = "text_id")
	@ForeignKey(name = "fk_entity_to_name_text")
	private Text name;

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

	public Entity getParent() {
		return parent;
	}

	public void setParent(final Entity parent) {
		this.parent = parent;
	}

	public Set<Entity> getChildren() {
		return children;
	}

	public void setChildren(final Set<Entity> children) {
		this.children = children;
	}

	public Text getName() {
		return name;
	}

	public void setName(final Text name) {
		this.name = name;
	}

	@Override
	public int compareTo(final Entity o) {
		return this.code.compareTo(o.code);
	}

}
