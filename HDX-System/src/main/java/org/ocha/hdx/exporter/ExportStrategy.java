package org.ocha.hdx.exporter;

/**
 * Export strategy interface.
 * This interface defines the only method "export" for a given exporter.
 */
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.country.ExportStrategyCountryOverview_XLSX;
import org.ocha.hdx.exporter.country.ExportStrategyCountry_XLSX;
import org.ocha.hdx.exporter.country.ExporterCountry;
import org.ocha.hdx.exporter.country.ExporterCountryOverview;

public interface ExportStrategy<E extends Exporter<E>, T> {

	public ExportStrategy<ExporterCountry, XSSFWorkbook> Country_XLSX = new ExportStrategyCountry_XLSX();
	public ExportStrategy<ExporterCountryOverview, XSSFSheet> CountryOverview_XLSX = new ExportStrategyCountryOverview_XLSX();

	/*
	public ExportStrategy<ExporterCountryOverview> CountryOverview_CSV = new ExportStrategyCountryOverview_CSV();
	public ExportStrategy<ExporterCountryOverview> CountryOverview_PDF = new ExportStrategyCountryOverview_PDF();
	*/

	public T export(E exporter);
}
