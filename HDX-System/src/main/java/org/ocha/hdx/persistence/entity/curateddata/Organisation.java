/**
 *
 */
package org.ocha.hdx.persistence.entity.curateddata;

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
import org.ocha.hdx.persistence.entity.i18n.Text;

/**
 * @author alexandru-m-g
 *
 */
@Entity
@Table(name = "organisation")
@SequenceGenerator(name = "organisation_seq", sequenceName = "organisation_seq")
public class Organisation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "organisation_seq")
	@Column(name = "id", nullable = false)
	private Long id;

//	@Column(name = "code", unique = true, nullable = false, updatable = false)
//	private String code;

	@Column(name = "org_link", nullable = true, updatable = true)
	private String orgLink;

	@ManyToOne
	@JoinColumn(name = "full_name_id", nullable=false)
	@ForeignKey(name = "fk_full_name_to_text")
	private Text fullName;

	@ManyToOne
	@JoinColumn(name = "short_name_id", nullable=false)
	@ForeignKey(name = "fk_short_name_to_text")
	private Text shortName;


	public Organisation() {
	}

	public Organisation(final String orgLink, final Text fullName, final Text shortName) {
		this.orgLink = orgLink;
		this.fullName = fullName;
		this.shortName = shortName;
	}
	public Long getId() {
		return this.id;
	}
	public void setId(final Long id) {
		this.id = id;
	}

	public String getOrgLink() {
		return this.orgLink;
	}
	public void setOrgLink(final String orgLink) {
		this.orgLink = orgLink;
	}
	public Text getFullName() {
		return this.fullName;
	}
	public void setFullName(final Text fullName) {
		this.fullName = fullName;
	}
	public Text getShortName() {
		return this.shortName;
	}
	public void setShortName(final Text shortName) {
		this.shortName = shortName;
	}


}
