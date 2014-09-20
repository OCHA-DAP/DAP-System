package org.ocha.hdx.service;

import org.ocha.hdx.importer.report.ImportReport;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset;
import org.ocha.hdx.persistence.entity.ckan.CKANResource;
import org.ocha.hdx.persistence.entity.ckan.CKANResource.WorkflowState;

public interface WorkflowService {

	public boolean nextStateIsPossible(final String id, final String revision_id, WorkflowState destinationState);

	public boolean nextStateIsPossible(CKANResource resource, WorkflowState destinationState);

	public boolean flagCKANResourceAsOutdated(final String id, final String revision_id);

	public boolean flagCKANResourceAsDownloaded(final String id, final String revision_id);

	public boolean flagCKANResourceAsFilePreValidationSuccess(final String id, final String revision_id, final ValidationReport report);

	public boolean flagCKANResourceAsFilePreValidationFail(final String id, final String revision_id, final ValidationReport report);

	public boolean flagCKANResourceAsImporting(final String id, final String revision_id);

	public boolean flagCKANResourceAsImportSuccess(final String id, final String revision_id, final CKANDataset.Type importer, final ValidationReport validationReport, final ImportReport importReport);

	public boolean flagCKANResourceAsImportFail(final String id, final String revision_id, final CKANDataset.Type importer, final ValidationReport validationReport, final ImportReport importReport);

	public ValidationReport readValidationReport(final String id, final String revision_id);

}
