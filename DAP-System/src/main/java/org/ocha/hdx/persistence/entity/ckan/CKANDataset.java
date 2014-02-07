package org.ocha.hdx.persistence.entity.ckan;

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
	 * @author Samuel Eustachi
	 * 
	 * 
	 *         PENDING : initial Status, the dataset resource won't be processed
	 * 
	 *         IGNORED : a data manager flagged this as ignored, the dataset resource won't be processed
	 * 
	 *         TO_BE_CURATED : all resources from this dataset will be curated (using processor (validator, importer...) according to
	 *         CKANDataset#Type )
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

	public CKANDataset(final String name, final String title, final String maintainer, final String maintainer_email, final String author, final String author_email) {
		super();
		this.name = name;
		this.title = title;
		this.maintainer = maintainer;
		this.maintainer_email = maintainer_email;
		this.author = author;
		this.author_email = author_email;
		this.status = Status.PENDING;
		this.type = null;
	}

	@Column(name = "title", nullable = false, updatable = true)
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
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
