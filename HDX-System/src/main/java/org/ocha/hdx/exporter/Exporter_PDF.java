package org.ocha.hdx.exporter;

import java.io.File;

public abstract class Exporter_PDF<QD extends QueryData> extends AbstractExporter<File, QD> {

	public Exporter_PDF() {
		super();
	}

	public Exporter_PDF(final Exporter<File, QD> exporter) {
		super(exporter);
	}

}
