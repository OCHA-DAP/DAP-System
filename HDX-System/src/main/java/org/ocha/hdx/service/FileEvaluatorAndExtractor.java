package org.ocha.hdx.service;

import java.io.File;
import java.util.List;

import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.importer.report.ImportReport;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;

public interface FileEvaluatorAndExtractor {

	/**
	 * Performs the evaluation of the resource, using the appropriate evaluator (Dummy evaluator, ScrapperEvaluator etc...)
	 *
	 */
	public ValidationReport evaluateResource(final File file, final CKANDataset.Type type);

	/**
	 * Performs the required transformation and import data of the resource, using the appropriate importer (Dummy importer, ScrapperImporter etc...)
	 *
	 * Also responsible for the creation of a record for the import in db (to perform all this is one transaction, ensuring consistency)
	 *
	 * @param report
	 * @param config
	 *
	 */
	public ImportReport transformAndImportDataFromResource(final File file, final CKANDataset.Type type, final String resourceId, final String revisionId, ResourceConfiguration config,
			ValidationReport report);

	/**
	 *
	 * @param preparedIndicators - list of prepared indicators to be saved in the database
	 * @param resourceId
	 * @param revisionId
	 * @return
	 */
	public ImportReport saveReadIndicatorsToDatabase(final List<PreparedIndicator> preparedIndicators, final String resourceId, final String revisionId) ;

}
