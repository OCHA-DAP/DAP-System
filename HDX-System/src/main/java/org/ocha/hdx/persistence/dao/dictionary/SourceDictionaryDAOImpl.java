package org.ocha.hdx.persistence.dao.dictionary;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.dictionary.SourceDictionary;
import org.springframework.transaction.annotation.Transactional;

public class SourceDictionaryDAOImpl implements SourceDictionaryDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<SourceDictionary> listSourceDictionaries() {
		final String qlString = "SELECT sd FROM SourceDictionary sd ORDER BY sd.id";

		final TypedQuery<SourceDictionary> query = em.createQuery(qlString, SourceDictionary.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void createSourceDictionary(final ResourceConfiguration resourceConfiguration, final Source source, final String unnormalizedName) {
		final SourceDictionary sourceDictionary = new SourceDictionary(resourceConfiguration, source, unnormalizedName);
		em.persist(sourceDictionary);
	}

	@Override
	@Transactional
	public void deleteSourceDictionary(final SourceDictionary sourceDictionary) {
		em.remove(em.contains(sourceDictionary) ? sourceDictionary : em.merge(sourceDictionary));
	}

	@Override
	public List<SourceDictionary> getSourceDictionariesByResourceConfiguration(final ResourceConfiguration resourceConfiguration) {
		final TypedQuery<SourceDictionary> query = em.createQuery("SELECT sd FROM SourceDictionary sd WHERE sd.resourceConfiguration = :resourceConfiguration ORDER BY sd.id", SourceDictionary.class)
				.setParameter("resourceConfiguration", resourceConfiguration);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteAllSourceDictionaries() {
		em.createQuery("DELETE FROM SourceDictionary").executeUpdate();
	}

	@Override
	@Transactional
	public void deleteSourceDictionary(final long id) {
		deleteSourceDictionary(em.find(SourceDictionary.class, id));
	}

}
