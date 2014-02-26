package org.ocha.hdx.exporter.country;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.exporter.QueryData;

/**
 * Exporter for a country.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryCrisis_XLSX extends Exporter_XLSX implements Exporter<XSSFWorkbook> {

	public ExporterCountryCrisis_XLSX() {
		super();
	}

	public ExporterCountryCrisis_XLSX(final Exporter<XSSFWorkbook> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook incomingData, final QueryData queryData) {
		incomingData.createSheet("first sheet");
		return super.export(incomingData, queryData);
	}

}
