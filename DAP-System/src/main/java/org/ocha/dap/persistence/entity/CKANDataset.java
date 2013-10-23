package org.ocha.dap.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ckan_dataset")
public class CKANDataset {

	/**
	 * 
	 * @author seustachi
	 * 
	 * 
	 *         PENDING : initial Status, the dataset resource won't be processed
	 *         IGNORED : a data manager flagged this as ignored, the dataset resource won't be processed
	 *         TO_BE_CURATED : all resources from this dataset will be curated (using processor (validator, importer...) according to CKANDataset#Type )
	 */
	public enum Status {
		PENDING, TO_BE_CURATED, IGNORED;
	}

	public enum Type {
		SCRAPPER, DUMMY;
	}

	@Id
	private String name;

	public CKANDataset() {
		super();
	}

	public CKANDataset(final String name) {
		super();
		this.name = name;
		this.status = Status.PENDING;
		this.type = null;
	}

	@Column(name = "status", nullable = false, updatable = true)
	@Enumerated(EnumType.STRING)
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}

	@Column(name = "type", nullable = true, updatable = true)
	@Enumerated(EnumType.STRING)
	private Type type;

	public Type getType() {
		return type;
	}

	public void setType(final Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

}
