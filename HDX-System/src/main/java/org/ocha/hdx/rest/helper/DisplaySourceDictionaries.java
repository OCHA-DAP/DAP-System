package org.ocha.hdx.rest.helper;

import java.util.List;

import org.ocha.hdx.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.dictionary.SourceDictionary;

public class DisplaySourceDictionaries {

	private List<Source> sources;
	private List<SourceDictionary> sourceDictionaries;
	private List<Type> importers;

	public List<Source> getSources() {
		return sources;
	}

	public void setSources(final List<Source> sources) {
		this.sources = sources;
	}

	public List<SourceDictionary> getSourceDictionaries() {
		return sourceDictionaries;
	}

	public void setSourceDictionaries(final List<SourceDictionary> sourceDictionaries) {
		this.sourceDictionaries = sourceDictionaries;
	}

	public List<Type> getImporters() {
		return importers;
	}

	public void setImporters(final List<Type> importers) {
		this.importers = importers;
	}

}
