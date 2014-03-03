package org.ocha.hdx.service;

import java.util.List;
import java.util.Map;

import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.persistence.dao.ckan.CKANResourceDAO;
import org.ocha.hdx.persistence.dao.config.ResourceConfigurationDao;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.hdx.persistence.entity.ckan.CKANResource;
import org.ocha.hdx.persistence.entity.ckan.CKANResource.WorkflowState;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkflowServiceImpl implements WorkflowService {

	private final Map<WorkflowState, List<WorkflowState>> possibleTransitionsMap;

	public WorkflowServiceImpl(final Map<WorkflowState, List<WorkflowState>> possibleTransitionsMap) {
		super();
		this.possibleTransitionsMap = possibleTransitionsMap;
	}

	@Autowired
	private CKANResourceDAO resourceDAO;
	
	@Autowired 
	private ResourceConfigurationDao resourceConfigurationDao;

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
	public boolean flagCKANResourceAsConfigured(final String id, final String revision_id, final ResourceConfiguration resourceConfiguration) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.CONFIGURED)) {
			resourceDAO.flagCKANResourceAsConfigured(id, revision_id, resourceConfiguration);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean flagCKANResourceAsTechEvaluationSuccess(final String id, final String revision_id, final ValidationReport report) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.TECH_EVALUATION_SUCCESS)) {
			resourceDAO.flagCKANResourceAsTechEvaluationSuccess(id, revision_id, report);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean flagCKANResourceAsTechEvaluationFail(final String id, final String revision_id, final ValidationReport report) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.TECH_EVALUATION_FAIL)) {
			resourceDAO.flagCKANResourceAsTechEvaluationFail(id, revision_id, report);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean flagCKANResourceAsImportSuccess(final String id, final String revision_id, final Type evaluator, final ValidationReport report) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.IMPORT_SUCCESS)) {
			resourceDAO.flagCKANResourceAsImportSuccess(id, revision_id, evaluator, report);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean flagCKANResourceAsImportFail(final String id, final String revision_id, final Type evaluator, final ValidationReport report) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.IMPORT_FAIL)) {
			resourceDAO.flagCKANResourceAsImportFail(id, revision_id, evaluator, report);
			return true;
		} else {
			return false;
		}
	}
	
}
