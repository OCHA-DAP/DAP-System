package org.ocha.hdx.rest.helper;

import java.util.List;

import org.ocha.hdx.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.dictionary.RegionDictionary;

public class DisplayRegionDictionaries {

	private List<Entity> entities;
	private List<RegionDictionary> regionDictionaries;
	private List<Type> importers;

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

	public List<Type> getImporters() {
		return importers;
	}

	public void setImporters(final List<Type> importers) {
		this.importers = importers;
	}

}
