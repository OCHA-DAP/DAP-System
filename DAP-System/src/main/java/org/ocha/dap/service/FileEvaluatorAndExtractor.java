package org.ocha.dap.service;

import java.io.File;

import org.ocha.dap.persistence.entity.CKANDataset;

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
	public boolean evaluateDummyCSVFile(File file);
	
	/**
	 * In order to evaluate a file, we must know its type (to use the appropriate evaluator
	 * The Type is defined on the Dataset level)
	 * 
	 * @return
	 */
	public CKANDataset.Type getTypeForFile(final String id, final String revision_id);
	
	/**
	 * Performs the evaluation of the resource, using the appropriate evaluator (Dummy evaluator, ScrapperEvaluator etc...)
	 * 
	 * @return
	 */
	public boolean evaluateResource(final String id, final String revision_id, final CKANDataset.Type type);
	
	/**
	 * Performs the required transformation and import data of the resource, using the appropriate importer (Dummy importer, ScrapperImporter etc...)
	 * 
	 * @return
	 */
	public boolean transformAndImportDataFromResource(final String id, final String revision_id, final CKANDataset.Type type);

}
