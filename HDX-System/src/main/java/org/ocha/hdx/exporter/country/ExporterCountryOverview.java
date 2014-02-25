package org.ocha.hdx.exporter.country;

import org.apache.poi.ss.formula.functions.T;
import org.ocha.hdx.exporter.ExportStrategy;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.QueryData;

/**
 * Exporter for a country overview.
 * @author bmichiels
 *
 */
public class ExporterCountryOverview extends Exporter<ExporterCountryOverview> {
	
	public ExporterCountryOverview(final ExportStrategy<ExporterCountryOverview, T> strategy, final QueryData queryData) {
		super(strategy, queryData);
	}

	@Override
	protected ExporterCountryOverview getType() {
		return this;
	}
}
