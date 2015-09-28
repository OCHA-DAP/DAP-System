package org.ocha.hdx.persistence.entity.ckan;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;

@Entity
@Table(name = "ckan_resource")
public class CKANResource {

	public enum WorkflowState {
		DETECTED_NEW, DETECTED_REVISION, OUTDATED, DOWNLOADED, FILE_PRE_VALIDATION_SUCCESS, FILE_PRE_VALIDATION_FAIL, IMPORTING, IMPORT_SUCCESS, IMPORT_FAIL;
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
			return this.id;
		}

		public String getRevision_id() {
			return this.revision_id;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
			result = prime * result + ((this.revision_id == null) ? 0 : this.revision_id.hashCode());
			return result;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (this.getClass() != obj.getClass()) {
				return false;
			}
			final Id other = (Id) obj;
			if (this.id == null) {
				if (other.id != null) {
					return false;
				}
			} else if (!this.id.equals(other.id)) {
				return false;
			}
			if (this.revision_id == null) {
				if (other.revision_id != null) {
					return false;
				}
			} else if (!this.revision_id.equals(other.revision_id)) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return "Id [id=" + this.id + ", revision_id=" + this.revision_id + "]";
		}

	}

	public CKANResource() {
		super();
	}

	public CKANResource(final String id, final String revision_id, final Type type, final boolean isNew, final String parentDataset_name) {
		this.id.id = id;
		this.id.revision_id = revision_id;
		this.type = type;
		if (isNew) {
			this.workflowState = WorkflowState.DETECTED_NEW;
		} else {
			this.workflowState = WorkflowState.DETECTED_REVISION;
		}
		this.parentDataset_name = parentDataset_name;
	}

	/**
	 * Constructor that skips the huge blob reports. Used in HQL.
	 * 
	 * @param name
	 * @param workflowState
	 * @param revision_timestamp
	 * @param parentDataset_name
	 * @param parentDataset_id
	 * @param parentDataset_revision_id
	 * @param parentDataset_revision_timestamp
	 * @param detectionDate
	 * @param downloadDate
	 * @param evaluationDate
	 * @param evaluator
	 * @param importDate
	 * @param importer
	 * @param resourceConfiguration
	 */
	public CKANResource(final Id id, final Type type, final String name, final WorkflowState workflowState, final Date revision_timestamp, final String parentDataset_name,
			final String parentDataset_id, final String parentDataset_revision_id, final Date parentDataset_revision_timestamp, final Date detectionDate, final Date downloadDate,
			final Date evaluationDate, final Type evaluator, final Date importDate, final Type importer, final ResourceConfiguration resourceConfiguration) {

		this.id.id = id.getId();
		this.id.revision_id = id.getRevision_id();

		this.type = type;

		this.name = name;
		this.workflowState = workflowState;
		this.revision_timestamp = revision_timestamp;
		this.parentDataset_name = parentDataset_name;
		this.parentDataset_id = parentDataset_id;
		this.parentDataset_revision_id = parentDataset_revision_id;
		this.parentDataset_revision_timestamp = parentDataset_revision_timestamp;
		this.detectionDate = detectionDate;
		this.downloadDate = downloadDate;
		this.evaluationDate = evaluationDate;
		this.evaluator = evaluator;
		this.importDate = importDate;
		this.importer = importer;
		this.resourceConfiguration = resourceConfiguration;
	}

	@EmbeddedId
	private final Id id = new Id();

	public Id getId() {
		return this.id;
	}

	@Column(name = "type", nullable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private Type type;

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

	@Column(name = "parentDataset_revision_timestamp", columnDefinition = "timestamp", nullable = true, updatable = false)
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

	@Column(name = "importer", nullable = true, updatable = true)
	@Enumerated(EnumType.STRING)
	private Type importer;

	@ManyToOne(optional = true)
	@JoinColumn(name = "resource_configuration_id", nullable = false)
	@ForeignKey(name = "fk_ckan_resource_to_resource_config")
	private ResourceConfiguration resourceConfiguration;

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public WorkflowState getWorkflowState() {
		return this.workflowState;
	}

	public void setWorkflowState(final WorkflowState workflowState) {
		this.workflowState = workflowState;
	}

	public boolean isDownloadable() {
		return this.workflowState.equals(WorkflowState.DETECTED_NEW) || this.workflowState.equals(WorkflowState.DETECTED_REVISION);
	}

	public Date getRevision_timestamp() {
		return this.revision_timestamp;
	}

	public void setRevision_timestamp(final Date revision_timestamp) {
		this.revision_timestamp = revision_timestamp;
	}

	public String getParentDataset_name() {
		return this.parentDataset_name;
	}

	public void setParentDataset_name(final String parentDataset_name) {
		this.parentDataset_name = parentDataset_name;
	}

	public String getParentDataset_id() {
		return this.parentDataset_id;
	}

	public void setParentDataset_id(final String parentDataset_id) {
		this.parentDataset_id = parentDataset_id;
	}

	public String getParentDataset_revision_id() {
		return this.parentDataset_revision_id;
	}

	public void setParentDataset_revision_id(final String parentDataset_revision_id) {
		this.parentDataset_revision_id = parentDataset_revision_id;
	}

	public Date getParentDataset_revision_timestamp() {
		return this.parentDataset_revision_timestamp;
	}

	public void setParentDataset_revision_timestamp(final Date parentDataset_revision_timestamp) {
		this.parentDataset_revision_timestamp = parentDataset_revision_timestamp;
	}

	public Date getDetectionDate() {
		return this.detectionDate;
	}

	public void setDetectionDate(final Date detectionDate) {
		this.detectionDate = detectionDate;
	}

	public Date getDownloadDate() {
		return this.downloadDate;
	}

	public void setDownloadDate(final Date downloadDate) {
		this.downloadDate = downloadDate;
	}

	public Date getEvaluationDate() {
		return this.evaluationDate;
	}

	public void setEvaluationDate(final Date evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	public CKANDataset.Type getEvaluator() {
		return this.evaluator;
	}

	public void setEvaluator(final CKANDataset.Type evaluator) {
		this.evaluator = evaluator;
	}

	public Date getImportDate() {
		return this.importDate;
	}

	public void setImportDate(final Date importDate) {
		this.importDate = importDate;
	}

	public CKANDataset.Type getImporter() {
		return this.importer;
	}

	public void setImporter(final CKANDataset.Type importer) {
		this.importer = importer;
	}

	public ResourceConfiguration getResourceConfiguration() {
		return this.resourceConfiguration;
	}

	public void setResourceConfiguration(final ResourceConfiguration resourceConfiguration) {
		this.resourceConfiguration = resourceConfiguration;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(final Type type) {
		this.type = type;
	}

}
