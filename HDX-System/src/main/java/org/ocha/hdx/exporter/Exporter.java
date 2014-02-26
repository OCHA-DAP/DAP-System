package org.ocha.hdx.exporter;

/**
 * Data exporter for the HDX Project.
 * 
 * @author bmichiels
 * 
 * @param <E>
 */
public interface Exporter<E> {

	public E export(E incomingData, QueryData queryData);

}