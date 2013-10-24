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
		SCRAPER, DUMMY;
	}

	@Id
	private String name;

	public CKANDataset() {
		super();
	}

	public CKANDataset(final String name, final String maintainer, final String maintainer_email, final String author, final String author_email) {
		super();
		this.name = name;
		this.maintainer = maintainer;
		this.maintainer_email = maintainer_email;
		this.author = author;
		this.author_email = author_email;
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
	
	@Column(name = "maintainer", nullable = true, updatable = true)
	private String maintainer;
	@Column(name = "maintainer_email", nullable = true, updatable = true)
	private String maintainer_email;
	@Column(name = "author", nullable = true, updatable = true)
	private String author;
	@Column(name = "author_email", nullable = true, updatable = true)
	private String author_email;

	public String getMaintainer() {
		return maintainer;
	}

	public String getMaintainer_email() {
		return maintainer_email;
	}


	public String getAuthor() {
		return author;
	}


	public String getAuthor_email() {
		return author_email;
	}


}
