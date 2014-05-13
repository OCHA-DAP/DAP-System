package org.ocha.hdx.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.country.ExporterCountryQueryData;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.exporter.indicator.ExporterIndicatorMetadataQueryData;
import org.ocha.hdx.exporter.indicator.ExporterIndicatorQueryData;
import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
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

	public List<Object[]> listIndicatorsForCountryOverview(final String countryCode, final String languageCode, final String[] indicatorsList);

	public IndicatorType getIndicatorTypeByCode(final String code);

	public Source getSourceByCode(final String code);

	public List<DataSerieMetadata> getMetadataForDataSerie(final DataSerie dataSerie);

	public Map<String, Timestamp> getMinMaxDatesForDataSeries(final DataSerie dataSeries);

	/* **************** */
	/* Country reports. */
	/* **************** */

	// SW 
	
	public XSSFWorkbook exportCountry_XLSX(String countryCode, Integer fromYear, Integer toYear, String language) throws Exception;

	public File exportCountry_CSV(String countryCode, Integer fromYear, Integer toYear, String language) throws Exception;

	public File exportCountryReadMe_TXT(String countryCode, String language) throws Exception;

	// RW 

	public XSSFWorkbook exportCountryRW_XLSX(String countryCode, Integer fromYear, Integer toYear, String language) throws Exception;

	public File exportCountryRW_CSV(String countryCode, Integer fromYear, Integer toYear, String language) throws Exception;

	public File exportCountryRWReadMe_TXT(String countryCode, String language) throws Exception;

	// FTS 

	public XSSFWorkbook exportCountryFTS_XLSX(String countryCode, Integer fromYear, Integer toYear, String language) throws Exception;

	public File exportCountryFTS_CSV(String countryCode, Integer fromYear, Integer toYear, String language) throws Exception;

	public File exportCountryFTSReadMe_TXT(String countryCode, String language) throws Exception;

	/*
	 * Data getters for country reports.
	 * Only the rows with actual data are added in the returned result.
	 */
	public List<Object[]> getCountryOverviewData(final ExporterCountryQueryData queryData, final String[] indicatorsList);
	public Map<String, ReportRow> getCountryData(ExporterCountryQueryData queryData, final List<DataSerie> dataSeries);
	public Map<String, ReportRow> getCountryData(ExporterCountryQueryData queryData, final Periodicity periodicity, final List<DataSerie> dataSeries);

	
	
	/* ****************** */
	/* Indicator reports. */
	/* ****************** */
	
	// SW
	
	public XSSFWorkbook exportIndicator_XLSX(String indicatorTypeCode, String sourceCode, Long fromYear, Long toYear, String language) throws Exception;

	public File exportIndicator_CSV(String indicatorTypeCode, String sourceCode, Long fromYear, Long toYear, String language) throws Exception;

	public File exportIndicatorReadMe_TXT(String indicatorTypeCode, String sourceCode, String language) throws Exception;
	
	// RW

	public XSSFWorkbook exportIndicatorRW_XLSX(Long fromYear, Long toYear, String language) throws Exception;

	public File exportIndicatorRWReadMe_TXT(String language) throws Exception;

	// FTS

	public XSSFWorkbook exportIndicatorFTS_XLSX(Long fromYear, Long toYear, String language) throws Exception;

	public File exportIndicatorFTSReadMe_TXT(String language) throws Exception;

	/*
	 * Data getters for Indicator reports.
	 * Only the rows with actual data are added in the returned result.
	 */
	public IndicatorTypeOverview getIndicatorTypeOverviewData(ExporterIndicatorQueryData queryData);
	public Map<Long, Map<String, IndicatorData>> getIndicatorDataData(ExporterIndicatorQueryData queryData);

	
	
	/* ***************** */
	/* Metadata reports. */
	/* ***************** */
	public File exportIndicatorAllMetadata_CSV(String language) throws Exception;
	public File exportIndicatorMetadata_CSV(String indicatorTypeCode, String language) throws Exception;

	/*
	 * Data getters for Metadata reports.
	 * Only the rows with actual data are added in the returned result.
	 */
	public List<DataSerieMetadata> getIndicatorMetadataData(ExporterIndicatorMetadataQueryData queryData);
}
