package org.ocha.hdx.persistence.entity.dictionary;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;

import com.google.gson.JsonObject;

@Entity
@Table(name = "indicator_type_dictionary")
public class IndicatorTypeDictionary {

	@ManyToOne
	@ForeignKey(name = "fk_indicator_type_dictionary_to_indicator_type")
	@JoinColumn(name = "indicator_type_id", nullable = false)
	private final IndicatorType indicatorType;

	public IndicatorType getIndicatorType() {
		return indicatorType;
	}

	public IndicatorTypeDictionary() {
		super();
		this.indicatorType = null;

	}

	@Override
	public JsonObject toJSON() {
		final JsonObject element = super.toJSON();
		element.addProperty("indicatorTypeName", getIndicatorType().getName().getDefaultValue());
		return element;
	}

}
