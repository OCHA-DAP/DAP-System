package org.ocha.hdx.exporter.country;

import java.io.File;

import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_PDF;

public class ExporterCountryCrisis_PDF extends Exporter_PDF<ExporterCountryQueryData> implements Exporter<File, ExporterCountryQueryData> {

	public ExporterCountryCrisis_PDF() {
		super();
	}

	public ExporterCountryCrisis_PDF(final Exporter<File, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public File export(final File incomingData, final ExporterCountryQueryData queryData) {
		// Do something
		return super.export(incomingData, queryData);
	}

}
