package org.ocha.hdx.exporter;

import org.ocha.hdx.service.ExporterService;

public abstract class AbstractExporter<E, QD extends QueryData> implements Exporter<E, QD> {

	protected final Exporter<E, QD> exporter;
	protected final ExporterService exporterService;

	public AbstractExporter(final ExporterService exporterService) {
		super();
		exporter = null;
		this.exporterService = exporterService;
	}

	public AbstractExporter(final Exporter<E, QD> exporter) {
		super();
		this.exporter = exporter;
		this.exporterService = exporter.getExporterService();
	}

	@Override
	public E export(final E incomingData, final QD queryData) {
		if (exporter != null) {
			return exporter.export(incomingData, queryData);
		} else {
			return incomingData;
		}
	}

	@Override
	public ExporterService getExporterService() {
		return exporterService;
	}
}
