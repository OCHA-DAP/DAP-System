package org.ocha.hdx.persistence.dao.dictionary;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.dictionary.RegionDictionary;
import org.springframework.transaction.annotation.Transactional;

public class RegionDictionaryDAOImpl implements RegionDictionaryDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<RegionDictionary> listRegionDictionaries() {
		final String qlString = "SELECT rd FROM RegionDictionary rd ORDER BY rd.id";

		final TypedQuery<RegionDictionary> query = em.createQuery(qlString, RegionDictionary.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void createRegionDictionary(final ResourceConfiguration resourceConfiguration, final Entity entity, final String unnormalizedName) {
		final RegionDictionary regionDictionary = new RegionDictionary(resourceConfiguration, entity, unnormalizedName);
		em.persist(regionDictionary);
	}

	@Override
	@Transactional
	public void deleteRegionDictionary(final RegionDictionary regionDictionary) {
		em.remove(em.contains(regionDictionary) ? regionDictionary : em.merge(regionDictionary));
	}

	@Override
	public List<RegionDictionary> getRegionDictionariesByResourceConfiguration(final ResourceConfiguration resourceConfiguration) {
		final TypedQuery<RegionDictionary> query = em.createQuery("SELECT rd FROM RegionDictionary rd WHERE rd.resourceConfiguration = :resourceConfiguration ORDER BY rd.id", RegionDictionary.class)
				.setParameter("resourceConfiguration", resourceConfiguration);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteRegionDictionary(final long id) {
		deleteRegionDictionary(em.find(RegionDictionary.class, id));

	}
}
