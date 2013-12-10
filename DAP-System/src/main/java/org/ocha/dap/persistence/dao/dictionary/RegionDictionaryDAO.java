package org.ocha.dap.persistence.dao.dictionary;

import java.util.List;

import org.ocha.dap.persistence.entity.dictionary.RegionDictionary;

public interface RegionDictionaryDAO {
	
	public List<RegionDictionary> listRegionDictionary();
	
	public void addRegionDictionary(final String unnormalizedName, final String source, final String entityType, final String entityCode);

}
