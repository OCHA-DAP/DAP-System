package org.ocha.hdx.persistence.dao.currateddata;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.importer.TimeRange;
import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.model.api2util.IntermediaryIndicatorValue;
import org.ocha.hdx.model.api2util.RequestParamsWrapper.SortingOption;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorImportConfig;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class IndicatorDAOImpl implements IndicatorDAO {

	private static Logger logger = LoggerFactory.getLogger(IndicatorDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	/* *************** */
	/* CRUD operations */
	/* *************** */

	/**
	 * Create indicator.
	 */
	@Override
	@Transactional
	public void createIndicator(final Source source, final Entity entity, final IndicatorType type, final Date start, final Date end, final Periodicity periodicity, final IndicatorValue value,
			final String initialValue, final ValidationStatus validationStatus, final String sourceLink, final ImportFromCKAN importFromCKAN) {
		this.createIndicator(source, entity, type, start, end, periodicity, value, new IndicatorImportConfig(initialValue, validationStatus), sourceLink, importFromCKAN);
	}

	/**
	 * Create indicator.
	 */
	@Override
	@Transactional
	public void createIndicator(final Source source, final Entity entity, final IndicatorType type, final Date start, final Date end, final Periodicity periodicity, final IndicatorValue value,
			final IndicatorImportConfig indicatorImportConfig, final String sourceLink, final ImportFromCKAN importFromCKAN) {

		final Indicator indicator = new Indicator();
		indicator.setSource(source);
		indicator.setEntity(entity);
		indicator.setType(type);
		indicator.setStart(start);
		indicator.setEnd(end);
		indicator.setPeriodicity(periodicity);
		indicator.setValue(value);
		indicator.setIndicatorImportConfig(indicatorImportConfig);
		indicator.setSourceLink(sourceLink);
		indicator.setImportFromCKAN(importFromCKAN);
		this.em.persist(indicator);
	}

	/**
	 * Delete an indicator.
	 */
	@Override
	@Transactional
	public void deleteIndicator(final long indicatorId) {
		this.em.createQuery("DELETE FROM Indicator i WHERE i.id = :indicatorId").setParameter("indicatorId", indicatorId).executeUpdate();

	}

	/**
	 * Delete some indicators.
	 *
	 * @author Dan TODO: It could delete all indicators in one transaction
	 */
	@Override
	@Transactional
	public void deleteIndicators(final List<Long> indList) {
		for (final Long ind : indList) {
			this.deleteIndicator(ind);
		}
	}

	/**
	 * Delete all indicators.
	 */
	@Override
	@Transactional
	public void deleteAllIndicators() {
		this.em.createQuery("DELETE FROM Indicator").executeUpdate();
	}

	/**
	 * Delete all indicators for a given import.
	 */
	@Override
	@Transactional
	public void deleteAllIndicatorsFromImport(final long importId) {
		this.em.createQuery("DELETE FROM Indicator i WHERE i.importFromCKAN.id = :importId").setParameter("importId", importId).executeUpdate();

	}

	/* Lists */

	@Override
	public List<Indicator> listLastIndicators(final int limit) {
		final TypedQuery<Indicator> query = this.em.createQuery("SELECT i FROM Indicator i ORDER BY i.id DESC", Indicator.class).setMaxResults(limit);
		return query.getResultList();
	}

	@Override
	public List<Indicator> listIndicatorsByPeriodicityAndSourceAndIndicatorType(final Periodicity periodicity, final String sourceCode, final String indicatorTypeCode, final List<String> countryCodes) {
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT i FROM Indicator i WHERE i.periodicity = :periodicity AND i.source.code = :source AND i.type.code = :indicatorType ");
		if ((countryCodes != null) && !countryCodes.isEmpty()) {
			sb.append(" AND i.entity.type.code = 'country' AND i.entity.code IN :countryCodes ");
		}
		sb.append(" ORDER BY i.start, i.entity.type.code, i.entity.code");
		final TypedQuery<Indicator> query = this.em.createQuery(sb.toString(), Indicator.class).setParameter("periodicity", periodicity).setParameter("source", sourceCode)
				.setParameter("indicatorType", indicatorTypeCode);

		if ((countryCodes != null) && !countryCodes.isEmpty()) {
			query.setParameter("countryCodes", countryCodes);
		}

		return query.getResultList();
	}

	@Override
	public List<Indicator> listIndicatorsByPeriodicityAndEntityAndIndicatorType(final Periodicity periodicity, final String entityType, final String entityCode, final String indicatorTypeCode) {
		final TypedQuery<Indicator> query = this.em
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
		final TypedQuery<Indicator> query = this.em.createQuery(sb.toString(), Indicator.class).setParameter("periodicity", Periodicity.YEAR).setParameter("start", timeRange.getStart())
				.setParameter("source", sourceCode).setParameter("indicatorType", indicatorTypeCode);

		if ((countryCodes != null) && !countryCodes.isEmpty()) {
			query.setParameter("countryCodes", countryCodes);
		}
		return query.getResultList();
	}

	@Override
	public List<Indicator> listIndicatorsByYearAndSourceAndIndicatorTypes(final int year, final String sourceCode, final List<String> indicatorTypeCodes) {
		final TimeRange timeRange = new TimeRange(year);
		final TypedQuery<Indicator> query = this.em
				.createQuery(
						"SELECT i FROM Indicator i WHERE i.periodicity = :periodicity AND i.source.code = :source AND i.type.code IN :indicatorTypes AND i.start = :start ORDER BY i.entity.type.code, i.entity.code, i.type.code",
						Indicator.class).setParameter("periodicity", Periodicity.YEAR).setParameter("start", timeRange.getStart()).setParameter("source", sourceCode)
				.setParameter("indicatorTypes", indicatorTypeCodes);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Long, Long> countIndicatorsByImport() {
		final Map<Long, Long> result = new HashMap<Long, Long>();
		final Query query = this.em.createQuery("SELECT i.importFromCKAN.id, COUNT(i) from Indicator i GROUP BY i.importFromCKAN");
		List<Object[]> queryResult = null;
		try {
			queryResult = query.getResultList();
		} catch (final NoResultException e) {
			// It is possible that no value exists.
		}
		if(null != queryResult) {
			for (final Object[] object : queryResult) {
				result.put((Long)object[0], (Long)object[1]);
			}
		}
		return result;
	}

	/* ******* */
	/* Reports */
	/* ******* */

	/* Country reports */

	/*
	 * List of indicators for a country - overview.
	 */
	@Override
	@Deprecated
	public List<Object[]> listIndicatorsForCountryOverview(final String countryCode, final String languageCode, final String[] indicatorsList) {
		final List<Object[]> result = new ArrayList<Object[]>();
		for (final String indicator : indicatorsList) {
			// TODO i18n
			final Query query = this.em
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
	 * Generic method to get indicators for a given country.
	 */
	@Override
	public Map<Integer, List<Object[]>> listIndicatorsForCountry(final String countryCode, final int fromYear, final int toYear, final String languageCode, final List<DataSerie> dataSeries) {
		return this.listIndicatorsForCountry(countryCode, fromYear, toYear, languageCode, Periodicity.YEAR, dataSeries);
	}

	@Override
	public Map<Integer, List<Object[]>> listIndicatorsForCountry(final String countryCode, final int fromYear, final int toYear, final String languageCode, final Periodicity periodicity,
			final List<DataSerie> dataSeries) {
		int fromYear_ = fromYear;
		int toYear_ = toYear;

		// Convention : if fromYear = 0 => we take the earliest data available
		Map<String, Integer> minMax = null;
		if (0 == fromYear) {
			// Find the earliest year available
			minMax = this.getMinMaxDatesForCountryIndicators(countryCode, dataSeries);
			fromYear_ = minMax.get("MIN");
		}

		// Convention : if toYear = 0 => we take the latest data available
		if (0 == toYear) {
			// Find the latest year available
			if (null == minMax) {
				minMax = this.getMinMaxDatesForCountryIndicators(countryCode, dataSeries);
			}
			toYear_ = minMax.get("MAX");
		}

		final Map<Integer, List<Object[]>> result = new HashMap<>();
		for (int year = fromYear_; year <= toYear_; year++) {
			final DateTime yearAsDate = new DateTime(year, 1, 1, 0, 0);
			final List<Object[]> yearResult = new ArrayList<Object[]>();
			for (int i = 0; i < dataSeries.size(); i++) {

				final DataSerie dataSerie = dataSeries.get(i);
				final String indicatorCode = dataSerie.getIndicatorCode();
				final String sourceCode = dataSerie.getSourceCode();

				// FIXME this should be possible to run only one query per indicator, with several results, one per year. Only if there is one and only one result per year, no updates
				final Query query = this.em
						.createQuery(
								"SELECT i.type.code, i.type.name.defaultValue, i.type.unit.name.defaultValue, i.value, i.importFromCKAN.timestamp, i.source.name.defaultValue, i.source.code from Indicator i WHERE i.entity.type.code = :isCountry AND i.type.code = :code AND i.source.code = :sourceCode "
										+ "AND i.entity.code = :countryCode AND i.periodicity = :periodicity and i.start = :start").setParameter("isCountry", "country")
						.setParameter("code", indicatorCode).setParameter("sourceCode", sourceCode).setParameter("countryCode", countryCode).setParameter("periodicity", periodicity)
						.setParameter("start", yearAsDate.toDate());
				Object[] queryResult = null;
				try {
					queryResult = (Object[]) query.getSingleResult();
				} catch (final NoResultException e) {
					// It is possible that no value exists for the given indicator.
					// So we just put the indicator in the result.
					queryResult = new Object[] { indicatorCode };
				}
				yearResult.add(queryResult);
			}
			result.put(year, yearResult);
		}
		return result;
	}

	/*
	 * For a given country and list of indicators, find the earliest and latest years of information available.
	 */
	@Override
	public Map<String, Integer> getMinMaxDatesForCountryIndicators(final String countryCode, final List<DataSerie> dataSeries) {
		String query_ = "SELECT MIN(i.start), MAX(i.start) from Indicator i WHERE i.entity.type.code = :isCountry AND i.entity.code = :countryCode AND i.periodicity = :periodicity AND (";
		for (int i = 0; i < dataSeries.size(); i++) {
			query_ += "(i.source.code = :sourceCode_" + i + " AND i.type.code = :code_" + i + ")";
			if (i < (dataSeries.size() - 1)) {
				query_ += " OR ";
			}
		}
		query_ += ")";
		logger.debug("Min-max dates query : " + query_);

		final Query query = this.em.createQuery(query_);

		query.setParameter("isCountry", "country");
		query.setParameter("countryCode", countryCode);
		query.setParameter("periodicity", Periodicity.YEAR);

		for (int i = 0; i < dataSeries.size(); i++) {
			final DataSerie dataSerie = dataSeries.get(i);
			final String indicatorCode = dataSerie.getIndicatorCode();
			final String sourceCode = dataSerie.getSourceCode();
			query.setParameter("code_" + i, indicatorCode);
			query.setParameter("sourceCode_" + i, sourceCode);
		}
		Object[] queryResult = null;
		queryResult = (Object[]) query.getSingleResult();

		final Map<String, Integer> result = new HashMap<String, Integer>();

		final Timestamp min = (Timestamp) queryResult[0];
		final Timestamp max = (Timestamp) queryResult[1];

		final Calendar minCal = new GregorianCalendar();
		final Calendar maxCal = new GregorianCalendar();

		if (null != min) {
			minCal.setTimeInMillis(min.getTime());
			result.put("MIN", minCal.get(Calendar.YEAR));
		} else {
			result.put("MIN", 0);
		}
		if (null != max) {
			maxCal.setTimeInMillis(max.getTime());
			result.put("MAX", maxCal.get(Calendar.YEAR));
		} else {
			result.put("MAX", 0);
		}
		return result;
	}

	/*
	 * For a given data series, find the earliest and latest date of information available.
	 */
	@Override
	public Map<String, Timestamp> getMinMaxDatesForDataSeries(final DataSerie dataSeries) {
		final String query_ = "SELECT MIN(i.start), MAX(i.start) from Indicator i WHERE i.source.code = :sourceCode AND i.type.code = :indicatorTypeCode";
		final Query query = this.em.createQuery(query_);
		query.setParameter("sourceCode", dataSeries.getSourceCode());
		query.setParameter("indicatorTypeCode", dataSeries.getIndicatorCode());

		Object[] queryResult = null;
		queryResult = (Object[]) query.getSingleResult();

		final Map<String, Timestamp> result = new HashMap<String, Timestamp>();

		final Timestamp min = (Timestamp) queryResult[0];
		final Timestamp max = (Timestamp) queryResult[1];

		result.put("MIN", min);
		result.put("MAX", max);

		return result;
	}

	/* Indicator reports */

	/*
	 * Indicator - overview.
	 */
	@Override
	public Object[] getIndicatorTypeOverview(final String indicatorTypeCode, final String sourceCode, final String languageCode) {
		// TODO i18n
		final Query query = this.em.createQuery("SELECT i.type.code, i.type.name.defaultValue, i.source.code, i.value, i.source.name.defaultValue from Indicator i WHERE i.type.code = :code and ")
				.setParameter("code", indicatorTypeCode).setMaxResults(1);
		Object[] queryResult = null;
		try {
			queryResult = (Object[]) query.getSingleResult();
		} catch (final NoResultException e) {
			// It is possible that no value exists for the given indicator.
			// So we just put the indicator in the result.
			queryResult = new Object[] { indicatorTypeCode };
		}
		return queryResult;
	}

	/* ********* */
	/* Utilities */
	/* ********* */

	@Override
	public List<String> getExistingSourcesCodesForYearAndIndicatorType(final int year, final String indicatorTypeCode) {
		final TimeRange timerange = new TimeRange(Integer.valueOf(year).toString());
		final TypedQuery<String> query = this.em
				.createQuery("SELECT DISTINCT(i.source.code) FROM Indicator i Where i.start = :start AND i.end = :end AND i.periodicity = :periodicity AND i.type.code = :indicatorTypeCode",
						String.class).setParameter("start", timerange.getStart()).setParameter("end", timerange.getEnd()).setParameter("periodicity", timerange.getPeriodicity())
				.setParameter("indicatorTypeCode", indicatorTypeCode);
		return query.getResultList();
	}

	@Override
	public List<String> getExistingSourcesCodesForIndicatorType(final String indicatorTypeCode) {
		final TypedQuery<String> query = this.em.createQuery("SELECT DISTINCT(i.source.code) FROM Indicator i Where i.type.code = :indicatorTypeCode", String.class).setParameter("indicatorTypeCode",
				indicatorTypeCode);
		return query.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Long countIndicatorsByCriteria(final List<String> indicatorTypeCodes, final List<String> sourceCodes,
			final List<String> entityCodes, final Integer startYear, final Integer endYear){
		final CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		final CriteriaQuery<Long> criteriaQuery =
				this.createQueryforIndicatorsByCriteria(indicatorTypeCodes, sourceCodes, entityCodes, startYear,
				endYear, null, criteriaBuilder, Long.class, false);

		final Set<Root<?>> roots = criteriaQuery.getRoots();
		criteriaQuery.select(criteriaBuilder.count(roots.iterator().next()));

		final TypedQuery<Long> query = this.em.createQuery(criteriaQuery);
		return query.getSingleResult();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Integer> listAvailablePeriods(final List<String> indicatorTypeCodes, final List<String> sourceCodes,
			final List<String> entityCodes, final Integer startYear, final Integer endYear){

		final CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		final CriteriaQuery<Integer> criteriaQuery =
				this.createQueryforIndicatorsByCriteria(indicatorTypeCodes, sourceCodes, entityCodes, startYear,
				endYear, null, criteriaBuilder, Integer.class, false);

		final Set<Root<?>> roots = criteriaQuery.getRoots();
		final Root<?> root = roots.iterator().next();
		final Expression<Integer> selectExpression = criteriaBuilder.function("year", Integer.class, root.get("start"));
		criteriaQuery.select(selectExpression);
		criteriaQuery.distinct(true);
		criteriaQuery.orderBy(criteriaBuilder.desc(selectExpression));

		final TypedQuery<Integer> query = this.em.createQuery(criteriaQuery);
		final List<Integer> resultList = query.getResultList();

		return resultList;
	}

	@Transactional(readOnly = true)
	@Override
	public Integer latestYearForIndicatorsByCriteria(final List<String> indicatorTypeCodes, final List<String> sourceCodes,
			final List<String> entityCodes){
		final CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		final CriteriaQuery<Date> criteriaQuery =
				this.createQueryforIndicatorsByCriteria(indicatorTypeCodes, sourceCodes, entityCodes, null,
				null, null, criteriaBuilder, Date.class, false);

		final Set<Root<?>> roots = criteriaQuery.getRoots();

		final Root<Indicator> indicatorRoot = (Root<Indicator>) roots.iterator().next();

		final Path<Date> datePath = indicatorRoot.get("start");

		criteriaQuery.select( criteriaBuilder.greatest(datePath) );

		final TypedQuery<Date> query = this.em.createQuery(criteriaQuery);
		final Date resultDate =  query.getSingleResult();

		if ( resultDate != null ) {
			final Calendar c = Calendar.getInstance();
			c.setTime(resultDate);
			return c.get(Calendar.YEAR);
		}

		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public List<IntermediaryIndicatorValue> listIndicatorsByCriteria(final List<String> indicatorTypeCodes, final List<String> sourceCodes,
			final List<String> entityCodes, final Integer startYear, final Integer endYear, final List<SortingOption> sortingOptions,
			final Integer startPosition, final Integer maxResult, final String lang){

		final CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		final CriteriaQuery<IntermediaryIndicatorValue> criteriaQuery =
				this.createQueryforIndicatorsByCriteria(indicatorTypeCodes, sourceCodes, entityCodes, startYear,
						endYear, sortingOptions, criteriaBuilder, IntermediaryIndicatorValue.class, true);

		final TypedQuery<IntermediaryIndicatorValue> query = this.em.createQuery(criteriaQuery);
		query.setFirstResult(startPosition);
		query.setMaxResults(maxResult);
		return query.getResultList();
	}

	/**
	 * @param indicatorTypeCodes
	 * @param sourceCodes
	 * @param entityCodes
	 * @param startYear
	 * @param endYear
	 * @param criteriaQuery
	 * @param criteriaBuilder
	 * @return
	 */
	private <T> CriteriaQuery<T> createQueryforIndicatorsByCriteria(final List<String> indicatorTypeCodes,
			final List<String> sourceCodes, final List<String> entityCodes, final Integer startYear,
			final Integer endYear, final List<SortingOption> sortingOptions, final CriteriaBuilder criteriaBuilder,
			final Class<T> responseClass,
			final boolean addSelectClause) {

		final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(responseClass);
		final Root<Indicator> indicatorRoot = criteriaQuery.from(Indicator.class);


		final Join<Indicator, IndicatorType> indToIndTypeJoin = indicatorRoot.join("type");
		final Join<IndicatorType, Text> indTypeToTextJoin = indToIndTypeJoin.join("name");
//		final Join<Translation, Language> translationToLangJoin = textToTranslationsJoin.join((SingularAttribute) textToTranslationsJoin.get("id").get("language").getModel());
//		final Join<Id, Language> translationToLangJoin = tempJoin.join("language");

		final Join<Indicator, Source> indToSrcJoin = indicatorRoot.join("source");
		final Join<Object, Object> srcToTextJoin = indToSrcJoin.join("name");

		final Join<Indicator, Entity> indToEntityJoin = indicatorRoot.join("entity");
		final Join<Object, Object> entityToTextJoin = indToEntityJoin.join("name");

		final List<Predicate> filters = new ArrayList<>();
		filters.add(indicatorRoot.get("value").get("numberValue").isNotNull());

		if ( CollectionUtils.isNotEmpty(indicatorTypeCodes) ) {
			filters.add( indToIndTypeJoin.get("code").in(indicatorTypeCodes) );
		}
		if ( CollectionUtils.isNotEmpty(sourceCodes)) {
			filters.add( indToSrcJoin.get("code").in(sourceCodes) );
		}
		if ( CollectionUtils.isNotEmpty(entityCodes)) {
			filters.add( indToEntityJoin.get("code").in(entityCodes) );
		}
		if ( startYear != null ) {
			final LocalDateTime  startDate = new LocalDateTime(startYear, 1, 1, 0, 0);
			filters.add( criteriaBuilder.greaterThanOrEqualTo(indicatorRoot.<Date>get("start"), startDate.toDate()) );
		}
		if ( endYear != null ) {
			final LocalDateTime  endDate = new LocalDateTime(endYear+1, 1, 1, 0, 0);
			filters.add( criteriaBuilder.lessThan(indicatorRoot.<Date>get("start"), endDate.toDate()) );
		}


		if (addSelectClause) {
			criteriaQuery.
				select(criteriaBuilder.construct(
						responseClass,
						indicatorRoot.get("value").get("numberValue"),
						indToIndTypeJoin.get("code"),
						indTypeToTextJoin.get("defaultValue"),
						indTypeToTextJoin.get("id"),
						indToEntityJoin.get("code"),
						entityToTextJoin.get("defaultValue"),
						entityToTextJoin.get("id"),
						indToSrcJoin.get("code"),
						srcToTextJoin.get("defaultValue"),
						srcToTextJoin.get("id"),
						indicatorRoot.get("start")
					)
				)
				.orderBy(criteriaBuilder.asc(indToIndTypeJoin.get("code")));

			if ( sortingOptions != null ) {
				this.addSorting(sortingOptions, criteriaBuilder, criteriaQuery, indicatorRoot, indTypeToTextJoin, srcToTextJoin, entityToTextJoin);
			}
		}
		criteriaQuery.where(filters.toArray(new Predicate[0]));

		return criteriaQuery;
	}

	/**
	 * @param sortingOption
	 * @param criteriaBuilder
	 * @param criteriaQuery
	 * @param indicatorRoot
	 * @param indTypeToTextJoin
	 * @param srcToTextJoin
	 * @param entityToTextJoin
	 */
	private <T> void addSorting(final List<SortingOption> sortingOptions, final CriteriaBuilder criteriaBuilder, final CriteriaQuery<T> criteriaQuery,
			final Root<Indicator> indicatorRoot, final Join<IndicatorType, Text> indTypeToTextJoin, final Join<Object, Object> srcToTextJoin,
			final Join<Object, Object> entityToTextJoin) {
		for (final SortingOption sortingOption: sortingOptions) {
			switch (sortingOption) {
				case VALUE_ASC:
					criteriaQuery.orderBy(criteriaBuilder.asc(indicatorRoot.get("value").get("numberValue")));
					break;
				case VALUE_DESC:
					criteriaQuery.orderBy(criteriaBuilder.desc(indicatorRoot.get("value").get("numberValue")));
					break;
				case COUNTRY_ASC:
					criteriaQuery.orderBy(criteriaBuilder.asc(entityToTextJoin.get("defaultValue")));
					break;
				case COUNTRY_DESC:
					criteriaQuery.orderBy(criteriaBuilder.desc(entityToTextJoin.get("defaultValue")));
					break;
				case INDICATOR_TYPE_ASC:
					criteriaQuery.orderBy(criteriaBuilder.asc(indTypeToTextJoin.get("defaultValue")));
					break;
				case INDICATOR_TYPE_DESC:
					criteriaQuery.orderBy(criteriaBuilder.desc(indTypeToTextJoin.get("defaultValue")));
					break;
				case SOURCE_TYPE_ASC:
					criteriaQuery.orderBy(criteriaBuilder.asc(srcToTextJoin.get("defaultValue")));
					break;
				case SOURCE_TYPE_DESC:
					criteriaQuery.orderBy(criteriaBuilder.desc(srcToTextJoin.get("defaultValue")));
					break;
				case START_DATE_ASC:
					criteriaQuery.orderBy(criteriaBuilder.asc(indicatorRoot.get("start")));
					break;
				case START_DATE_DESC:
					criteriaQuery.orderBy(criteriaBuilder.desc(indicatorRoot.get("start")));
					break;
			}
		}
	}

	@Override
	public boolean indicatorExists(final PreparedIndicator preparedIndicator) {
		final StringBuilder builder = new StringBuilder("SELECT count(i) FROM Indicator i WHERE");
		builder.append(" i.source.code = :sourceCode");
		builder.append(" AND i.type.code = :indicatorTypeCode");
		builder.append(" AND i.entity.code = :entityCode");
		builder.append(" AND i.entity.type.code = :entityTypeCode");
		builder.append(" AND i.periodicity = :periodicity");
		builder.append(" AND i.start = :startTime");
		final TypedQuery<Long> query = this.em.createQuery(builder.toString(), Long.class);
		query.setParameter("sourceCode", preparedIndicator.getSourceCode());
		query.setParameter("indicatorTypeCode", preparedIndicator.getIndicatorTypeCode());
		query.setParameter("entityCode", preparedIndicator.getEntityCode());
		query.setParameter("entityTypeCode", preparedIndicator.getEntityTypeCode());
		query.setParameter("startTime", preparedIndicator.getStart());
		query.setParameter("periodicity", preparedIndicator.getPeriodicity());
		final Long count = query.getSingleResult();
		if ( count != null && count > 0 ) {
			return true;
		} else {
			return false;
		}
	}

}
