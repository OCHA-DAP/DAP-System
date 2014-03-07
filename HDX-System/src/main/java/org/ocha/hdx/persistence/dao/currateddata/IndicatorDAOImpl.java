package org.ocha.hdx.persistence.dao.currateddata;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.joda.time.DateTime;
import org.ocha.hdx.importer.TimeRange;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.springframework.transaction.annotation.Transactional;

public class IndicatorDAOImpl implements IndicatorDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Indicator> listLastIndicators(final int limit) {
		final TypedQuery<Indicator> query = em.createQuery("SELECT i FROM Indicator i ORDER BY i.id DESC", Indicator.class).setMaxResults(limit);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void createIndicator(final Source source, final Entity entity, final IndicatorType type, final Date start, final Date end, final Periodicity periodicity, final IndicatorValue value,
			final String initialValue, final String sourceLink, final ImportFromCKAN importFromCKAN) {

		final Indicator indicator = new Indicator();
		indicator.setSource(source);
		indicator.setEntity(entity);
		indicator.setType(type);
		indicator.setStart(start);
		indicator.setEnd(end);
		indicator.setPeriodicity(periodicity);
		indicator.setValue(value);
		indicator.setInitialValue(initialValue);
		indicator.setSourceLink(sourceLink);
		indicator.setImportFromCKAN(importFromCKAN);
		em.persist(indicator);
	}

	@Override
	public List<Indicator> listIndicatorsByPeriodicityAndSourceAndIndicatorType(final Periodicity periodicity, final String sourceCode, final String indicatorTypeCode, final List<String> countryCodes) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT i FROM Indicator i WHERE i.periodicity = :periodicity AND i.source.code = :source AND i.type.code = :indicatorType ");
		if ((countryCodes != null) && !countryCodes.isEmpty()) {
			sb.append(" AND i.entity.type.code = 'country' AND i.entity.code IN :countryCodes ");
		}
		sb.append(" ORDER BY i.start, i.entity.type.code, i.entity.code");
		final TypedQuery<Indicator> query = em.createQuery(sb.toString(), Indicator.class).setParameter("periodicity", periodicity).setParameter("source", sourceCode)
				.setParameter("indicatorType", indicatorTypeCode);

