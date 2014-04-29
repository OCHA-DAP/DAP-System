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
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;

import com.google.gson.JsonObject;

@Entity
@Table(name = "indicator_type_dictionary")
@SequenceGenerator(name = "indicator_type_dictionary_seq", sequenceName = "indicator_type_dictionary_seq")
public class IndicatorTypeDictionary implements JSONable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "indicator_type_dictionary_seq")
	@Column(name = "id", nullable = false)
	private long id;

	@ManyToOne
	@JoinColumn(name = "resource_configuration_id", nullable = false)
	@ForeignKey(name = "fk_indicator_type_dictionary_to_config")
	private final ResourceConfiguration resourceConfiguration;

	@ManyToOne
	@ForeignKey(name = "fk_indicator_type_dictionary_to_indicator_type")
	@JoinColumn(name = "indicator_type_id", nullable = false)
	private final IndicatorType indicatorType;

	@Column(name = "unnormalized_name", nullable = false, updatable = false)
	private final String unnormalizedName;

	public IndicatorTypeDictionary(final ResourceConfiguration resourceConfiguration, final IndicatorType indicatorType, final String unnormalizedName) {
		super();
		this.resourceConfiguration = resourceConfiguration;
		this.indicatorType = indicatorType;
		this.unnormalizedName = unnormalizedName;
	}

	public IndicatorTypeDictionary() {
		super();
		this.resourceConfiguration = null;
		this.indicatorType = null;
		this.unnormalizedName = null;
	}

	public ResourceConfiguration getResourceConfiguration() {
		return resourceConfiguration;
	}

	public IndicatorType getIndicatorType() {
		return indicatorType;
	}

	public String getUnnormalizedName() {
		return unnormalizedName;
	}

	@Override
	public JsonObject toJSON() {
		final JsonObject element = new JsonObject();
		element.addProperty("resourceConfiguration", getResourceConfiguration().getName());
		element.addProperty("unnormalizedName", getUnnormalizedName());
		element.addProperty("indicatorTypeName", getIndicatorType().getName().getDefaultValue());
		return element;
	}

}
