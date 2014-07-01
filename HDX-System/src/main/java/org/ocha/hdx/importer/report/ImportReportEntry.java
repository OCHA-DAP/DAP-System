package org.ocha.hdx.importer.report;

import java.io.Serializable;

public class ImportReportEntry implements Serializable {

	private static final long serialVersionUID = 4090405946027327873L;

	private final ImportStatus status;
	private final String message;

	public ImportReportEntry(final ImportStatus status, final String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ImportStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}