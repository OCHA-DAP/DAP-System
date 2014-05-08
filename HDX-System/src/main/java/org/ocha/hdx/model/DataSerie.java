package org.ocha.hdx.model;

import org.apache.log4j.Logger;
import org.ocha.hdx.persistence.dao.view.IndicatorTypeOverviewDAOImpl;

public class DataSerie implements Comparable<DataSerie> {

	Logger logger = Logger.getLogger(IndicatorTypeOverviewDAOImpl.class);

	private final String indicatorCode;
	private final String sourceCode;

	public DataSerie(final String indicatorCode, final String sourceCode) {
		super();
		logger.debug("Source code param [" + sourceCode + "]");
		this.indicatorCode = indicatorCode;
		this.sourceCode = sourceCode;
	}

	public String getIndicatorCode() {
		return indicatorCode;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	@Override
	public int compareTo(final DataSerie o) {
		final int indicatorTypeComparison = indicatorCode.compareTo(o.indicatorCode);
		if (indicatorTypeComparison != 0) {
			return indicatorTypeComparison;
		} else {
			return sourceCode.compareTo(o.sourceCode);
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((indicatorCode == null) ? 0 : indicatorCode.hashCode());
		result = (prime * result) + ((sourceCode == null) ? 0 : sourceCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DataSerie other = (DataSerie) obj;
		if (indicatorCode == null) {
			if (other.indicatorCode != null) {
				return false;
			}
		} else if (!indicatorCode.equals(other.indicatorCode)) {
			return false;
		}
		if (sourceCode == null) {
			if (other.sourceCode != null) {
				return false;
			}
		} else if (!sourceCode.equals(other.sourceCode)) {
			return false;
		}
		return true;
	}
}
