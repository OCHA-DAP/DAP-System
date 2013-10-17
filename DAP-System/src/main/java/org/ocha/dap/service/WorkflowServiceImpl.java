package org.ocha.dap.service;

import java.util.List;
import java.util.Map;

import org.ocha.dap.persistence.dao.CKANResourceDAO;
import org.ocha.dap.persistence.entity.CKANResource;
import org.ocha.dap.persistence.entity.CKANResource.WorkflowState;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkflowServiceImpl implements WorkflowService {

	private final Map<WorkflowState, List<WorkflowState>> possibleTransitionsMap;

	public WorkflowServiceImpl(final Map<WorkflowState, List<WorkflowState>> possibleTransitionsMap) {
		super();
		this.possibleTransitionsMap = possibleTransitionsMap;
	}

	@Autowired
	private CKANResourceDAO resourceDAO;

	private boolean isTransitionPossible(final WorkflowState from, final WorkflowState to) {
		final List<WorkflowState> tos = possibleTransitionsMap.get(from);

		if (tos == null)
			return false;
		if (tos.contains(to)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean nextStateIsPossible(final CKANResource resource, final WorkflowState destinationState) {
		return isTransitionPossible(resource.getWorkflowState(), destinationState);
	}

	@Override
	public boolean flagCKANResourceAsOutdated(final String id, final String revision_id) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.OUTDATED)) {
			resourceDAO.flagCKANResourceAsOutdated(id, revision_id);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean flagCKANResourceAsDownloaded(final String id, final String revision_id) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.DOWNLOADED)) {
			resourceDAO.flagCKANResourceAsDownloaded(id, revision_id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean flagCKANResourceAsTechEvaluationSuccess(final String id, final String revision_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean flagCKANResourceAsTechEvaluationFail(final String id, final String revision_id) {
		// TODO Auto-generated method stub
		return false;
	}

}
