package org.ocha.hdx.exporter.country;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country - socio-economic.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountrySocioEconomic_XLSX extends AbstractExporterCountry_XLSX {

	public ExporterCountrySocioEconomic_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountrySocioEconomic_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) {

		final Map<String, ReportRow> data = exporterService.getCountrySocioEconomicData(queryData);

		return export(workbook, queryData, data, "Socio-economic");

	}
}
