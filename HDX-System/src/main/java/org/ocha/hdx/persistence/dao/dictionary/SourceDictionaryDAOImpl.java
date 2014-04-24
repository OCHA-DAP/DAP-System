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
	public List<SourceDictionary> listSourceDictionaries(final Long configId) {
        String qlString = "SELECT sd FROM SourceDictionary sd";
        if (configId != null)
            qlString += " WHERE sd.configuration = " + configId;
        qlString += " ORDER BY sd.id";

        final TypedQuery<SourceDictionary> query = em.createQuery(qlString, SourceDictionary.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void createSourceDictionary(final String unnormalizedName, final String importer, final Source source, final ResourceConfiguration configuration) {
		final SourceDictionary sourceDictionary = new SourceDictionary(unnormalizedName, importer, source, configuration);
		em.persist(sourceDictionary);
	}

	@Override
	@Transactional
	public void deleteSourceDictionary(final SourceDictionary sourceDictionary) {
		em.remove(em.contains(sourceDictionary) ? sourceDictionary : em.merge(sourceDictionary));
	}

	@Override
	@Transactional
	public void deleteSourceDictionary(final String unnormalizedName, final String importer) {
		final SourceDictionary sourceDictionary = new SourceDictionary(unnormalizedName, importer, null, null);
		em.remove(em.contains(sourceDictionary) ? sourceDictionary : em.merge(sourceDictionary));
	}

	@Override
	public List<SourceDictionary> getSourceDictionariesByImporter(final String importer) {
		final TypedQuery<SourceDictionary> query = em.createQuery("SELECT sd FROM SourceDictionary sd WHERE sd.id.importer = :importer ORDER BY sd.id", SourceDictionary.class).setParameter(
				"importer", importer);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteAllSourceDictionaries() {
		em.createQuery("DELETE FROM SourceDictionary").executeUpdate();
	}

}
