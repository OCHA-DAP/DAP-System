package org.ocha.hdx.importer;

import java.io.File;

public class DummyImporter implements HDXImporter {

	@Override
	public PreparedData prepareDataForImport(final File file) {
		return null;
	}

}
