package org.ocha.hdx.service;

import java.io.File;
import java.util.List;

import org.ocha.hdx.importer.PreparedData;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;

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
	 * Also responsible for the creation of a record for the import in db (to perform all this is one transaction, ensuring consistency)
	 * @param report
	 * @param config
	 *
	 */
	public boolean transformAndImportDataFromResource(final File file, final CKANDataset.Type type, final String resourceId, final String revisionId, ResourceConfiguration config, ValidationReport report);

	/**
	 * @deprecated use {@link FileEvaluatorAndExtractor#saveReadIndicatorsToDatabase(Indicator, String, String)} instead
	 */
	@Deprecated
	public void incorporatePreparedDataForImport(final PreparedData preparedData, final String resourceId, final String revisionId);

	public void saveReadIndicatorsToDatabase(final List<Indicator> indicators, final String resourceId, final String revisionId);

}
