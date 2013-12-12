package org.ocha.dap.persistence.dao.dictionary;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.Source;
import org.ocha.dap.persistence.entity.dictionary.SourceDictionary;

public interface SourceDictionaryDAO {

	public List<SourceDictionary> listSourceDictionaries();

	public void addSourceDictionary(final String unnormalizedName, final String importer, final Source source);

	public List<SourceDictionary> getSourceDictionariesByImporter(final String importer);

	/**
	 * very likely to be used by the unit tests only
	 */
	public void deleteAllSourceDictionaries();

}
