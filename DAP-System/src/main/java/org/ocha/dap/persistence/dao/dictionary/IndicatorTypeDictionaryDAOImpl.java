package org.ocha.dap.persistence.dao.dictionary;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.dictionary.IndicatorTypeDictionary;
import org.springframework.transaction.annotation.Transactional;

public class IndicatorTypeDictionaryDAOImpl implements IndicatorTypeDictionaryDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<IndicatorTypeDictionary> listIndicatorTypeDictionaries() {
		final TypedQuery<IndicatorTypeDictionary> query = em.createQuery("SELECT itd FROM IndicatorTypeDictionary itd ORDER BY itd.id", IndicatorTypeDictionary.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void addIndicatorTypeDictionary(final String unnormalizedName, final String importer, final IndicatorType indicatorType) {
		final IndicatorTypeDictionary indicatorTypeDictionary = new IndicatorTypeDictionary(unnormalizedName, importer, indicatorType);
		em.persist(indicatorTypeDictionary);
	}

	@Override
	@Transactional
	public void deleteIndicatorTypeDictionary(final IndicatorTypeDictionary indicatorTypeDictionary) {
		em.remove(em.contains(indicatorTypeDictionary) ? indicatorTypeDictionary : em.merge(indicatorTypeDictionary));

	}

	@Override
	@Transactional
	public void deleteIndicatorTypeDictionary(final String unnormalizedName, final String importer) {
		final IndicatorTypeDictionary indicatorTypeDictionary = new IndicatorTypeDictionary(unnormalizedName, importer);
		em.remove(em.contains(indicatorTypeDictionary) ? indicatorTypeDictionary : em.merge(indicatorTypeDictionary));

	}

}
