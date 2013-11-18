package org.ocha.dap.service;

import java.io.File;

import org.ocha.dap.model.ValidationReport;
import org.ocha.dap.persistence.entity.ckan.CKANDataset;

public interface FileEvaluatorAndExtractor {

	/**
	 * Performs the evaluation of the resource, using the appropriate evaluator (Dummy evaluator, ScrapperEvaluator etc...)
	 * 
	 */
	public ValidationReport evaluateResource(final File file, final CKANDataset.Type type);

	/**
	 * Performs the required transformation and import data of the resource, using the appropriate importer (Dummy importer,
	 * ScrapperImporter etc...)
	 * 
	 */
	public boolean transformAndImportDataFromResource(final File file, final CKANDataset.Type type);

}
