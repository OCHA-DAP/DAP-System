package org.ocha.hdx.exporter;

import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.country.ExporterCountryCrisis_PDF;
import org.ocha.hdx.exporter.country.ExporterCountryCrisis_XLSX;
import org.ocha.hdx.exporter.country.ExporterCountryOverview_PDF;
import org.ocha.hdx.exporter.country.ExporterCountryOverview_XSLX;
import org.ocha.hdx.exporter.country.ExporterCountryQueryData;

/**
 * TODO This class can be used to perform reports manually.
 * 
 * @author bmichiels
 * 
 */
public class Reporter {
	public static void main(final String[] args) {

		// TODO Query data

		// TODO Process data

		// Export data

		// final ExporterCountryOverview countryOverview_CSV = new ExporterCountryOverview(ExportStrategy.CountryOverview_CSV, data);
		// final ExporterCountryOverview countryOverview_PDF = new ExporterCountryOverview(ExportStrategy.CountryOverview_PDF, data);
		// final ExporterCountry country_XLSX = new ExporterCountry(ExportStrategy.Country_XLSX, new ExporterCountryQueryData());

		// System.out.println(countryOverview_CSV.export());
		// System.out.println(countryOverview_PDF.export());

		// Write the file
		// ExportStrategy_XLSX.writeFile((Workbook) country_XLSX.export(), "C:\\Users\\bmichiels\\Desktop\\workbook.xlsx");

		final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter = new ExporterCountryCrisis_XLSX(new ExporterCountryOverview_XSLX());
		// final Exporter<XSSFWorkbook> exporter = new ExporterCountryOverview_XSLX(new ExporterCountry_XLSX());

		final Exporter<File, ExporterCountryQueryData> exporterPDF = new ExporterCountryCrisis_PDF(new ExporterCountryOverview_PDF());

		XSSFWorkbook workbook = new XSSFWorkbook();
		// Export
		workbook = exporter.export(workbook, null);
		Exporter_XLSX.writeFile(workbook, "C:\\Users\\seustachi\\Desktop\\workbook.xlsx");
	}
}