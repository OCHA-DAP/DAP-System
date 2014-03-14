package org.ocha.hdx.rest.helper;

import java.util.List;

import org.ocha.hdx.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.dictionary.IndicatorTypeDictionary;

public class DisplayIndicatorTypeDictionaries {

	private List<IndicatorType> indicatorTypes;
	private List<IndicatorTypeDictionary> indicatorTypeDictionaries;
	private List<Type> importers;

	public List<IndicatorType> getIndicatorTypes() {
		return indicatorTypes;
	}

	public void setIndicatorTypes(final List<IndicatorType> indicatorTypes) {
		this.indicatorTypes = indicatorTypes;
	}

	public List<IndicatorTypeDictionary> getIndicatorTypeDictionaries() {
		return indicatorTypeDictionaries;
	}

	public void setIndicatorTypeDictionaries(final List<IndicatorTypeDictionary> indicatorTypeDictionaries) {
		this.indicatorTypeDictionaries = indicatorTypeDictionaries;
	}

	public List<Type> getImporters() {
		return importers;
	}

	public void setImporters(final List<Type> importers) {
		this.importers = importers;
	}

}
