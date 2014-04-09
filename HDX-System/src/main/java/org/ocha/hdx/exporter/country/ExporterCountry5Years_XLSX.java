package org.ocha.hdx.exporter.country;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.service.ExporterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exporter for a country - 5-years data.
 * 
 * @author seustachi
 * 
 */
public class ExporterCountry5Years_XLSX extends AbstractExporterCountry_XLSX {

	private static Logger logger = LoggerFactory.getLogger(ExporterCountry5Years_XLSX.class);

	public ExporterCountry5Years_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountry5Years_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) throws Exception {
		// logger.debug("******     About to export 5 years data");
		final Map<String, ReportRow> data = exporterService.getCountry5YearsData(queryData);
		// for (final ReportRow row : data.values()) {
		// logger.debug(String.format("****** found row : %s", row));
		// }

		return export(workbook, queryData, data, "5 Years indicators");

	}

}
