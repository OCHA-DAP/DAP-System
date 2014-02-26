package org.ocha.hdx.exporter;

import java.io.File;

public abstract class Exporter_PDF extends AbstractExporter<File> {

	public Exporter_PDF() {
		super();
	}

	public Exporter_PDF(final Exporter<File> exporter) {
		super(exporter);
	}

}
