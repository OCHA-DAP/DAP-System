package org.ocha.hdx.persistence.dao.currateddata;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.model.api2util.IntermediaryIndicatorValue;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorImportConfig;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.persistence.entity.curateddata.Source;

public interface IndicatorDAO {

	/**
	 * Create an indicator.
	 * 
	 * @param source
	 * @param entity
	 * @param type
	 * @param start
	 * @param end
	 * @param periodicity
	 * @param value
	 * @param initialValue
	 * @param validationStatus
	 * @param sourceLink
	 * @param importFromCKAN
	 */
	public void createIndicator(final Source source, final Entity entity, final IndicatorType type, final Date start, final Date end, final Periodicity periodicity, final IndicatorValue value,
			final String initialValue, final ValidationStatus validationStatus, final String sourceLink, final ImportFromCKAN importFromCKAN);

	/**
	 * Create an indicator.
	 * 
	 * @param source
	 * @param entity
	 * @param type
	 * @param start
	 * @param end
	 * @param periodicity
	 * @param value
	 * @param indicatorImportConfig
	 * @param sourceLink
	 * @param importFromCKAN
	 */
	public void createIndicator(final Source source, final Entity entity, final IndicatorType type, final Date start, final Date end, final Periodicity periodicity, final IndicatorValue value,
			final IndicatorImportConfig indicatorImportConfig, final String sourceLink, final ImportFromCKAN importFromCKAN);

	/**
	 * 
	 * @return
	 */
	public Map<Long, Long> countIndicatorsByImport();

	/**
	 * 
	 * @param countryCodes
	 *            optional filter to only get some countries (cannot deal other entityTypes yet)
	 */
	public List<Indicator> listIndicatorsByPeriodicityAndSourceAndIndicatorType(final Periodicity periodicity, final String sourceCode, final String indicatorTypeCode, final List<String> countryCodes);

	public List<Indicator> listIndicatorsByPeriodicityAndEntityAndIndicatorType(final Periodicity periodicity, final String entityType, final String entityCode, final String indicatorTypeCode);

	/**
	 * periodicity is implicitely YEAR
	 * 
	 * @param year
	 * @param sourceCode
	 * @param indicatorTypeCode
	 */
	public List<Indicator> listIndicatorsByYearAndSourceAndIndicatorType(final int year, final String sourceCode, final String indicatorTypeCode, final List<String> countryCodes);

	/**
	 * periodicity is implicitely YEAR
	 * 
	 * @param year
	 * @param sourceCode
	 * @param indicatorTypeCode
	 */
	public List<Indicator> listIndicatorsByYearAndSourceAndIndicatorTypes(final int year, final String sourceCode, final List<String> indicatorTypeCodes);

	public List<Indicator> listLastIndicators(final int limit);

	/**
	 * Indicators for the country - overview.
	 * 
	 * @param countryCode
	 * @param languageCode
	 * @param indicatorsList
	 */
	public List<Object[]> listIndicatorsForCountryOverview(String countryCode, String languageCode, String[] indicatorsList);

	/**
	 * Generic method to get indicators for a given country.
	 * 
	 * @param countryCode
	 * @param fromYear
	 * @param toYear
	 * @param dataSeries
	 * @param languageCode
	 * @return
	 */
	Map<Integer, List<Object[]>> listIndicatorsForCountry(String countryCode, int fromYear, int toYear, String languageCode, List<DataSerie> dataSeries);

	/**
	 * Generic method to get indicators for a given country.
	 * 
	 * @param countryCode
	 * @param fromYear
	 * @param toYear
	 * @param dataSeries
	 * @param languageCode
	 * @param periodicity
	 * @return
	 */
	Map<Integer, List<Object[]>> listIndicatorsForCountry(String countryCode, int fromYear, int toYear, String languageCode, Periodicity periodicity, List<DataSerie> dataSeries);

	/**
	 * very likely to be used by the unit tests only
	 */
	public void deleteAllIndicators();

	public void deleteAllIndicatorsFromImport(long importId);

	public void deleteIndicator(final long indicatorId);

	public void deleteIndicators(final List<Long> indList);

	/**
	 * Based on the existing indicators
	 * 
	 * @param year
	 * @param indicatorTypeCode
	 * 
	 * @return the list of sources for which there is ar least one matching record in Indicators (Year, IndicatorType)
	 */
	public List<String> getExistingSourcesCodesForYearAndIndicatorType(final int year, final String indicatorTypeCode);

	/**
	 * Based on the existing indicators
	 * 
	 * @param indicatorTypeCode
	 * 
	 * @return the list of sources for which there is ar least one matching record in Indicators (IndicatorType)
	 */
	public List<String> getExistingSourcesCodesForIndicatorType(final String indicatorTypeCode);

	/**
	 * Get the min and max dates for a given country, and a set of indicator-source couples.
	 * 
	 * @param countryCode
	 *            The country code
	 * @param indicatorsList
	 *            The list of indicators
	 * @param sourcesList
	 *            The list of sources
	 * @return A map with ("MIN" => the earliest date available for this country and at least one of the indicator-source couples) and ("MAX" => the latest date available for this country and at least
	 *         one of the indicator-source couples)
	 */
	public Map<String, Integer> getMinMaxDatesForCountryIndicators(String countryCode, final List<DataSerie> dataSeries);

	/**
	 * For a given data series, find the earliest and latest date of information available.
	 * 
	 * @param dataSeries
	 *            The data series to consider
	 * @return A map with ("MIN" => the earliest date available for this data series) and ("MAX" => the latest date available for this data series)
	 */
	public Map<String, Timestamp> getMinMaxDatesForDataSeries(DataSerie dataSeries);

	/**
	 * Data for indicator-centric overview.
	 * 
	 * @param indicatorTypeCode
	 * @param sourceCode
	 * @param languageCode
	 * @return
	 */
	public Object[] getIndicatorTypeOverview(String indicatorTypeCode, String sourceCode, String languageCode);

	/**
	 * 
	 * @param indicatorTypeCodes
	 * @param sourceCodes
	 * @param entityCodes
	 * @param startYear
	 * @param endYear
	 * @param startPosition
	 *            first item is 0
	 * @param maxResult
	 * @param lang
	 *            this should not be null
	 * @return list of indicators that have passed the filter
	 */
	public List<IntermediaryIndicatorValue> listIndicatorsByCriteria(List<String> indicatorTypeCodes, List<String> sourceCodes, List<String> entityCodes, Integer startYear, Integer endYear,
			Integer startPosition, Integer maxResult, String lang);

	/**
	 * 
	 * @param indicatorTypeCodes
	 * @param sourceCodes
	 * @param entityCodes
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	public Long countIndicatorsByCriteria(List<String> indicatorTypeCodes, List<String> sourceCodes, List<String> entityCodes, Integer startYear, Integer endYear);

	public Integer latestYearForIndicatorsByCriteria(List<String> indicatorTypeCodes, List<String> sourceCodes, List<String> entityCodes);

}
