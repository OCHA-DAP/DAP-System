package org.ocha.hdx.persistence.dao.view;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.config.api2.Constants;
import org.ocha.hdx.model.api2util.RequestParamsWrapper.SortingOption;
import org.ocha.hdx.persistence.entity.view.IndicatorMaxDate;
import org.springframework.util.CollectionUtils;

public class IndicatorMaxDateDAOImpl implements IndicatorMaxDateDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<IndicatorMaxDate> getValues(final List<String> entityCodes, final List<String> indicatorTypeCodes, final List<String> sourceCodes, final SortingOption sortingOption) {
		final StringBuilder builder = new StringBuilder("SELECT imd FROM IndicatorMaxDate imd");

		if ( !CollectionUtils.isEmpty(indicatorTypeCodes) || !CollectionUtils.isEmpty(entityCodes) || !CollectionUtils.isEmpty(sourceCodes)) {
			builder.append(" WHERE ");
		}

		boolean andNeeded = false;
		if (entityCodes != null && !entityCodes.isEmpty()) {
			builder.append(" imd.locationCode IN (:entityCodes) ");
			andNeeded = true;
		}

		if (indicatorTypeCodes != null && !indicatorTypeCodes.isEmpty()) {
			if (andNeeded) {
				builder.append(" AND ");
			}
			builder.append(" imd.indicatorTypeCode IN (:indicatorTypeCodes) ");
			andNeeded = true;

		}

		if (sourceCodes != null && !sourceCodes.isEmpty()) {
			if (andNeeded) {
				builder.append(" AND ");
			}
			builder.append(" imd.sourceCode IN (:sourceCodes) ");
		}

		if ( sortingOption != null ) {
			builder.append(" ORDER BY " + this.addSortingParameter(sortingOption));
		}

		final TypedQuery<IndicatorMaxDate> query = this.em.createQuery(builder.toString(), IndicatorMaxDate.class);

		if (entityCodes != null && !entityCodes.isEmpty()) {
			query.setParameter("entityCodes", entityCodes);
		}
		if (indicatorTypeCodes != null && !indicatorTypeCodes.isEmpty()) {
			query.setParameter("indicatorTypeCodes", indicatorTypeCodes);
		}
		if (sourceCodes != null && !sourceCodes.isEmpty()) {
			query.setParameter("sourceCodes", sourceCodes);
		}
//		if ( sortingOption != null ) {
//			this.addSortingParameter(sortingOption, query);
//		}

		query.setMaxResults(Constants.MAX_RESULTS);
		return query.getResultList();
	}

	private String addSortingParameter (final SortingOption sortingOption) {
		String sortingField = null;
		switch (sortingOption) {
		case VALUE_ASC:
			sortingField = "value ASC";
			break;
		case VALUE_DESC:
			sortingField = "value DESC";
			break;
		case COUNTRY_ASC:
			sortingField = "locationName ASC";
			break;
		case COUNTRY_DESC:
			sortingField = "locationName DESC";
			break;
		case INDICATOR_TYPE_ASC:
			sortingField = "indicatorTypeName ASC";
			break;
		case INDICATOR_TYPE_DESC:
			sortingField = "indicatorTypeName DESC";
			break;
		case SOURCE_TYPE_ASC:
			sortingField = "sourceName ASC";
			break;
		case SOURCE_TYPE_DESC:
			sortingField = "sourceName DESC";
			break;
		}

		return sortingField;
	}
}
