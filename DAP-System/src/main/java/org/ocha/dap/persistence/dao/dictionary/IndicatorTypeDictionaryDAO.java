package org.ocha.dap.persistence.dao.dictionary;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.dictionary.IndicatorTypeDictionary;

public interface IndicatorTypeDictionaryDAO {

	public List<IndicatorTypeDictionary> listIndicatorTypeDictionaries();

	public void addIndicatorTypeDictionary(final String unnormalizedName, final String importer, final IndicatorType indicatorType);

	public void deleteIndicatorTypeDictionary(final IndicatorTypeDictionary indicatorTypeDictionary);

	public void deleteIndicatorTypeDictionary(String unnormalizedName, String importer);

}
