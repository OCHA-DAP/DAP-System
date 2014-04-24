package org.ocha.hdx.service;

import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset;
import org.ocha.hdx.persistence.entity.ckan.CKANResource;
import org.ocha.hdx.persistence.entity.ckan.CKANResource.WorkflowState;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;

public interface WorkflowService {

	public boolean nextStateIsPossible(CKANResource resource, WorkflowState destinationState);

	public boolean flagCKANResourceAsOutdated(final String id, final String revision_id);

	public boolean flagCKANResourceAsDownloaded(final String id, final String revision_id);

	public boolean flagCKANResourceAsFilePreValidationSuccess(final String id, final String revision_id, final ValidationReport report);

	public boolean flagCKANResourceAsFilePreValidationFail(final String id, final String revision_id, final ValidationReport report);

	public boolean flagCKANResourceAsImportSuccess(final String id, final String revision_id, final CKANDataset.Type evaluator, final ValidationReport report);

	public boolean flagCKANResourceAsImportFail(final String id, final String revision_id, final CKANDataset.Type evaluator, final ValidationReport report);

	/**
	 * @deprecated the resource should be configured from the first moment when it gets created and saved into the database.
	 * It takes the configuration from the dataset
	 */
	@Deprecated
	public boolean flagCKANResourceAsConfigured(final String id, final String revision_id, final ResourceConfiguration resourceConfiguration);

}
