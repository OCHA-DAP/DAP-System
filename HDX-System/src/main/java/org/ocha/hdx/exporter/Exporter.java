package org.ocha.hdx.exporter;

import org.ocha.hdx.service.ExporterService;

/**
 * Data exporter for the HDX Project.
 * 
 * @author bmichiels
 * 
 * @param <E>
 */
public interface Exporter<E, QD extends QueryData> {

	public E export(final E incomingData, QD queryData);
	
	public ExporterService getExporterService();

}