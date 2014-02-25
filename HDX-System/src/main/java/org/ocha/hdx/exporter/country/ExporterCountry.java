package org.ocha.hdx.exporter.country;

import org.apache.poi.ss.formula.functions.T;
import org.ocha.hdx.exporter.ExportStrategy;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.QueryData;

/**
 * Exporter for a country.
 * @author bmichiels
 *
 */
public class ExporterCountry extends Exporter<ExporterCountry> {
	public ExporterCountry(final ExportStrategy<ExporterCountry, T> strategy, final QueryData queryData) {
		super(strategy, queryData);
	}

	@Override
	protected ExporterCountry getType() {
		return this;
	}
}
