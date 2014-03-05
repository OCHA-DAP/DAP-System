package org.ocha.hdx.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.country.ExporterCountryQueryData;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;

/**
 * Exporter service.
 * 
 * @author bmichiels
 * 
 */
public interface ExporterService {

	/*
	 * Delegates from CuratedDataService.
	 */

	public List<Object[]> listIndicatorsForCountryOverview(final String countryCode, final String languageCode);

	public Map<Integer, List<Object[]>> listIndicatorsForCountryCrisis(final String countryCode, final int fromYear, final int toYear, final String languageCode);

	public IndicatorType getIndicatorTypeByCode(final String code);

	/*
	 * Country reports.
	 */
	public XSSFWorkbook exportCountry_XLSX(String countryCode, Integer fromYear, Integer toYear, String language);

	// Country overview
	public List<Object[]> getCountryOverviewData(final ExporterCountryQueryData queryData);

	// Country crisis history
	public Map<Integer, List<Object[]>> getCountryCrisisHistoryData(ExporterCountryQueryData queryData);

}
