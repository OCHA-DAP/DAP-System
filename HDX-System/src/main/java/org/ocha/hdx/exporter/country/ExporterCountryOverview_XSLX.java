package org.ocha.hdx.exporter.country;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.exporter.QueryData;

/**
 * Exporter for a country overview.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryOverview_XSLX extends Exporter_XLSX implements Exporter<XSSFWorkbook> {

	public ExporterCountryOverview_XSLX() {
		super();
	}

	public ExporterCountryOverview_XSLX(final Exporter<XSSFWorkbook> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook incomingData, final QueryData queryData) {
		incomingData.createSheet("overview");
		return super.export(incomingData, queryData);
	}

}
