package org.ocha.hdx.exporter.country;

import java.io.File;

import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_PDF;

public class ExporterCountryOverview_PDF extends Exporter_PDF<ExporterCountryQueryData> implements Exporter<File, ExporterCountryQueryData> {

	public ExporterCountryOverview_PDF() {
		super();
	}

	public ExporterCountryOverview_PDF(final Exporter<File, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public File export(final File incomingData, final ExporterCountryQueryData queryData) {
		// Do something
		return super.export(incomingData, queryData);
	}

}
