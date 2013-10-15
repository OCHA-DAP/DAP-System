package org.ocha.dap.persistence.dao;

import java.util.Date;
import java.util.List;

import org.ocha.dap.persistence.entity.CKANResource;

public interface CKANResourceDAO {
	public void newCKANResourceDetected(final String id, final String revision_id, final Date revision_timestamp, final String parentDataset_id,
			final String parentDataset_revision_id, final Date parentDataset_revision_timestamp);

	public void flagCKANResourceAsDownloaded(final String id, final String revision_id);

	public CKANResource getCKANResource(final String id, final String revision_id);

	public List<CKANResource> listCKANResources();
	
	public void deleteAllCKANResourcesRecords();

}
