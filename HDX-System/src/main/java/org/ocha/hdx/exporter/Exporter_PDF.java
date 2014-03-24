package org.ocha.hdx.exporter;

import java.io.File;

import org.ocha.hdx.service.ExporterService;

/**
 * Abstract class for PDF report generation.
 * This class should be implemented for every report needing an PDF export.
 * 
 * Exporters follow the Decorator pattern (see constructors).
 * 
 * @author bmichiels
 *
 * @param <QD> The information needed to query and gather the report data.
 */
public abstract class Exporter_PDF<QD extends QueryData> extends AbstractExporter<File, QD> {

	/**
	 * This constructor is for the final exporter of a chain of exporters.
	 * It propagates the exporter service to its predecessors for proper generation.
	 * @param exporterService
	 */
	public Exporter_PDF(final ExporterService exporterService) {
		super(exporterService);
	}

	/**
	 * This constructor takes the following exporter in the global export chain.
	 * @param exporter
	 */
	public Exporter_PDF(final Exporter<File, QD> exporter) {
		super(exporter);
	}

}
