package org.ocha.dap.importer;

import java.util.List;

public class PreparedData {

	private final boolean success;

	private final List<PreparedIndicator> indicatorsToImport;

	public PreparedData(final boolean success, final List<PreparedIndicator> indicatorsToImport) {
		super();
		this.success = success;
		this.indicatorsToImport = indicatorsToImport;
	}

	public boolean isSuccess() {
		return success;
	}

	public List<PreparedIndicator> getIndicatorsToImport() {
		return indicatorsToImport;
	}

}