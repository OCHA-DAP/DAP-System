/**
 *
 */
package org.ocha.hdx.persistence.entity.configs;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//FIXME see how we plan to use this
/**
 * @author dan
 * 
 */
// @Entity
@Table(name = "ckan_export_configuration")
@SequenceGenerator(name = "ckan_export_configuration_seq", sequenceName = "ckan_export_configuration_seq")
public class CKANExportConfiguration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ckan_export_configuration_seq")
	@Column(name = "id", nullable = false)
	private long id;

	// @Column(name = "name", unique = true, nullable = false, updatable = true)
	// @Index(name = "nameIndex")
	// private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parentConfiguration", orphanRemoval = true)
	private Set<CKANExportConfigEntry> configEntries;

	public CKANExportConfiguration() {
	}

	// public CKANExportConfiguration(final String name, final Set<CKANExportConfigEntry> configEntries) {
	// super();
	// this.name = name;
	// this.configEntries = configEntries;
	// }

	public CKANExportConfiguration(final Set<CKANExportConfigEntry> configEntries) {
		super();
		this.configEntries = configEntries;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	// public String getName() {
	// return name;
	// }
	//
	// public void setName(final String name) {
	// this.name = name;
	// }

	public Set<CKANExportConfigEntry> getConfigEntries() {
		return configEntries;
	}

	public void setConfigEntries(final Set<CKANExportConfigEntry> configEntries) {
		this.configEntries = configEntries;
	}

}
