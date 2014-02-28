package org.ocha.hdx.exporter.country;

import java.io.File;

import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_PDF;
import org.ocha.hdx.service.ExporterService;

public class ExporterCountryCrisisHistory_PDF extends Exporter_PDF<ExporterCountryQueryData> {

	public ExporterCountryCrisisHistory_PDF(final Exporter<File, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	public ExporterCountryCrisisHistory_PDF(final ExporterService exporterService) {
		super(exporterService);
	}

	@Override
	public File export(final File incomingData, final ExporterCountryQueryData queryData) {
		// Do something
		return super.export(incomingData, queryData);
	}

}
