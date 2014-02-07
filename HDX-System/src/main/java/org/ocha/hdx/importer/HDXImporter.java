package org.ocha.hdx.importer;

import java.io.File;

public interface HDXImporter {

	public PreparedData prepareDataForImport(final File file);

}
