package org.ocha.dap.persistence.dao.currateddata;

import java.util.Date;
import java.util.List;

import org.ocha.dap.persistence.entity.ImportFromCKAN;
import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.Indicator;
import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.curateddata.Source;

public interface IndicatorDAO {

	public List<Indicator> listLastIndicators(final int limit);

	public void addIndicator(final Source source, final Entity entity, final IndicatorType type, final Date start, final Date end, final Periodicity periodicity, final boolean numeric,
			final String value, final String initialValue, final ImportFromCKAN importFromCKAN);

	public List<Indicator> listIndicatorsByPeriodicityAndSourceAndIndicatorType(final Periodicity periodicity, final String sourceCode, final String indicatorTypeCode);

	public List<Indicator> listIndicatorsByPeriodicityAndEntityAndIndicatorType(final Periodicity periodicity, final String entityType, final String entityCode, final String indicatorTypeCode);

	/**
	 * periodicity is implicitely YEAR
	 * 
	 * @param year
	 * @param sourceCode
	 * @param indicatorTypeCode
	 */
	public List<Indicator> listIndicatorsByYearAndSourceAndIndicatorType(final int year, final String sourceCode, final String indicatorTypeCode);

	/**
	 * very likely to be used by the unit tests only
	 */
	public void deleteAllIndicators();

	public void deleteAllIndicatorsFromImport(long importId);

}
