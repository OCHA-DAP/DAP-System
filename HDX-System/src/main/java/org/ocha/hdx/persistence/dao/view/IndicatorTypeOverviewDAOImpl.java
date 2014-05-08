package org.ocha.hdx.persistence.dao.view;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.ocha.hdx.persistence.entity.view.IndicatorTypeOverview;

/*
 * Indicator type overview DAO Implementation
 */
public class IndicatorTypeOverviewDAOImpl implements IndicatorTypeOverviewDAO {
	
	Logger logger = Logger.getLogger(IndicatorTypeOverviewDAOImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public IndicatorTypeOverview getIndicatorTypeOverview(final String indicatorTypeCode, final String sourceCode) {
		final TypedQuery<IndicatorTypeOverview> query = em.createQuery("SELECT i FROM IndicatorTypeOverview i WHERE i.id.indicatorTypeCode = :indicatorTypeCode AND i.id.sourceCode = :sourceCode", IndicatorTypeOverview.class).setParameter("indicatorTypeCode", indicatorTypeCode).setParameter("sourceCode", sourceCode);
		final IndicatorTypeOverview result = query.getSingleResult();
		logger.debug("Source code param [" + sourceCode + "], result source code [" + result.getSourceCode() + "], result source default value [" + result.getSourceDefaultValue() + "]");
		return result;
	}
}
