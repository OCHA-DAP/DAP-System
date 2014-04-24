package org.ocha.hdx.persistence.dao.dictionary;

import java.util.List;

import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.dictionary.RegionDictionary;

public interface RegionDictionaryDAO {

	public List<RegionDictionary> listRegionDictionaries(final Long configId);

	public void createRegionDictionary(final String unnormalizedName, final String importer, final Entity entity, final ResourceConfiguration configuration);

	public void deleteRegionDictionary(final RegionDictionary regionDictionary);

	public void deleteRegionDictionary(String unnormalizedName, String importer);

}
