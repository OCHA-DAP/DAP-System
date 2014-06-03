package org.ocha.hdx.importer;

import java.io.File;
import java.util.Map;

public interface HDXWithCountryListImporter extends HDXImporter {
	public Map<String, String> getCountryList(final File file);

}
