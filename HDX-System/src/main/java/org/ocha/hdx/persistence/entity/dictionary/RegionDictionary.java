package org.ocha.hdx.persistence.entity.dictionary;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.JsonObject;
import org.hibernate.annotations.ForeignKey;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;

@Entity
@Table(name = "region_dictionary")
public class RegionDictionary extends AbstractDictionary {

	@ManyToOne
	@ForeignKey(name = "fk_region_dictionary_to_entity")
	@JoinColumn(name = "entity_id", nullable = false)
	private org.ocha.hdx.persistence.entity.curateddata.Entity entity;

	public void setEntity(final org.ocha.hdx.persistence.entity.curateddata.Entity entity) {
		this.entity = entity;
	}

	public RegionDictionary(final String unnormalizedName, final String importer, final org.ocha.hdx.persistence.entity.curateddata.Entity entity, final ResourceConfiguration configuration) {
		super(unnormalizedName, importer, configuration);
		this.entity = entity;
	}

	public RegionDictionary() {
		super();
		this.entity = null;

	}

    @Override
    public JsonObject toJSON() {
        JsonObject element = super.toJSON();
        element.addProperty("entityName", getEntity().getName().getDefaultValue());
        element.addProperty("entityType", getEntity().getType().getName().getDefaultValue());
        return element;
    }

	public org.ocha.hdx.persistence.entity.curateddata.Entity getEntity() {
		return entity;
	}

}
