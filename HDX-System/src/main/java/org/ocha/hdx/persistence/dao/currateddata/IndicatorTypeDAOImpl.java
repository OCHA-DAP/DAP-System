package org.ocha.hdx.persistence.dao.currateddata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorTypeCount;
import org.ocha.hdx.persistence.entity.curateddata.Unit;
import org.ocha.hdx.persistence.entity.i18n.Text;
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
	public List<IndicatorTypeCount> listIndicatorTypeCounts() {
		final TypedQuery<IndicatorTypeCount> query = em.createQuery("FROM IndicatorTypeCount", IndicatorTypeCount.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public IndicatorType createIndicatorType(final String code, final Text name, final Unit unit, final ValueType valueType) {
		final IndicatorType indicatorType = new IndicatorType();
		indicatorType.setCode(code);
		indicatorType.setName(name);
		indicatorType.setUnit(unit);
		indicatorType.setValueType(valueType);
		em.persist(indicatorType);
		
		return indicatorType;
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

	@Override
	@Transactional
	public void deleteIndicatorType(final long indicatorTypeId) {
		em.createQuery("DELETE FROM IndicatorType i WHERE i.id = :indicatorTypeId").setParameter("indicatorTypeId", indicatorTypeId).executeUpdate();
	}

	@Override
	@Transactional
	public void updateIndicatorType(final long indicatorTypeId, final String newCode, final String newName, final Unit newUnit, final ValueType valueType) {
		final IndicatorType indicatorType = em.find(IndicatorType.class, indicatorTypeId);
		indicatorType.setCode(newCode);
		indicatorType.getName().setDefaultValue(newName);
		indicatorType.setUnit(newUnit);
		indicatorType.setValueType(valueType);
	}
}
