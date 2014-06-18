package org.ocha.hdx.importer.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.ocha.hdx.persistence.entity.curateddata.Indicator;

public class ImportReport implements Serializable {

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

	public List<ImportReportEntry> getEntries() {
		return entries;
	}
}
