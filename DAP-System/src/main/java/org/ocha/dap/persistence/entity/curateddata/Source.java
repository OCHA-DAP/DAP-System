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
 *         a table of data sources on the responsibility level — that is, organisations like the World Bank, not specific web sites or URLs.
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
	@Column(name = "code", unique = true, nullable = false, updatable = false)
	private String code;

	@ManyToOne
	@JoinColumn(name = "text_id")
	@ForeignKey(name = "fk_source_to_name_text")
	private Text name;

	@Column(name = "org_link", nullable = true, updatable = true)
	private String orgLink;

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

	public String getOrgLink() {
		return orgLink;
	}

	public void setOrgLink(final String orgLink) {
		this.orgLink = orgLink;
	}

}
