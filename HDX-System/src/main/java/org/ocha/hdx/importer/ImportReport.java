package org.ocha.hdx.importer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.ocha.hdx.importer.ImportReport.Status;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;

public class ImportReport implements Serializable {

	public enum Status {
		SUCCESS, ERROR;
	}

	private static final long serialVersionUID = -7350328255412730901L;

	public ImportReport() {
		super();
		this.overallStatus = Status.SUCCESS;
		entries = new ArrayList<>();
	}

	private final Status overallStatus;
	private final List<ImportReportEntry> entries;

	public Status getOverallStatus() {
		return overallStatus;
	}

	public boolean isSuccess() {
		return Status.SUCCESS.equals(overallStatus);
	}

	public void addEntry(final Status status, final String message) {
		entries.add(new ImportReportEntry(status, message));
	}

	public void addEntry(final Status status, final String message, final Indicator indicator) {
		entries.add(new ImportReportEntry(status, message));
	}
}

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
