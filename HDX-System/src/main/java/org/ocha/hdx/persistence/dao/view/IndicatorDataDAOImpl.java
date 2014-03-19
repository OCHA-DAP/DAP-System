package org.ocha.hdx.persistence.dao.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.view.IndicatorData;

/*
 * Indicator data DAO Implementation
 */
public class IndicatorDataDAOImpl implements IndicatorDataDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Map<Long, Map<String, IndicatorData>> getIndicatorData(final String indicatorTypeCode, final String sourceCode, final Long fromYear, final Long toYear) {
		final Map<Long, Map<String, IndicatorData>> result = new HashMap<Long, Map<String, IndicatorData>>();
		List<IndicatorData> queryResult = null;
		final TypedQuery<IndicatorData> query = em.createQuery("SELECT i FROM IndicatorData i WHERE i.indicatorTypeCode = :indicatorTypeCode AND i.sourceCode = :sourceCode and i.indicatorYear >= :fromYear and i.indicatorYear <= :toYear order by i.indicatorYear, i.countryCode", IndicatorData.class).setParameter("indicatorTypeCode", indicatorTypeCode).setParameter("sourceCode", sourceCode).setParameter("fromYear", fromYear).setParameter("toYear", toYear);
		queryResult = query.getResultList();
		if(null != queryResult) {
			for (final IndicatorData indicatorData : queryResult) {
				Map<String, IndicatorData> countryMap = result.get(indicatorData.getIndicatorYear());
				if(null == countryMap) {
					countryMap = new HashMap<String, IndicatorData>();
					result.put(indicatorData.getIndicatorYear(), countryMap);
				}
				countryMap.put(indicatorData.getCountryCode(), indicatorData);
			}
		}
		return result;
	}
}
