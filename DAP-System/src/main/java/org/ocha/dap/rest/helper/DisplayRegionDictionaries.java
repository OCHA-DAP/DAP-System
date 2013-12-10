package org.ocha.dap.rest.helper;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.dictionary.RegionDictionary;

public class DisplayRegionDictionaries {

	private List<Entity> entities;
	private List<RegionDictionary> regionDictionaries;

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(final List<Entity> entities) {
		this.entities = entities;
	}

	public List<RegionDictionary> getRegionDictionaries() {
		return regionDictionaries;
	}

	public void setRegionDictionaries(final List<RegionDictionary> regionDictionaries) {
		this.regionDictionaries = regionDictionaries;
	}

}
