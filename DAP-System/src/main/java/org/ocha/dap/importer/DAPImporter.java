package org.ocha.dap.importer;

import java.io.File;

public interface DAPImporter {

	public PreparedData prepareDataForImport(final File file);

}
