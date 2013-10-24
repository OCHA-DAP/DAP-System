package org.ocha.dap.service;

import java.util.List;
import java.util.Map;

import org.ocha.dap.persistence.dao.CKANResourceDAO;
import org.ocha.dap.persistence.entity.CKANDataset;
import org.ocha.dap.persistence.entity.CKANDataset.Type;
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
	public boolean flagCKANResourceAsTechEvaluationSuccess(final String id, final String revision_id, final CKANDataset.Type evaluator) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.TECH_EVALUATION_SUCCESS)) {
			resourceDAO.flagCKANResourceAsTechEvaluationSuccess(id, revision_id, evaluator);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean flagCKANResourceAsTechEvaluationFail(final String id, final String revision_id, final CKANDataset.Type evaluator) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.TECH_EVALUATION_FAIL)) {
			resourceDAO.flagCKANResourceAsTechEvaluationFail(id, revision_id, evaluator);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean flagCKANResourceAsImportSuccess(final String id, final String revision_id, final Type evaluator) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.IMPORT_SUCCESS)) {
			resourceDAO.flagCKANResourceAsImportSuccess(id, revision_id, evaluator);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean flagCKANResourceAsImportFail(final String id, final String revision_id, final Type evaluator) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.IMPORT_FAIL)) {
			resourceDAO.flagCKANResourceAsImportFail(id, revision_id, evaluator);
			return true;
		} else {
			return false;
		}
	}

}
