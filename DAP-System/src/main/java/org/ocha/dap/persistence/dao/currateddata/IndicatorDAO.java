package org.ocha.dap.persistence.dao.currateddata;

import java.util.Date;
import java.util.List;

import org.ocha.dap.persistence.entity.ImportFromCKAN;
import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.Indicator;
import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.curateddata.IndicatorValue;
import org.ocha.dap.persistence.entity.curateddata.Source;

public interface IndicatorDAO {

	public List<Indicator> listLastIndicators(final int limit);

	public void addIndicator(final Source source, final Entity entity, final IndicatorType type, final Date start, final Date end, final Periodicity periodicity, final IndicatorValue value,
			final String initialValue, final String sourceLink, final ImportFromCKAN importFromCKAN);

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

	/**
	 * very likely to be used by the unit tests only
	 */
	public void deleteAllIndicators();

	public void deleteAllIndicatorsFromImport(long importId);

	public void deleteIndicator(final long indicatorId);

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

}
