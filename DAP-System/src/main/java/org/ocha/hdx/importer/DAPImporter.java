package org.ocha.hdx.importer;

import java.io.File;

public interface DAPImporter {

	public PreparedData prepareDataForImport(final File file);

}
