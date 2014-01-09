package org.ocha.dap.persistence.dao.currateddata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.springframework.transaction.annotation.Transactional;

public class IndicatorTypeDAOImpl implements IndicatorTypeDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<IndicatorType> listIndicatorTypes() {
		final TypedQuery<IndicatorType> query = em.createQuery("SELECT it FROM IndicatorType it ORDER BY it.code", IndicatorType.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void addIndicatorType(final String code, final String name, final String unit) {
		final IndicatorType indicatorType = new IndicatorType();
		indicatorType.setCode(code);
		indicatorType.setName(name);
		indicatorType.setUnit(unit);
		em.persist(indicatorType);

	}

	@Override
	public IndicatorType getIndicatorTypeByCode(final String code) {
		final TypedQuery<IndicatorType> query = em.createQuery("SELECT it FROM IndicatorType it Where it.code = :code", IndicatorType.class).setParameter("code", code);
		return query.getSingleResult();
	}

	@Override
	public IndicatorType getIndicatorTypeById(final long id) {
		return em.find(IndicatorType.class, id);
	}

	@Override
	@Transactional
	public void deleteIndicatorTypeByCode(final String code) {
		em.remove(getIndicatorTypeByCode(code));
	}

}
