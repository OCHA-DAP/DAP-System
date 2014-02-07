package org.ocha.hdx.persistence.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.springframework.transaction.annotation.Transactional;

public class ImportFromCKANDAOImpl implements ImportFromCKANDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public ImportFromCKAN createNewImportRecord(final String resourceId, final String revisionId, final Date timestamp) {
		final ImportFromCKAN importFromCKAN = new ImportFromCKAN(resourceId, revisionId, timestamp);
		em.persist(importFromCKAN);

		return importFromCKAN;
	}

	@Override
	public ImportFromCKAN getDummyImport() {
		final TypedQuery<ImportFromCKAN> query = em.createQuery("SELECT ifckan FROM ImportFromCKAN ifckan Where ifckan.resourceId = 'dummy'", ImportFromCKAN.class);

		ImportFromCKAN importFromCkan;
		try {
			importFromCkan = query.getSingleResult();
		} catch (final NoResultException e) {
			// TODO create the record if not found
			importFromCkan = createNewImportRecord("dummy", "dummy", new Date());
		}

		return importFromCkan;
	}

	@Override
	public List<ImportFromCKAN> listImportsFromCKAN() {
		final TypedQuery<ImportFromCKAN> query = em.createQuery("SELECT i FROM ImportFromCKAN i ORDER BY i.id", ImportFromCKAN.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteImportFromCKAN(final long id) {
		em.remove(em.find(ImportFromCKAN.class, id));

	}
}
