package org.ocha.hdx.exporter.country;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country - capacity.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryCapacity_XLSX extends AbstractExporterCountry_XLSX {

	public ExporterCountryCapacity_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountryCapacity_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) throws Exception {

		final Map<String, ReportRow> data = exporterService.getCountryCapacityData(queryData);

		return export(workbook, queryData, data, "Capacity");

	}
}
