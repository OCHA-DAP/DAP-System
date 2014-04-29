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
import org.ocha.hdx.persistence.entity.curateddata.Source;

import com.google.gson.JsonObject;

@Entity
@Table(name = "source_dictionary")
@SequenceGenerator(name = "source_dictionary_seq", sequenceName = "source_dictionary_seq")
public class SourceDictionary implements JSONable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "source_dictionary_seq")
	@Column(name = "id", nullable = false)
	private long id;

	@ManyToOne
	@JoinColumn(name = "resource_configuration_id", nullable = false)
	@ForeignKey(name = "fk_source_dictionary_to_config")
	private final ResourceConfiguration resourceConfiguration;

	@ManyToOne
	@ForeignKey(name = "fk_source_dictionary_to_source")
	@JoinColumn(name = "source_id", nullable = false)
	private final Source source;

	@Column(name = "unnormalized_name", nullable = false, updatable = false)
	private final String unnormalizedName;

	public SourceDictionary(final ResourceConfiguration resourceConfiguration, final Source source, final String unnormalizedName) {
		super();
		this.resourceConfiguration = resourceConfiguration;
		this.source = source;
		this.unnormalizedName = unnormalizedName;
	}

	public SourceDictionary() {
		super();
		this.resourceConfiguration = null;
		this.source = null;
		this.unnormalizedName = null;
	}

	public ResourceConfiguration getResourceConfiguration() {
		return resourceConfiguration;
	}

	public Source getSource() {
		return source;
	}

	public String getUnnormalizedName() {
		return unnormalizedName;
	}

	@Override
	public JsonObject toJSON() {
		final JsonObject element = new JsonObject();
		element.addProperty("resourceConfiguration", getResourceConfiguration().getName());
		element.addProperty("unnormalizedName", getUnnormalizedName());
		element.addProperty("sourceName", getSource().getName().getDefaultValue());
		return element;
	}

}
