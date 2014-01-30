package org.ocha.dap.persistence.entity.curateddata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.ocha.dap.persistence.entity.i18n.Text;

/**
 * 
 * @author Samuel Eustachi
 * @author David Megginson
 * 
 *         a table listing the types of {@link org.ocha.dap.persistence.entity.curateddata.Entity} supported in the curated data repository
 *         (e.g. countries, crises, etc.).
 * 
 */
@Entity
@Table(name = "entity_type")
@SequenceGenerator(name = "entity_type_seq", sequenceName = "entity_type_seq")
public class EntityType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "entity_type_seq")
	@Column(name = "id", nullable = false)
	private long id;
	@Column(name = "code", nullable = false, updatable = false, unique = true)
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "text_id")
	@ForeignKey(name = "fk_entity_type_to_name_text")
	private Text name;

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

	public Text getName() {
		return name;
	}

	public void setName(final Text name) {
		this.name = name;
	}

}
