package org.ocha.dap.persistence.dao.dictionary;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.dictionary.RegionDictionary;

public interface RegionDictionaryDAO {

	public List<RegionDictionary> listRegionDictionaries();

	public void addRegionDictionary(final String unnormalizedName, final String importer, final Entity entity);

}
