package org.ocha.hdx.persistence.dao.dictionary;

import java.util.List;

import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.dictionary.IndicatorTypeDictionary;

public interface IndicatorTypeDictionaryDAO {

	public List<IndicatorTypeDictionary> listIndicatorTypeDictionaries(final Long configId);

	public void createIndicatorTypeDictionary(final String unnormalizedName, final String importer, final IndicatorType indicatorType, final ResourceConfiguration configuration);

	public void deleteIndicatorTypeDictionary(final IndicatorTypeDictionary indicatorTypeDictionary);

	public void deleteIndicatorTypeDictionary(String unnormalizedName, String importer);

}
