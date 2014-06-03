package org.ocha.hdx.importer;

import java.io.Serializable;

public class ImportReport implements Serializable {

	private static final long serialVersionUID = -7350328255412730901L;

	private boolean overallResult;

	public boolean getOverallResult() {
		return overallResult;
	}

	public void setOverallResult(final boolean overallResult) {
		this.overallResult = overallResult;
	}

	public void addEntry() {

	}

}

class ImportReportEntry {

}
