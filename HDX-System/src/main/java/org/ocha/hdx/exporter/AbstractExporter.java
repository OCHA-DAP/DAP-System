package org.ocha.hdx.exporter;

public abstract class AbstractExporter<E, QD extends QueryData> implements Exporter<E, QD> {

	protected final Exporter<E, QD> exporter;

	public AbstractExporter() {
		super();
		exporter = null;
	}

	public AbstractExporter(final Exporter<E, QD> exporter) {
		super();
		this.exporter = exporter;
	}

	@Override
	public E export(final E incomingData, final QD queryData) {
		if (exporter != null) {
			return exporter.export(incomingData, queryData);
		} else {
			return incomingData;
		}
	}

}
