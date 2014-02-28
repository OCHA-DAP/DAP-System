package org.ocha.hdx.exporter.country;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryCrisisHistory_XLSX extends Exporter_XLSX<ExporterCountryQueryData> {

	public ExporterCountryCrisisHistory_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountryCrisisHistory_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook incomingData, final ExporterCountryQueryData queryData) {
		incomingData.createSheet("Crisis history");
		return super.export(incomingData, queryData);
	}

}
