package org.ocha.hdx.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.country.ExporterCountryOverview_XLSX;
import org.ocha.hdx.exporter.country.ExporterCountryQueryData;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.springframework.beans.factory.annotation.Autowired;

public class ExporterServiceImpl implements ExporterService {

	@Autowired
	private CuratedDataService curatedDataService;

	/*
	 * Delegates to CuratedDataService
	 */

	@Override
	public IndicatorType getIndicatorTypeByCode(final String code) {
		return curatedDataService.getIndicatorTypeByCode(code);
	}

	@Override
	public List<Object[]> getCountryOverviewData(final ExporterCountryQueryData queryData) {
		return curatedDataService.listIndicatorsForCountryOverview(queryData.getCountryCode(), queryData.getLanguage());
	}

	@Override
	public Map<String, List<Object[]>> getCountryCrisisHistoryData(final ExporterCountryQueryData queryData) {
		return curatedDataService.listIndicatorsForCountryCrisisHistory(queryData.getCountryCode(), Integer.valueOf(queryData.getFromYear()), Integer.valueOf(queryData.getToYear()),
				queryData.getLanguage());
	}

	@Override
	public List<Object[]> listIndicatorsForCountryOverview(final String countryCode, final String languageCode) {
		return curatedDataService.listIndicatorsForCountryOverview(countryCode, languageCode);
	}

	@Override
	public Map<String, List<Object[]>> listIndicatorsForCountryCrisis(final String countryCode, final int fromYear, final int toYear, final String languageCode) {
		return curatedDataService.listIndicatorsForCountryCrisisHistory(countryCode, fromYear, toYear, languageCode);
	}

	/*
	 * Exports
	 */

	/**
	 * Export a country report as XLSX
	 */
	@Override
	public XSSFWorkbook exportCountry_XLSX(final String countryCode, final String fromYear, final String toYear, final String language) {
		// Set the query data
		final ExporterCountryQueryData exporterCountryQueryData = new ExporterCountryQueryData();
		exporterCountryQueryData.setCountryCode(countryCode);
		exporterCountryQueryData.setFromYear(fromYear);
		exporterCountryQueryData.setToYear(toYear);
		exporterCountryQueryData.setLanguage(language);

		// Define the exporter
		// Country report contains :
		// 1. Country overview
		// 2. Country crisis history
		// 3. ... TODO
		// final Exporter<XSSFWorkbook, ExporterCountryQueryData> countryExporter = new ExporterCountryOverview_XLSX(new ExporterCountryCrisisHistory_XLSX(this));

		final Exporter<XSSFWorkbook, ExporterCountryQueryData> countryExporter = new ExporterCountryOverview_XLSX(this);

		// Export the data in a new workbook
		final XSSFWorkbook workbook = new XSSFWorkbook();
		countryExporter.export(workbook, exporterCountryQueryData);

		// Return the workbook
		return workbook;
	}
}
