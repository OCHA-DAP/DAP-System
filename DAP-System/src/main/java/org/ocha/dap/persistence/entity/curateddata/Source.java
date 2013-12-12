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
 *         a table of data sources on the responsibility level — that is,
 *         organisations like the World Bank, not specific web sites or URLs.
 * 
 */
@Entity
@Table(name = "source")
@SequenceGenerator(name = "source_seq", sequenceName = "source_seq")
public class Source {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "source_seq")
	@Column(name = "id", nullable = false)
	private long id;
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
