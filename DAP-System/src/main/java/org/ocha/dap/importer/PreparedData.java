package org.ocha.dap.importer;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.Indicator;

public class PreparedData {

	private final boolean success;

	private final List<Indicator> indicatorsToImport;

	public PreparedData(final boolean success, final List<Indicator> indicatorsToImport) {
		super();
		this.success = success;
		this.indicatorsToImport = indicatorsToImport;
	}

	public boolean isSuccess() {
		return success;
	}

	public List<Indicator> getIndicatorsToImport() {
		return indicatorsToImport;
	}

}
