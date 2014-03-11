package org.ocha.hdx.exporter.country;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.service.ExporterService;

public class ExporterCountry5Years_XLSX extends AbstractExporter_XLSX {

	public ExporterCountry5Years_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountry5Years_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) {

		final Map<String, ReportRow> data = exporterService.getCountry5YearsData(queryData);

		return export(workbook, queryData, data, "5 Years indicators");

	}

}
