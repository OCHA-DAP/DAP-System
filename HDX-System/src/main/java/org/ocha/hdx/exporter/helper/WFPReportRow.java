package org.ocha.hdx.exporter.helper;

import org.ocha.hdx.persistence.entity.curateddata.Entity;

public class WFPReportRow extends MultiIndicatorReportRow {

	private final Entity lowestEntity;

	public WFPReportRow(final Entity lowestEntity) {
		super();
		this.lowestEntity = lowestEntity;
	}

	public String getHighestLevelAsString() {
		final Entity mediumEntity = lowestEntity.getParent();
		if (mediumEntity != null) {
			final Entity highestEntity = mediumEntity.getParent();
			if (highestEntity != null) {
				return highestEntity.getName().getDefaultValue();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public String getMediumLevelAsString() {
		final Entity mediumEntity = lowestEntity.getParent();
		if (mediumEntity != null) {
			return mediumEntity.getName().getDefaultValue();
		} else {
			return "";
		}
	}

	public String getLowestEntityAsString() {
		return lowestEntity.getName().getDefaultValue();
	}

}
