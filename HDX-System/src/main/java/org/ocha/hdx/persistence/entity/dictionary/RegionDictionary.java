package org.ocha.hdx.persistence.entity.dictionary;

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
import org.ocha.hdx.model.JSONable;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;

import com.google.gson.JsonObject;

@Entity
@Table(name = "region_dictionary")
@SequenceGenerator(name = "region_dictionary_seq", sequenceName = "region_dictionary_seq")
public class RegionDictionary implements JSONable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "region_dictionary_seq")
	@Column(name = "id", nullable = false)
	private long id;

	@ManyToOne
	@JoinColumn(name = "resource_configuration_id", nullable = false)
	@ForeignKey(name = "fk_region_dictionary_to_config")
	private final ResourceConfiguration resourceConfiguration;

	@ManyToOne
	@ForeignKey(name = "fk_region_dictionary_to_entity")
	@JoinColumn(name = "entity_id", nullable = false)
	private final org.ocha.hdx.persistence.entity.curateddata.Entity entity;

	@Column(name = "unnormalized_name", nullable = false, updatable = false)
	private final String unnormalizedName;

	public RegionDictionary(final ResourceConfiguration resourceConfiguration, final org.ocha.hdx.persistence.entity.curateddata.Entity entity, final String unnormalizedName) {
		super();
		this.resourceConfiguration = resourceConfiguration;
		this.entity = entity;
		this.unnormalizedName = unnormalizedName;
	}

	public RegionDictionary() {
		super();
		this.resourceConfiguration = null;
		this.entity = null;
		this.unnormalizedName = null;
	}

	public ResourceConfiguration getResourceConfiguration() {
		return resourceConfiguration;
	}

	public org.ocha.hdx.persistence.entity.curateddata.Entity getEntity() {
		return entity;
	}

	public String getUnnormalizedName() {
		return unnormalizedName;
	}

	@Override
	public JsonObject toJSON() {
		final JsonObject element = new JsonObject();
		element.addProperty("resourceConfiguration", getResourceConfiguration().getName());
		element.addProperty("unnormalizedName", getUnnormalizedName());
		element.addProperty("entity", getEntity().getName().getDefaultValue());
		return element;
	}

}
