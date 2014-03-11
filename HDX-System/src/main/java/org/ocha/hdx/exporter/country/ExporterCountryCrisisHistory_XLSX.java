package org.ocha.hdx.exporter.country;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country - crisis history.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryCrisisHistory_XLSX extends AbstractExporterCountry_XLSX {

	public ExporterCountryCrisisHistory_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountryCrisisHistory_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) {

		final Map<String, ReportRow> data = exporterService.getCountryCrisisHistoryData(queryData);

		return export(workbook, queryData, data, "Crisis history");

	}
}
