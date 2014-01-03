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
	public void addRegionDictionary(final String unnormalizedName, final String importer, final Entity entity) { // TODO The list of
																													// importers should
																													// probably be available
																													// in a table.
		final RegionDictionary regionDictionary = new RegionDictionary(unnormalizedName, importer, entity);
		em.persist(regionDictionary);
	}

	@Override
	@Transactional
	public void deleteRegionDictionary(RegionDictionary regionDictionary) {
		em.remove(em.contains(regionDictionary) ? regionDictionary : em.merge(regionDictionary));
		return;
	}

	@Override
	@Transactional
	public void deleteRegionDictionary(String unnormalizedName, String importer) {
		RegionDictionary regionDictionary = new RegionDictionary(unnormalizedName, importer);
		em.remove(em.contains(regionDictionary) ? regionDictionary : em.merge(regionDictionary));
	}
}
