package org.ocha.hdx.persistence.dao.dictionary;

import java.util.List;

import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.dictionary.SourceDictionary;

public interface SourceDictionaryDAO {

	public List<SourceDictionary> listSourceDictionaries();

	public void createSourceDictionary(final ResourceConfiguration resourceConfiguration, final Source source, final String unnormalizedName);

	public void deleteSourceDictionary(final SourceDictionary sourceDictionary);

	public List<SourceDictionary> getSourceDictionariesByResourceConfiguration(final ResourceConfiguration resourceConfiguration);

	/**
	 * very likely to be used by the unit tests only
	 */
	public void deleteAllSourceDictionaries();

}
