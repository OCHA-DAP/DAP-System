package org.ocha.dap.persistence.dao.currateddata;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.dap.importer.TimeRange;
import org.ocha.dap.persistence.entity.ImportFromCKAN;
import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.Indicator;
import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.curateddata.Source;
import org.springframework.transaction.annotation.Transactional;

public class IndicatorDAOImpl implements IndicatorDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Indicator> listLastIndicators(final int limit) {
		final TypedQuery<Indicator> query = em.createQuery("SELECT i FROM Indicator i ORDER BY i.id", Indicator.class).setMaxResults(limit);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void addIndicator(final Source source, final Entity entity, final IndicatorType type, final Date start, final Date end, final Periodicity periodicity, final boolean numeric,
			final String value, final String initialValue, final ImportFromCKAN importFromCKAN) {

		final Indicator indicator = new Indicator();
		indicator.setSource(source);
		indicator.setEntity(entity);
		indicator.setType(type);
		indicator.setStart(start);
		indicator.setEnd(end);
		indicator.setPeriodicity(periodicity);
		indicator.setNumeric(numeric);
		indicator.setValue(value);
		indicator.setInitialValue(initialValue);
		indicator.setImportFromCKAN(importFromCKAN);
		em.persist(indicator);

	}

	@Override
	public List<Indicator> listIndicatorsByPeriodicityAndSourceAndIndicatorType(final Periodicity periodicity, final String sourceCode, final String indicatorTypeCode, final List<String> countryCodes) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT i FROM Indicator i WHERE i.periodicity = :periodicity AND i.source.code = :source AND i.type.code = :indicatorType ");
		if (countryCodes != null && !countryCodes.isEmpty()) {
			sb.append(" AND i.entity.type.code = 'country' AND i.entity.code IN :countryCodes ");
		}
		sb.append(" ORDER BY i.start, i.entity.type.code, i.entity.code");
		final TypedQuery<Indicator> query = em.createQuery(sb.toString(), Indicator.class).setParameter("periodicity", periodicity).setParameter("source", sourceCode)
				.setParameter("indicatorType", indicatorTypeCode);

		if (countryCodes != null && !countryCodes.isEmpty()) {
			query.setParameter("countryCodes", countryCodes);
		}

		return query.getResultList();
	}

	@Override
	public List<Indicator> listIndicatorsByPeriodicityAndEntityAndIndicatorType(final Periodicity periodicity, final String entityType, final String entityCode, final String indicatorTypeCode) {
		final TypedQuery<Indicator> query = em
				.createQuery(
						"SELECT i FROM Indicator i WHERE i.periodicity = :periodicity AND i.entity.type.code = :entityType AND i.entity.code = :entityCode AND i.type.code = :indicatorType ORDER BY i.start, i.source.code",
						Indicator.class).setParameter("periodicity", periodicity).setParameter("entityType", entityType).setParameter("entityCode", entityCode)
				.setParameter("indicatorType", indicatorTypeCode);

		return query.getResultList();
	}

	@Override
	public List<Indicator> listIndicatorsByYearAndSourceAndIndicatorType(final int year, final String sourceCode, final String indicatorTypeCode) {
		final TimeRange timeRange = new TimeRange(year);
		final TypedQuery<Indicator> query = em
				.createQuery(
						"SELECT i FROM Indicator i WHERE i.periodicity = :periodicity AND i.source.code = :source AND i.type.code = :indicatorType AND i.start = :start ORDER BY i.entity.type.code, i.entity.code",
						Indicator.class).setParameter("periodicity", Periodicity.YEAR).setParameter("start", timeRange.getStart()).setParameter("source", sourceCode)
				.setParameter("indicatorType", indicatorTypeCode);

		return query.getResultList();
	}

	@Override
	public List<Indicator> listIndicatorsByYearAndSourceAndIndicatorTypes(final int year, final String sourceCode, final List<String> indicatorTypeCodes) {
		final TimeRange timeRange = new TimeRange(year);
		final TypedQuery<Indicator> query = em
				.createQuery(
						"SELECT i FROM Indicator i WHERE i.periodicity = :periodicity AND i.source.code = :source AND i.type.code IN :indicatorTypes AND i.start = :start ORDER BY i.entity.type.code, i.entity.code, i.type.code",
						Indicator.class).setParameter("periodicity", Periodicity.YEAR).setParameter("start", timeRange.getStart()).setParameter("source", sourceCode)
				.setParameter("indicatorTypes", indicatorTypeCodes);

		return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteAllIndicators() {
		em.createQuery("DELETE FROM Indicator").executeUpdate();
	}

	@Override
	@Transactional
	public void deleteAllIndicatorsFromImport(final long importId) {
		em.createQuery("DELETE FROM Indicator i WHERE i.importFromCKAN.id = :importId").setParameter("importId", importId).executeUpdate();

	}

	@Override
	public List<String> getExistingSourcesCodesForYearAndIndicatorType(final int year, final String indicatorTypeCode) {
		final TimeRange timerange = new TimeRange(Integer.valueOf(year).toString());
		final TypedQuery<String> query = em
				.createQuery("SELECT DISTINCT(i.source.code) FROM Indicator i Where i.start = :start AND i.end = :end AND i.periodicity = :periodicity AND i.type.code = :indicatorTypeCode",
						String.class).setParameter("start", timerange.getStart()).setParameter("end", timerange.getEnd()).setParameter("periodicity", timerange.getPeriodicity())
				.setParameter("indicatorTypeCode", indicatorTypeCode);
		return query.getResultList();
	}

	@Override
	public List<String> getExistingSourcesCodesForIndicatorType(final String indicatorTypeCode) {
		final TypedQuery<String> query = em.createQuery("SELECT DISTINCT(i.source.code) FROM Indicator i Where i.type.code = :indicatorTypeCode", String.class).setParameter("indicatorTypeCode",
				indicatorTypeCode);
		return query.getResultList();
	}

}
