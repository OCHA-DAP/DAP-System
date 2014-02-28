package org.ocha.hdx.exporter;

import java.io.File;

import org.ocha.hdx.service.ExporterService;

public abstract class Exporter_PDF<QD extends QueryData> extends AbstractExporter<File, QD> {

	public Exporter_PDF(final ExporterService exporterService) {
		super(exporterService);
	}

	public Exporter_PDF(final Exporter<File, QD> exporter) {
		super(exporter);
	}

}
