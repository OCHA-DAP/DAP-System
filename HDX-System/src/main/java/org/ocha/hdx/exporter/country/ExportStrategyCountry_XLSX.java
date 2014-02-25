package org.ocha.hdx.exporter.country;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.ExportStrategy;
import org.ocha.hdx.exporter.ExportStrategy_XLSX;

/**
 * Export strategy for a country in XSLX.
 * @author bmichiels
 *
 */
public class ExportStrategyCountry_XLSX extends ExportStrategy_XLSX implements ExportStrategy<ExporterCountry, XSSFWorkbook> {

	@Override
	public XSSFWorkbook export(final ExporterCountry country) {
		
		final XSSFWorkbook workbook = new XSSFWorkbook();
		
		// Exporting the country overview
		final ExporterCountryOverview countryOverview_XLSX = new ExporterCountryOverview(ExportStrategy.CountryOverview_XLSX, (ExporterCountryQueryData) country.getQueryData());
		countryOverview_XLSX.export();
		
		System.out.println("XLSX : " + country.getQueryData());
		return workbook;
	}
}
