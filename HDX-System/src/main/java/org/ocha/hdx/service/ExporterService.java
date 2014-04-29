package org.ocha.hdx.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.country.ExporterCountryQueryData;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.exporter.indicator.ExporterIndicatorMetadataQueryData;
import org.ocha.hdx.exporter.indicator.ExporterIndicatorQueryData;
import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata;
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

	public Source getSourceByCode(final String code);

	public List<DataSerieMetadata> getMetadataForDataSerie(final DataSerie dataSerie);

	/* **************** */
	/* Country reports. */
	/* **************** */
	public XSSFWorkbook exportCountry_XLSX(String countryCode, Integer fromYear, Integer toYear, String language) throws Exception;

	public File exportCountry_CSV(String countryCode, Integer fromYear, Integer toYear, String language) throws IOException, Exception;

	public File exportCountryReadMe_TXT(String countryCode, String language) throws Exception;

	/*
	 * Country overview
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
	public XSSFWorkbook exportIndicator_XLSX(String indicatorTypeCode, String sourceCode, Long fromYear, Long toYear, String language) throws Exception;

	/*
	 * Indicator overview
	 */
	public IndicatorTypeOverview getIndicatorTypeOverviewData(ExporterIndicatorQueryData queryData);

	/**
	 * Only the rows with actual data are added in the returned result.
	 * 
	 * @param queryData
	 * @return a map of the rows we can expect in the report.
	 */
	public Map<Long, Map<String, IndicatorData>> getIndicatorDataData(ExporterIndicatorQueryData queryData);

	/*
	 * Indicator metadata
	 */
	public File exportIndicatorMetadata_CSV(String indicatorTypeCode, String language) throws Exception;

	public List<DataSerieMetadata> getIndicatorMetadataData(ExporterIndicatorMetadataQueryData queryData);

}
