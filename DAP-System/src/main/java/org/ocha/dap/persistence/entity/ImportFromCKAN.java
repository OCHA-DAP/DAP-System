package org.ocha.dap.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Samuel Eustachi
 * 
 */
@Entity
@Table(name = "import_from_ckan")
@SequenceGenerator(name = "import_from_ckan_seq", sequenceName = "import_from_ckan_seq")
public class ImportFromCKAN {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "import_from_ckan_seq")
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "resource_id", nullable = false, updatable = false)
	private String resourceId;

	@Column(name = "revision_id", nullable = false, updatable = false)
	private String revisionId;

	@Column(name = "timestamp", columnDefinition = "timestamp", nullable = false, updatable = false)
	private Date timestamp;

	public ImportFromCKAN(final String resourceId, final String revisionId, final Date timestamp) {
		super();
		this.resourceId = resourceId;
		this.revisionId = revisionId;
		this.timestamp = timestamp;
	}

	public ImportFromCKAN() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
	}

	public String getRevisionId() {
		return revisionId;
	}

	public void setRevisionId(final String revisionId) {
		this.revisionId = revisionId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
	}

}
