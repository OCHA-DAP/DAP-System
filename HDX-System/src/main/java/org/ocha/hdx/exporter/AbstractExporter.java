package org.ocha.hdx.exporter;

import org.ocha.hdx.service.ExporterService;

/**
 * Abstract exporter for report generation.
 * 
 * @author bmichiels
 * 
 * @param <E>
 *            What is this exporter working on (currently : File for PDF, XSSFWorbook for XLSX).
 * @param <QD>
 *            The information needed to query and gather the report data.
 */
public abstract class AbstractExporter<E, QD extends QueryData> implements Exporter<E, QD> {

	/**
	 * The next exporter in the global export chain (if any).
	 * If null, then the current exporter is the final exporter in the global export chain. 
	 */
	protected final Exporter<E, QD> exporter;
	
	/**
	 * The service needed to perform the report generation.
	 */
	protected final ExporterService exporterService;

	/**
	 * This constructor is for the final exporter of a chain of exporters. It propagates the exporter service to its predecessors for proper generation.
	 * 
	 * @param exporterService
	 */
	public AbstractExporter(final ExporterService exporterService) {
		super();
		exporter = null;
		this.exporterService = exporterService;
	}

	/**
	 * This constructor takes the following exporter in the global export chain.
	 * 
	 * @param exporter
	 */
	public AbstractExporter(final Exporter<E, QD> exporter) {
		super();
		this.exporter = exporter;
		this.exporterService = exporter.getExporterService();
	}

	/**
	 * Generate the report.
	 */
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
