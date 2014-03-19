package org.ocha.hdx.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.country.ExporterCountryQueryData;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.exporter.indicator.ExporterIndicatorQueryData;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.view.IndicatorData;
import org.ocha.hdx.persistence.entity.view.IndicatorTypeOverview;

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

	public IndicatorType getIndicatorTypeByCode(final String code);

	/* **************** */
	/* Country reports. */
	/* **************** */
	public XSSFWorkbook exportCountry_XLSX(String countryCode, Integer fromYear, Integer toYear, String language);

	/*
	 *  Country overview
	 */
	public List<Object[]> getCountryOverviewData(final ExporterCountryQueryData queryData);

	/**
	 * Only the rows with actual data are added in the returned result.
	 * 
	 * @param queryData
	 * @return a map of the rows we can expect in the report.
	 */
	public Map<String, ReportRow> getCountryCrisisHistoryData(ExporterCountryQueryData queryData);

	public Map<String, ReportRow> getCountrySocioEconomicData(ExporterCountryQueryData queryData);

	public Map<String, ReportRow> getCountryVulnerabilityData(ExporterCountryQueryData queryData);

	public Map<String, ReportRow> getCountryCapacityData(ExporterCountryQueryData queryData);

	public Map<String, ReportRow> getCountryOtherData(ExporterCountryQueryData queryData);

	public Map<String, ReportRow> getCountry5YearsData(ExporterCountryQueryData queryData);

	/* ****************** */
	/* Indicator reports. */
	/* ****************** */
	public XSSFWorkbook exportIndicator_XLSX(String indicatorTypeCode, String sourceCode, Long fromYear, Long toYear, String language);

	/*
	 *  Indicator overview
	 */
	public IndicatorTypeOverview getIndicatorTypeOverviewData(ExporterIndicatorQueryData queryData);

	/**
	 * Only the rows with actual data are added in the returned result.
	 * 
	 * @param queryData
	 * @return a map of the rows we can expect in the report.
	 */
	public Map<Long, Map<String, IndicatorData>> getIndicatorDataData(ExporterIndicatorQueryData queryData);

}