		if ((countryCodes != null) && !countryCodes.isEmpty()) {
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
	public List<Indicator> listIndicatorsByYearAndSourceAndIndicatorType(final int year, final String sourceCode, final String indicatorTypeCode, final List<String> countryCodes) {
		final TimeRange timeRange = new TimeRange(year);
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT i FROM Indicator i WHERE i.periodicity = :periodicity AND i.source.code = :source AND i.type.code = :indicatorType AND i.start = :start ");
		if ((countryCodes != null) && !countryCodes.isEmpty()) {
			sb.append(" AND i.entity.type.code = 'country' AND i.entity.code IN :countryCodes ");
		}
		sb.append(" ORDER BY i.entity.type.code, i.entity.code ");
		final TypedQuery<Indicator> query = em.createQuery(sb.toString(), Indicator.class).setParameter("periodicity", Periodicity.YEAR).setParameter("start", timeRange.getStart())
				.setParameter("source", sourceCode).setParameter("indicatorType", indicatorTypeCode);

		if ((countryCodes != null) && !countryCodes.isEmpty()) {
			query.setParameter("countryCodes", countryCodes);
		}
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

	/*
	 * // The beautiful query : SELECT it.code AS indicatorId, i.start_time AS startTime, itt.default_value AS indicatorType, i.date_value AS dateValue, i.datetime_value AS dateTimeValue,
	 * i.number_value AS numberValue, i.string_value AS stringValue, ivt.default_value AS textValue, ifc.timestamp AS lastUpdated, st.default_value AS source FROM hdx_indicator i LEFT OUTER JOIN text
	 * ivt ON i.text_id = ivt.id, indicator_type it, text itt, entity e, import_from_ckan ifc, source s, text st WHERE it.code = '_emdat:total_affected' AND i.type_id = it.id AND it.text_id = itt.id
	 * AND i.entity_id = e.id AND e.entity_type_id = 1 AND e.code = 'COL' AND i.import_from_ckan_id = ifc.id AND i.source_id = s.id AND s.text_id = st.id ORDER BY i.start_time DESC LIMIT 1 ; List of
	 * indicators for a country overview.
	 */
	@Override
	public List<Object[]> listIndicatorsForCountryOverview(final String countryCode, final String languageCode) {
		// List of indicators relevant for country overview. TODO Externalize ?
		final String[] indicatorsList = new String[] { "CD010", "CD030", "CD050", "CD070", "CD080", "CD090", "CG020", "CG030", "CG060", "CG070", "CG080", "CG100", "CG120", "CG140", "CG150", "CG260",
				"CG290", "_m49-name", "_unterm:ISO Country alpha-2-code" };

		final List<Object[]> result = new ArrayList<Object[]>();
		for (final String indicator : indicatorsList) {
			// TODO i18n
			final Query query = em
					.createQuery(
							"SELECT i.type.code, i.type.name.defaultValue, i.value, i.importFromCKAN.timestamp, i.source.name.defaultValue from Indicator i WHERE i.entity.type.code = :isCountry AND i.type.code = :code AND i.entity.code = :countryCode")
					.setParameter("isCountry", "country").setParameter("code", indicator).setParameter("countryCode", countryCode).setMaxResults(1);
			Object[] queryResult = null;
			try {
				queryResult = (Object[]) query.getSingleResult();
			} catch (final NoResultException e) {
				// It is possible that no value exists for the given indicator.
				// So we just put the indicator in the result.
				queryResult = new Object[] { indicator };
			}
			result.add(queryResult);
		}
		return result;
	}

	/*
	 * List of indicators for a country crisis history for a given year.
	 */
	@Override
	public Map<Integer, List<Object[]>> listIndicatorsForCountryCrisisHistory(final String countryCode, final int fromYear, final int toYear, final String languageCode) {
		// List of indicators relevant for country crisis history. Each indicator type has an associated source here TODO Externalize ?
		final String[] indicatorsList = new String[] { "CH070", "CH080", "CH090", "CH100" };
		final String[] sourcesList = new String[] { "emdat", "emdat", "emdat", "emdat" };

		return listIndicatorsForCountry(countryCode, fromYear, toYear, indicatorsList, sourcesList, languageCode);
	}

	@Override
	public Map<Integer, List<Object[]>> listIndicatorsForCountryVulnerability(final String countryCode, final int fromYear, final int toYear, final String languageCode) {
		// List of indicators relevant for country crisis history. Each indicator type has an associated source here TODO Externalize ?
		final String[] indicatorsList = new String[] { "PVE130", "PVF020", "PVH140", "PVL040" };
		final String[] sourcesList = new String[] { "mdgs", "faostat3", "mdgs", "world-bank" };

		return listIndicatorsForCountry(countryCode, fromYear, toYear, indicatorsList, sourcesList, languageCode);
	}

	private Map<Integer, List<Object[]>> listIndicatorsForCountry(final String countryCode, final int fromYear, final int toYear, final String[] indicatorsList, final String[] sourcesList,
			final String languageCode) {
		final int fromYear_ = fromYear;
		final int toYear_ = toYear;

		// Convention : if fromYear = 0 => we take the earliest data available
		if (0 == fromYear) {
			// TODO find the earliest year available
			// fromYear_ = ...;
		}

		// Convention : if toYear = 0 => we take the latest data available
		if (0 == toYear) {
			// TODO find the latest year available
			// toYear_ = ...;
		}

		final Map<Integer, List<Object[]>> result = new HashMap<>();
		for (int year = fromYear_; year <= toYear_; year++) {
			final DateTime yearAsDate = new DateTime(year, 1, 1, 0, 0);
			final List<Object[]> yearResult = new ArrayList<Object[]>();
			for (int i = 0; i < indicatorsList.length; i++) {
				final String indicator = indicatorsList[i];
				final String sourceCode = sourcesList[i];
				// FIXME this should be possible to run only one query per indicator, with several results, one per year. Only if there is one and only one result per year, no updates
				final Query query = em
						.createQuery(
								"SELECT i.type.code, i.type.name.defaultValue, i.type.unit.name.defaultValue, i.value, i.importFromCKAN.timestamp, i.source.name.defaultValue from Indicator i WHERE i.entity.type.code = :isCountry AND i.type.code = :code AND i.source.code = :sourceCode "
										+ "AND i.entity.code = :countryCode AND i.periodicity = :periodicity and i.start = :start").setParameter("isCountry", "country")
						.setParameter("code", indicator).setParameter("sourceCode", sourceCode).setParameter("countryCode", countryCode).setParameter("periodicity", Periodicity.YEAR)
						.setParameter("start", yearAsDate.toDate());
				Object[] queryResult = null;
				try {
					queryResult = (Object[]) query.getSingleResult();
				} catch (final NoResultException e) {
					// It is possible that no value exists for the given indicator.
					// So we just put the indicator in the result.
					queryResult = new Object[] { indicator };
				}
				yearResult.add(queryResult);
			}
			result.put(year, yearResult);
		}
		return result;
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
	@Transactional
	public void deleteIndicator(final long indicatorId) {
		em.createQuery("DELETE FROM Indicator i WHERE i.id = :indicatorId").setParameter("indicatorId", indicatorId).executeUpdate();

	}

	/**
	 * @author Dan TODO: It could delete all indicators in one transaction
	 */
	@Override
	@Transactional
	public void deleteIndicators(final List<Long> indList) {
		for (final Long ind : indList) {
			deleteIndicator(ind);
		}
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
