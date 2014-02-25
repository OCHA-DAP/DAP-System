package org.ocha.hdx.exporter;

import org.apache.poi.ss.formula.functions.T;

/**
 * Data exporter for the HDX Project.
 * @author bmichiels
 *
 * @param <E>
 */
public abstract class Exporter<E extends Exporter<E>> {

	private final QueryData queryData;
	private final ExportStrategy<E, T> strategy;

	public Exporter(final ExportStrategy<E, T> strategy, final QueryData queryData) {
		this.strategy = strategy;
		this.queryData = queryData;
	}

	protected abstract E getType();

	public QueryData getQueryData() {
		return queryData;
	}

	public final Object export() {
		return strategy.export(getType());
	}
}