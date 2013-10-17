package org.ocha.dap.service;

import org.ocha.dap.persistence.entity.CKANResource;
import org.ocha.dap.persistence.entity.CKANResource.WorkflowState;

public interface WorkflowService {
	
	public boolean nextStateIsPossible(CKANResource resource, WorkflowState destinationState);
	
	public boolean flagCKANResourceAsOutdated(final String id, final String revision_id);
	public boolean flagCKANResourceAsDownloaded(final String id, final String revision_id);

}
