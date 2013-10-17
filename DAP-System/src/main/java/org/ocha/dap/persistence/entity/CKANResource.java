package org.ocha.dap.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ckan_resource")
public class CKANResource {

	public enum WorkflowState {
		DETECTED_NEW, DETECTED_REVISION, OUTDATED, DOWNLOADED;
	}
	
	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = 1L;
		
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

	public CKANResource(final String id, final String revision_id, final boolean isNew) {
		this.id.id = id;
		this.id.revision_id = revision_id;
		if(isNew){
			this.workflowState = WorkflowState.DETECTED_NEW;
		}else{
			this.workflowState = WorkflowState.DETECTED_REVISION;
		}
	}

	@EmbeddedId
	private final Id id = new Id();
	
	public Id getId() {
		return id;
	}

	@Column(name = "workflowState", nullable = false, updatable = true)
	private WorkflowState workflowState;

	@Column(name = "revision_timestamp", columnDefinition = "timestamp", nullable = false, updatable = false)
	private Date revision_timestamp;

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

	public WorkflowState getWorkflowState() {
		return workflowState;
	}

	public void setWorkflowState(final WorkflowState workflowState) {
		this.workflowState = workflowState;
	}
	
	public boolean isDownloadable(){
		return this.workflowState.equals(WorkflowState.DETECTED_NEW) || this.workflowState.equals(WorkflowState.DETECTED_REVISION);
	}

	public Date getRevision_timestamp() {
		return revision_timestamp;
	}

	public void setRevision_timestamp(final Date revision_timestamp) {
		this.revision_timestamp = revision_timestamp;
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

}
