package org.ocha.hdx.service;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.country.ExporterCountryCrisisHistory_XLSX;
import org.ocha.hdx.exporter.country.ExporterCountryOverview_XLSX;
import org.ocha.hdx.exporter.country.ExporterCountryQueryData;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.springframework.beans.factory.annotation.Autowired;

public class ExporterServiceImpl implements ExporterService {

	@Autowired
	private CuratedDataService curatedDataService;

	@Override
	public IndicatorType getIndicatorTypeByCode(final String code) {
		return curatedDataService.getIndicatorTypeByCode(code);
	}

	@Override
	public List<Object[]> listIndicatorsForCountryOverview(final String countryCode, final String languageCode) {
		return curatedDataService.listIndicatorsForCountryOverview(countryCode, languageCode);
	}

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
		//		1. Country overview
		//		2. Country crisis history
		//		3. ... TODO
		final Exporter<XSSFWorkbook, ExporterCountryQueryData> countryExporter = new ExporterCountryOverview_XLSX(new ExporterCountryCrisisHistory_XLSX(this));

		// Export the data in a new workbook
		final XSSFWorkbook workbook = new XSSFWorkbook();
		countryExporter.export(workbook, exporterCountryQueryData);

		// Return the workbook
		return workbook;
	}
	
	@Override
	public List<Object[]> getCountryOverviewData(final ExporterCountryQueryData queryData) {
		return curatedDataService.listIndicatorsForCountryOverview(queryData.getCountryCode(), queryData.getLanguage());
	}
}
