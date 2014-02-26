package org.ocha.hdx.exporter;

public abstract class AbstractExporter<E> implements Exporter<E> {

	protected final Exporter<E> exporter;

	public AbstractExporter() {
		super();
		exporter = null;
	}

	public AbstractExporter(final Exporter<E> exporter) {
		super();
		this.exporter = exporter;
	}

	@Override
	public E export(final E incomingData, final QueryData queryData) {
		if (exporter != null) {
			return exporter.export(incomingData, queryData);
		} else {
			return incomingData;
		}
	}

}
