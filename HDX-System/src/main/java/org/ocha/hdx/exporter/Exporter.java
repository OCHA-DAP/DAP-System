package org.ocha.hdx.exporter;

/**
 * Data exporter for the HDX Project.
 * 
 * @author bmichiels
 * 
 * @param <E>
 */
public interface Exporter<E, QD extends QueryData> {

	public E export(final E incomingData, QD queryData);

}