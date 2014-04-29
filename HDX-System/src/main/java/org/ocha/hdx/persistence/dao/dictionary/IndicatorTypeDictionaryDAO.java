package org.ocha.hdx.persistence.dao.dictionary;

import java.util.List;

import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.dictionary.IndicatorTypeDictionary;

public interface IndicatorTypeDictionaryDAO {

	public List<IndicatorTypeDictionary> listIndicatorTypeDictionaries(final Long configId);

	public void createIndicatorTypeDictionary(final ResourceConfiguration resourceConfiguration, final IndicatorType indicatorType, final String unnormalizedName);

	public void deleteIndicatorTypeDictionary(final IndicatorTypeDictionary indicatorTypeDictionary);

}
