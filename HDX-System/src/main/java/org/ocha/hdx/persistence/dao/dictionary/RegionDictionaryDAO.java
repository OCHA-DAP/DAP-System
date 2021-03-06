package org.ocha.hdx.persistence.dao.dictionary;

import java.util.List;

import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.dictionary.RegionDictionary;

public interface RegionDictionaryDAO {

	public List<RegionDictionary> listRegionDictionaries();

	public void createRegionDictionary(final ResourceConfiguration resourceConfiguration, final Entity entity, final String unnormalizedName);

	public void deleteRegionDictionary(final RegionDictionary regionDictionary);

	public void deleteRegionDictionary(final long id);

	public List<RegionDictionary> getRegionDictionariesByResourceConfiguration(final ResourceConfiguration resourceConfiguration);

}
