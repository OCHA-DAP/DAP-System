package org.ocha.hdx.exporter;

import org.ocha.hdx.service.ExporterService;

/**
 * Data exporter for the HDX Project.
 * All exporters (report generation) should implement this interface.
 * 
 * @author bmichiels
 * 
 * @param <E> What is this exporter working on (currently : File for CSV and TXT, XSSFWorbook for XLSX).
 * @param <QD> The information needed to query and gather the report data.
 */
public interface Exporter<E, QD extends QueryData> {

	/**
	 * Generate the report.
	 * @param exporter The (partial) report generator.
	 * @param queryData The data needed to perform the generation
	 * @return
	 * @throws Exception 
	 */
	public E export(final E exporter, QD queryData) throws Exception;
	
	/**
	 * Getter for the exporter service.
	 * @return
	 */
	public ExporterService getExporterService();

}