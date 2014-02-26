package org.ocha.hdx.exporter.country;

import java.io.File;

import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_PDF;
import org.ocha.hdx.exporter.QueryData;

public class ExporterCountryCrisis_PDF extends Exporter_PDF implements Exporter<File> {

	public ExporterCountryCrisis_PDF() {
		super();
	}

	public ExporterCountryCrisis_PDF(final Exporter<File> exporter) {
		super(exporter);
	}

	@Override
	public File export(final File incomingData, final QueryData queryData) {
		// Do something
		return super.export(incomingData, queryData);
	}

}
