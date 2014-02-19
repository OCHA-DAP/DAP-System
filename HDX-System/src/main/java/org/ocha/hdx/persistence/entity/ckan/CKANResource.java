package org.ocha.hdx.persistence.entity.ckan;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;

@Entity
@Table(name = "ckan_resource")
public class CKANResource {

	public enum WorkflowState {
		DETECTED_NEW, DETECTED_REVISION, OUTDATED, DOWNLOADED, TECH_EVALUATION_SUCCESS, TECH_EVALUATION_FAIL, IMPORT_SUCCESS, IMPORT_FAIL;
	}

	@Embeddable
	public static class Id implements Serializable {

		private static final long serialVersionUID = 2984070231545567772L;

		@Column(name = "id", nullable = false, updatable = false)
		private String id;

		@Column(name = "revision_id", nullable = false, updatable = false)
		private String revision_id;

		public Id() {
		}

		public Id(final String id, final String revision_id) {
			super();
			this.id = id;
			this.revision_id = revision_id;
		}

		public String getId() {
			return id;
		}

		public String getRevision_id() {
			return revision_id;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((revision_id == null) ? 0 : revision_id.hashCode());
			return result;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final Id other = (Id) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (revision_id == null) {
				if (other.revision_id != null)
					return false;
			} else if (!revision_id.equals(other.revision_id))
				return false;
			return true;
		}

	}

	public CKANResource() {
		super();
	}

	public CKANResource(final String id, final String revision_id, final boolean isNew, final String parentDataset_name) {
		this.id.id = id;
		this.id.revision_id = revision_id;
		if (isNew) {
			this.workflowState = WorkflowState.DETECTED_NEW;
		} else {
			this.workflowState = WorkflowState.DETECTED_REVISION;
		}
		this.parentDataset_name = parentDataset_name;
	}

	@EmbeddedId
	private final Id id = new Id();

	public Id getId() {
		return id;
	}

	@Column(name = "name", nullable = false, updatable = false)
	private String name;

	@Column(name = "workflowState", nullable = false, updatable = true)
	@Enumerated(EnumType.STRING)
	private WorkflowState workflowState;

	@Column(name = "revision_timestamp", columnDefinition = "timestamp", nullable = false, updatable = false)
	private Date revision_timestamp;

	@Column(name = "parentDataset_name", nullable = false, updatable = false)
	private String parentDataset_name;

	@Column(name = "parentDataset_id", nullable = false, updatable = false)
	private String parentDataset_id;

	@Column(name = "parentDataset_revision_id", nullable = false, updatable = false)
	private String parentDataset_revision_id;

	@Column(name = "parentDataset_revision_timestamp", columnDefinition = "timestamp", nullable = false, updatable = false)
	private Date parentDataset_revision_timestamp;

	@Column(name = "detectionDate", columnDefinition = "timestamp", nullable = false, updatable = false)
	private Date detectionDate;

	@Column(name = "downloadDate", columnDefinition = "timestamp", nullable = true, updatable = true)
	private Date downloadDate;

	@Column(name = "evaluationDate", columnDefinition = "timestamp", nullable = true, updatable = true)
	private Date evaluationDate;

	@Column(name = "evaluator", nullable = true, updatable = true)
	@Enumerated(EnumType.STRING)
	private CKANDataset.Type evaluator;

	@Column(name = "importDate", columnDefinition = "timestamp", nullable = true, updatable = true)
	private Date importDate;

	// FIXME here we store the serialization of the report for fast prototyping
	// this is something we'll probably want to change
	@Lob
	@Column(name = "validationReport", length = 50000, nullable = true, updatable = true)
	private ValidationReport validationReport;

	@Column(name = "importer", nullable = true, updatable = true)
	@Enumerated(EnumType.STRING)
	private CKANDataset.Type importer;
	
	@ManyToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name = "resource_configuration_id", nullable=true)
	@ForeignKey(name = "fk_ckan_resource_to_resource_config")
	private ResourceConfiguration resourceConfiguration;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public WorkflowState getWorkflowState() {
		return workflowState;
	}

	public void setWorkflowState(final WorkflowState workflowState) {
		this.workflowState = workflowState;
	}

	public boolean isDownloadable() {
		return this.workflowState.equals(WorkflowState.DETECTED_NEW) || this.workflowState.equals(WorkflowState.DETECTED_REVISION);
	}

	public Date getRevision_timestamp() {
		return revision_timestamp;
	}

	public void setRevision_timestamp(final Date revision_timestamp) {
		this.revision_timestamp = revision_timestamp;
	}

	public String getParentDataset_name() {
		return parentDataset_name;
	}

	public void setParentDataset_name(final String parentDataset_name) {
		this.parentDataset_name = parentDataset_name;
	}

	public String getParentDataset_id() {
		return parentDataset_id;
	}

	public void setParentDataset_id(final String parentDataset_id) {
		this.parentDataset_id = parentDataset_id;
	}

	public String getParentDataset_revision_id() {
		return parentDataset_revision_id;
	}

	public void setParentDataset_revision_id(final String parentDataset_revision_id) {
		this.parentDataset_revision_id = parentDataset_revision_id;
	}

	public Date getParentDataset_revision_timestamp() {
		return parentDataset_revision_timestamp;
	}

	public void setParentDataset_revision_timestamp(final Date parentDataset_revision_timestamp) {
		this.parentDataset_revision_timestamp = parentDataset_revision_timestamp;
	}

	public Date getDetectionDate() {
		return detectionDate;
	}

	public void setDetectionDate(final Date detectionDate) {
		this.detectionDate = detectionDate;
	}

	public Date getDownloadDate() {
		return downloadDate;
	}

	public void setDownloadDate(final Date downloadDate) {
		this.downloadDate = downloadDate;
	}

	public Date getEvaluationDate() {
		return evaluationDate;
	}

	public void setEvaluationDate(final Date evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	public CKANDataset.Type getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(final CKANDataset.Type evaluator) {
		this.evaluator = evaluator;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(final Date importDate) {
		this.importDate = importDate;
	}

	public ValidationReport getValidationReport() {
		return validationReport;
	}

	public void setValidationReport(final ValidationReport validationReport) {
		this.validationReport = validationReport;
	}

	public CKANDataset.Type getImporter() {
		return importer;
	}

	public void setImporter(final CKANDataset.Type importer) {
		this.importer = importer;
	}

	public ResourceConfiguration getResourceConfiguration() {
		return resourceConfiguration;
	}

	public void setResourceConfiguration(ResourceConfiguration resourceConfiguration) {
		this.resourceConfiguration = resourceConfiguration;
	}

	
}
