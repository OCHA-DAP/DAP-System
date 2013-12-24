package org.ocha.dap.persistence.dao.dictionary;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.Source;
import org.ocha.dap.persistence.entity.dictionary.RegionDictionary;
import org.springframework.transaction.annotation.Transactional;

public class RegionDictionaryDAOImpl implements RegionDictionaryDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<RegionDictionary> listRegionDictionaries() {
		final TypedQuery<RegionDictionary> query = em.createQuery("SELECT rd FROM RegionDictionary rd ORDER BY rd.id", RegionDictionary.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void addRegionDictionary(final String unnormalizedName, final String importer, final Entity entity) {
		final RegionDictionary regionDictionary = new RegionDictionary(unnormalizedName, importer, entity);
		em.persist(regionDictionary);
	}
	
	@Override
	@Transactional
	public void deleteRegionDictionary(final String unnormalizedName, final String importer, final Entity entity) {
		//RegionDictionary regionDictionary = new RegionDictionary;
		return;
	}

	@Override
	@Transactional
	public RegionDictionary getRegionDictionaryByFields(final String unnormalizedName, final String importer, final Entity entity) {
		final TypedQuery<RegionDictionary> query = em.createQuery("SELET rd FROM RegionDictionary Where rd.unnormalizedname = :unnormalizedName AND rd.importer = :importer AND rd.entity = :entity", RegionDictionary.class);
		System.out.println("RD queried: " + query.getSingleResult());
		return query.getSingleResult();
	}
}


