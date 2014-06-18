package org.ocha.hdx.importer.report;

import java.io.Serializable;

import org.ocha.hdx.persistence.entity.curateddata.Indicator;

class ImportReportEntry implements Serializable {

	private static final long serialVersionUID = 4090405946027327873L;

	private final Status status;
	private final String message;
	private final Indicator relatedIndicator;

	public ImportReportEntry(final Status status, final String message) {
		super();
		this.status = status;
		this.message = message;
		this.relatedIndicator = null;
	}

	public ImportReportEntry(final Status status, final String message, final Indicator relatedIndicator) {
		super();
		this.status = status;
		this.message = message;
		this.relatedIndicator = relatedIndicator;
	}

	public Status getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Indicator getRelatedIndicator() {
		return relatedIndicator;
	}

}