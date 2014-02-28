package org.ocha.hdx.importer;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.ocha.hdx.persistence.entity.curateddata.Indicator;

public interface HDXWithCountryListImporter extends HDXImporter {
	public Map<String, String> getCountryList(final File file) ;

	public List<Indicator> transformToFinalFormat();
}
