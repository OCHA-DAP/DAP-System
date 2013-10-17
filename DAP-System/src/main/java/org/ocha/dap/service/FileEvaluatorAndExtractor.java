package org.ocha.dap.service;

public interface FileEvaluatorAndExtractor {
	
	/**
	 * performs a dummy evaluation of a CSV file
	 * 
	 * for this example, we assume we got some percentage for some categories, per country
	 * all countries sum should be 100. 
	 * 
	 * @return true if all countries have a sum of 100, false otherwise
	 * 
	 */
	public boolean evaluateDummyCSVFile(final String id, final String revision_id);

}
