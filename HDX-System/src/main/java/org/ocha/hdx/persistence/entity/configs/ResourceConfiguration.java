/**
 *
 */
package org.ocha.hdx.persistence.entity.configs;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * @author alexandru-m-g
 *
 */
@Entity
@Table(name = "resource_configuration")
@SequenceGenerator(name = "resource_configuration_seq", sequenceName = "resource_configuration_seq")
public class ResourceConfiguration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "resource_configuration_seq")
	@Column(name = "id", nullable = false)
	private long id;

	/**
	 * This is the name that will be displayed / edited by the user
	 */
	@Column(name = "name", unique = true, nullable = false, updatable = true)
	@Index(name = "nameIndex")
	private String name;



	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
			mappedBy = "parentConfiguration", orphanRemoval=true)
	private Set<ResourceConfigEntry> generalConfigEntries;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
			mappedBy = "parentConfiguration", orphanRemoval=true)
	private Set<IndicatorResourceConfigEntry> indicatorConfigEntries;




	public ResourceConfiguration() {
	}

	public ResourceConfiguration(final String name,
			final Set<ResourceConfigEntry> generalConfigEntries,
			final Set<IndicatorResourceConfigEntry> indicatorConfigEntries) {
		super();
		this.name = name;
		this.generalConfigEntries = generalConfigEntries;
		this.indicatorConfigEntries = indicatorConfigEntries;
	}

	public long getId() {
		return this.id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Set<ResourceConfigEntry> getGeneralConfigEntries() {
		return this.generalConfigEntries;
	}

	public void setGeneralConfigEntries(
			final Set<ResourceConfigEntry> generalConfigEntries) {
		this.generalConfigEntries = generalConfigEntries;
	}

	public Set<IndicatorResourceConfigEntry> getIndicatorConfigEntries() {
		return this.indicatorConfigEntries;
	}

	public void setIndicatorConfigEntries(
			final Set<IndicatorResourceConfigEntry> indicatorConfigEntries) {
		this.indicatorConfigEntries = indicatorConfigEntries;
	}

}
