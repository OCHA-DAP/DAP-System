package org.ocha.dap.persistence.dao;

import java.util.Date;

import org.ocha.dap.persistence.entity.ImportFromCKAN;

public interface ImportFromCKANDAO {

	/**
	 * Creates a new record corresponding to the import of a revision of a resource
	 * 
	 * @param resourceId
	 * @param revisionId
	 * @param timestamp
	 *            timestamp of the import
	 * @return the created record
	 */
	public ImportFromCKAN createNewImportRecord(final String resourceId, final String revisionId, final Date timestamp);

}
