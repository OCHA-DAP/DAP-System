package org.ocha.hdx.importer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.ocha.hdx.importer.ImportReport.Status;

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
}

class ImportReportEntry {

	private final Status status;
	private final String message;

	public ImportReportEntry(final Status status, final String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public Status getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
