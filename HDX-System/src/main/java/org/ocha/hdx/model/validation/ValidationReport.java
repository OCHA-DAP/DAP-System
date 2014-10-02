package org.ocha.hdx.model.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.ocha.hdx.persistence.entity.ckan.CKANDataset;
import org.ocha.hdx.tools.GSONBuilderWrapper;

public class ValidationReport implements Serializable {

	private static final long serialVersionUID = -6222487518749118335L;

	private String id;
	private String revisionId;

	private ValidationStatus status;

	public boolean isNotInError() {
		return !this.status.equals(ValidationStatus.ERROR);
	}

	private CKANDataset.Type validator;

	private final List<ValidationReportEntry> entries;

	public ValidationReport() {
		super();
		this.entries = new ArrayList<>();
	}

	public ValidationReport(final CKANDataset.Type validator) {
		super();
		this.status = ValidationStatus.SUCCESS;
		this.validator = validator;
		this.entries = new ArrayList<>();
	}

	public ValidationReport(final String id, final String revisionId, final CKANDataset.Type validator) {
		this(validator);
		this.id = id;
		this.revisionId = revisionId;

	}

	public void addEntry(final ValidationStatus newEntryStatus, final String newEntryMessage) {
		// Compare to uses the ordinal of the Enum (based on declaration order)
		if (newEntryStatus.compareTo(this.status) > 0) {
			this.status = newEntryStatus;
		}
		final ValidationReportEntry newEntry = new ValidationReportEntry(newEntryStatus, newEntryMessage);
		this.entries.add(newEntry);
	}

	public void addEntries(final List<ValidationReportEntry> newEntries) {
		for (final ValidationReportEntry newEntry : newEntries) {
			this.addEntry(newEntry.getStatus(), newEntry.getMessage());
		}
	}

	public String getId() {
		return this.id;
	}

	public String getRevisionId() {
		return this.revisionId;
	}

	public ValidationStatus getStatus() {
		return this.status;
	}

	public CKANDataset.Type getValidator() {
		return this.validator;
	}

	public void setValidator(final CKANDataset.Type validator) {
		this.validator = validator;
	}

	public List<ValidationReportEntry> getEntries() {
		return this.entries;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @param revisionId the revisionId to set
	 */
	public void setRevisionId(final String revisionId) {
		this.revisionId = revisionId;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ValidationReport [status=" + this.status + ", validator=" + this.validator + ", entries=" + this.entries + "]";
	}

	public String toJson() {
		return GSONBuilderWrapper.getGSON().toJson(this);
	}

	public static ValidationReport fromJson(final String json) {
		return GSONBuilderWrapper.getGSON().fromJson(json, ValidationReport.class);

	}

}
