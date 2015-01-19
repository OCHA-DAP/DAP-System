/**
 *
 */
package org.ocha.hdx.persistence.entity.configs;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

/**
 * @author dan
 * 
 */
// @Entity
@Table(name = "ckan_export_config_entry")
@org.hibernate.annotations.Table(indexes = { @Index(name = "ckan_export_config_entry_index", columnNames = "entry_key") }, appliesTo = "ckan_export_config_entry")
@SequenceGenerator(name = "ckan_export_config_entry_seq", sequenceName = "ckan_export_config_entry_seq")
public class CKANExportConfigEntry extends AbstractConfigEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ckan_export_config_entry_seq")
	@Column(name = "id", nullable = false)
	private long id;

	@ManyToOne
	@JoinColumn(name = "ckan_export_configuration_id", nullable = false)
	@ForeignKey(name = "fk_ckan_export_config_map_to_parent")
	private CKANExportConfiguration parentConfiguration;

	public CKANExportConfigEntry() {
	}

	public CKANExportConfigEntry(final String key, final String value) {
		super(key, value);
	}

	public CKANExportConfiguration getParentConfiguration() {
		return parentConfiguration;
	}

	public void setParentConfiguration(final CKANExportConfiguration parentConfiguration) {
		this.parentConfiguration = parentConfiguration;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

}
