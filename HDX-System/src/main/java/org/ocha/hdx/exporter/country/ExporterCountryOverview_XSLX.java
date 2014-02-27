package org.ocha.hdx.exporter.country;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;

/**
 * Exporter for a country overview.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryOverview_XSLX extends Exporter_XLSX<ExporterCountryQueryData> implements Exporter<XSSFWorkbook, ExporterCountryQueryData> {

	public ExporterCountryOverview_XSLX() {
		super();
	}

	public ExporterCountryOverview_XSLX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook incomingData, final ExporterCountryQueryData queryData) {
		incomingData.createSheet("overview");
		return super.export(incomingData, queryData);
	}

}
