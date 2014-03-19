package org.ocha.hdx.persistence.dao.view;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.view.IndicatorTypeOverview;

/*
 * Indicator type overview DAO Implementation
 */
public class IndicatorTypeOverviewDAOImpl implements IndicatorTypeOverviewDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public IndicatorTypeOverview getIndicatorTypeOverview(final String indicatorTypeCode, final String sourceCode) {
		final TypedQuery<IndicatorTypeOverview> query = em.createQuery("SELECT i FROM IndicatorTypeOverview i WHERE i.id.indicatorTypeCode = :indicatorTypeCode AND i.id.sourceCode = :sourceCode", IndicatorTypeOverview.class).setParameter("indicatorTypeCode", indicatorTypeCode).setParameter("sourceCode", sourceCode);
		return query.getSingleResult();
	}
}
