package org.ocha.hdx.persistence.dao.dictionary;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.dictionary.IndicatorTypeDictionary;
import org.springframework.transaction.annotation.Transactional;

public class IndicatorTypeDictionaryDAOImpl implements IndicatorTypeDictionaryDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<IndicatorTypeDictionary> listIndicatorTypeDictionaries() {
		final String qlString = "SELECT itd FROM IndicatorTypeDictionary itd ORDER BY itd.id";

		final TypedQuery<IndicatorTypeDictionary> query = em.createQuery(qlString, IndicatorTypeDictionary.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void createIndicatorTypeDictionary(final ResourceConfiguration resourceConfiguration, final IndicatorType indicatorType, final String unnormalizedName) {
		final IndicatorTypeDictionary indicatorTypeDictionary = new IndicatorTypeDictionary(resourceConfiguration, indicatorType, unnormalizedName);
		em.persist(indicatorTypeDictionary);
	}

	@Override
	@Transactional
	public void deleteIndicatorTypeDictionary(final IndicatorTypeDictionary indicatorTypeDictionary) {
		em.remove(em.contains(indicatorTypeDictionary) ? indicatorTypeDictionary : em.merge(indicatorTypeDictionary));

	}

	@Override
	public List<IndicatorTypeDictionary> getIndicatorTypeDictionariesByResourceConfiguration(final ResourceConfiguration resourceConfiguration) {
		final TypedQuery<IndicatorTypeDictionary> query = em.createQuery("SELECT itd FROM IndicatorTypeDictionary itd WHERE itd.resourceConfiguration = :resourceConfiguration ORDER BY itd.id",
				IndicatorTypeDictionary.class).setParameter("resourceConfiguration", resourceConfiguration);
		return query.getResultList();
	}

}
