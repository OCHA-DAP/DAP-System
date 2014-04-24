package org.ocha.hdx.persistence.entity.dictionary;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.JsonObject;
import org.hibernate.annotations.ForeignKey;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;

@Entity
@Table(name = "indicator_type_dictionary")
public class IndicatorTypeDictionary extends AbstractDictionary {

	@ManyToOne
	@ForeignKey(name = "fk_indicator_type_dictionary_to_indicator_type")
	@JoinColumn(name = "indicator_type_id", nullable = false)
	private IndicatorType indicatorType;

	public IndicatorType getIndicatorType() {
		return indicatorType;
	}

	public IndicatorTypeDictionary(final String unnormalizedName, final String importer, final IndicatorType indicatorType, final ResourceConfiguration configuration) {
		super(unnormalizedName, importer, configuration);
		this.indicatorType = indicatorType;
	}

	public IndicatorTypeDictionary() {
		super();
		this.indicatorType = null;

	}

    @Override
    public JsonObject toJSON() {
        JsonObject element = super.toJSON();
        element.addProperty("indicatorTypeName", getIndicatorType().getName().getDefaultValue());
        return element;
    }

}
