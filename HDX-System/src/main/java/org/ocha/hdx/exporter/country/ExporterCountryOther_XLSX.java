package org.ocha.hdx.exporter.country;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country - other.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryOther_XLSX extends AbstractExporterCountry_XLSX {

	public ExporterCountryOther_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountryOther_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) {

		final Map<String, ReportRow> data = exporterService.getCountryOtherData(queryData);

		return export(workbook, queryData, data, "Other");

	}
}
