package org.ocha.hdx.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.ocha.hdx.importer.report.ImportReport;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.persistence.dao.ckan.CKANResourceDAO;
import org.ocha.hdx.persistence.dao.config.ResourceConfigurationDAO;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.hdx.persistence.entity.ckan.CKANResource;
import org.ocha.hdx.persistence.entity.ckan.CKANResource.WorkflowState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkflowServiceImpl implements WorkflowService {

	private static final Logger log = LoggerFactory.getLogger(WorkflowServiceImpl.class);

	private final Map<WorkflowState, List<WorkflowState>> possibleTransitionsMap;

	private final File reportDirectory;

	public WorkflowServiceImpl(final Map<WorkflowState, List<WorkflowState>> possibleTransitionsMap, final File reportDirectory) {
		super();
		this.possibleTransitionsMap = possibleTransitionsMap;

		if (!reportDirectory.isDirectory()) {
			throw new IllegalArgumentException("reports directory doesn't exist: " + reportDirectory.getAbsolutePath());
		}

		this.reportDirectory = reportDirectory;
	}

	@Autowired
	private CKANResourceDAO resourceDAO;

	@Autowired
	private ResourceConfigurationDAO resourceConfigurationDAO;

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
	public boolean nextStateIsPossible(final String id, final String revision_id, final WorkflowState destinationState) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		return isTransitionPossible(res.getWorkflowState(), destinationState);
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
	public boolean flagCKANResourceAsFilePreValidationSuccess(final String id, final String revision_id, final ValidationReport report) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.FILE_PRE_VALIDATION_SUCCESS)) {
			resourceDAO.flagCKANResourceAsFilePreValidationSuccess(id, revision_id, report.getValidator());
			writeValidationReportOnFile(id, revision_id, report);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean flagCKANResourceAsFilePreValidationFail(final String id, final String revision_id, final ValidationReport report) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.FILE_PRE_VALIDATION_FAIL)) {
			resourceDAO.flagCKANResourceAsFilePreValidationFail(id, revision_id, report.getValidator());
			writeValidationReportOnFile(id, revision_id, report);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean flagCKANResourceAsImporting(final String id, final String revision_id) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.IMPORTING)) {
			resourceDAO.flagCKANResourceAsImporting(id, revision_id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean flagCKANResourceAsImportSuccess(final String id, final String revision_id, final Type importer, final ValidationReport validationReport, final ImportReport importReport) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.IMPORT_SUCCESS)) {
			resourceDAO.flagCKANResourceAsImportSuccess(id, revision_id, importer);
			writeImportReportOnFile(id, revision_id, importReport);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean flagCKANResourceAsImportFail(final String id, final String revision_id, final Type importer, final ValidationReport validationReport, final ImportReport importReport) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		if (nextStateIsPossible(res, WorkflowState.IMPORT_FAIL)) {
			resourceDAO.flagCKANResourceAsImportFail(id, revision_id, importer);
			writeImportReportOnFile(id, revision_id, importReport);
			return true;
		} else {
			return false;
		}
	}

	private void writeValidationReportOnFile(final String id, final String revision_id, final ValidationReport validationReport) {
		final File reportFile = new File(getReportFolder(id, revision_id), "ValidationReport");

		try (final FileOutputStream fout = new FileOutputStream(reportFile)) {
			IOUtils.write(validationReport.toJson(), fout);
		} catch (final Exception e) {
			log.error(e.toString(), e);
		}
	}

	@Override
	public ValidationReport readValidationReport(final String id, final String revision_id) {
		final File reportFile = new File(getReportFolder(id, revision_id), "ValidationReport");

		try (FileInputStream fileIn = new FileInputStream(reportFile)) {
			return ValidationReport.fromJson(IOUtils.toString(fileIn));
		} catch (final Exception e) {
			log.error(e.toString(), e);
			return null;
		}

	}

	private void writeImportReportOnFile(final String id, final String revision_id, final ImportReport importReport) {
		final File reportFile = new File(getReportFolder(id, revision_id), "ImportReport");

		try (final FileOutputStream fout = new FileOutputStream(reportFile)) {
			IOUtils.write(importReport.toJson(), fout);
		} catch (final Exception e) {
			log.error(e.toString(), e);
		}
	}

	@Override
	public ImportReport readImportReport(final String id, final String revision_id) {
		final File reportFile = new File(getReportFolder(id, revision_id), "ImportReport");

		try (FileInputStream fileIn = new FileInputStream(reportFile)) {
			return ImportReport.fromJson(IOUtils.toString(fileIn));
		} catch (final Exception e) {
			log.error(e.toString(), e);
			return null;
		}

	}

	private File getReportFolder(final String id, final String revision_id) {
		final String fileName = resourceDAO.getCKANResource(id, revision_id).getName();

		final File resourceFolder = new File(reportDirectory, id);
		final File revisionFolder = new File(resourceFolder, revision_id);
		return new File(revisionFolder, fileName);
	}

}
