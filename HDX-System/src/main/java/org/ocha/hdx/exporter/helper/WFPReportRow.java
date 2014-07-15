package org.ocha.hdx.exporter.helper;

import org.ocha.hdx.persistence.entity.curateddata.Entity;

public class WFPReportRow extends MultiIndicatorReportRow {

	private final Entity lowestEntity;
	private final Entity mediumEntity;
	private final Entity highestEntity;

	public WFPReportRow(final Entity lowestEntity) {
		super();
		this.lowestEntity = lowestEntity;
		mediumEntity = lowestEntity.getParent();
		if (mediumEntity != null) {
			highestEntity = mediumEntity.getParent();
		} else {
			highestEntity = null;
		}
	}

	public String getHighestLevelAsString() {
		if (highestEntity != null)
			return highestEntity.getName().getDefaultValue();
		else if (mediumEntity != null) {
			return mediumEntity.getName().getDefaultValue();
		} else {
			return lowestEntity.getName().getDefaultValue();
		}
	}

	public String getMediumLevelAsString() {
		if (highestEntity != null) {
			return mediumEntity.getName().getDefaultValue();
		} else if (mediumEntity != null) {
			return lowestEntity.getName().getDefaultValue();
		} else {
			return "";
		}
	}

	public String getLowestEntityAsString() {
		if (highestEntity != null)
			return lowestEntity.getName().getDefaultValue();
		else {
			return "";
		}
	}

}
