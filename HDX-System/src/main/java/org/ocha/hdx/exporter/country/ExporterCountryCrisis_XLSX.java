package org.ocha.hdx.exporter.country;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;

/**
 * Exporter for a country.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryCrisis_XLSX extends Exporter_XLSX<ExporterCountryQueryData> implements Exporter<XSSFWorkbook, ExporterCountryQueryData> {

	public ExporterCountryCrisis_XLSX() {
		super();
	}

	public ExporterCountryCrisis_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook incomingData, final ExporterCountryQueryData queryData) {
		incomingData.createSheet("first sheet");
		return super.export(incomingData, queryData);
	}

}
