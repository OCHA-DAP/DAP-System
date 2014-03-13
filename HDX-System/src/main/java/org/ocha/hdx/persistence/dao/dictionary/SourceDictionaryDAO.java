package org.ocha.hdx.persistence.dao.dictionary;

import java.util.List;

import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.dictionary.SourceDictionary;

public interface SourceDictionaryDAO {

	public List<SourceDictionary> listSourceDictionaries();

	public void createSourceDictionary(final String unnormalizedName, final String importer, final Source source);

	public void deleteSourceDictionary(final SourceDictionary sourceDictionary);

	public void deleteSourceDictionary(String unnormalizedName, String importer);

	public List<SourceDictionary> getSourceDictionariesByImporter(final String importer);

	/**
	 * very likely to be used by the unit tests only
	 */
	public void deleteAllSourceDictionaries();

}
