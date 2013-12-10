package org.ocha.dap.persistence.dao.dictionary;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.dap.persistence.entity.dictionary.RegionDictionary;
import org.springframework.transaction.annotation.Transactional;

public class RegionDictionaryDAOImpl implements RegionDictionaryDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<RegionDictionary> listRegionDictionary() {
		final TypedQuery<RegionDictionary> query = em.createQuery("SELECT rd FROM RegionDictionary rd ORDER BY rd.id", RegionDictionary.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void addRegionDictionary(final String unnormalizedName, final String source, final String entityType, final String entityCode) {
		final RegionDictionary regionDictionary = new RegionDictionary(unnormalizedName, source, entityType, entityCode);
		em.persist(regionDictionary);
	}

}
