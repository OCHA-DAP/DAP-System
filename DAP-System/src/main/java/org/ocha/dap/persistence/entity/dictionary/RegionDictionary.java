package org.ocha.dap.persistence.entity.dictionary;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "region_dictionary")
public class RegionDictionary extends AbstractDictionary {

	@ManyToOne
	@ForeignKey(name = "fk_region_dictionary_to_entity")
	@JoinColumn(name = "entity_id", nullable = false)
	private org.ocha.dap.persistence.entity.curateddata.Entity entity;

	public void setEntity(final org.ocha.dap.persistence.entity.curateddata.Entity entity) {
		this.entity = entity;
	}

	public RegionDictionary(final String unnormalizedName, final String importer, final org.ocha.dap.persistence.entity.curateddata.Entity entity) {
		super(unnormalizedName, importer);
		this.entity = entity;
	}

	public RegionDictionary() {
		super();
		this.entity = null;

	}

	public RegionDictionary(final String unnormalizedName, final String importer) {
		super(unnormalizedName, importer);
	}

	public org.ocha.dap.persistence.entity.curateddata.Entity getEntity() {
		return entity;
	}

}
