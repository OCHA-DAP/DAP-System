package org.ocha.dap.persistence.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ocha.dap.persistence.entity.ImportFromCKAN;
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
}
