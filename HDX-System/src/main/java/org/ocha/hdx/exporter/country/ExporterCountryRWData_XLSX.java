package org.ocha.hdx.exporter.country;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country RW.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryRWData_XLSX extends AbstractExporterCountryRW_XLSX {

	public ExporterCountryRWData_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountryRWData_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) throws Exception {

		final Map<String, ReportRow> data = exporterService.getCountryRWData(queryData);

		return export(workbook, queryData, data, "ReliefWeb data");

	}
}
