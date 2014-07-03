package org.ocha.hdx.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.ocha.hdx.config.DummyConfigurationCreator;
import org.ocha.hdx.importer.HDXWithCountryListImporter;
import org.ocha.hdx.importer.PreparedData;
import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.importer.ScraperValidatingImporter;
import org.ocha.hdx.importer.WfpImporter;
import org.ocha.hdx.importer.report.ImportReport;
import org.ocha.hdx.importer.report.ImportStatus;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.dao.ImportFromCKANDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.dictionary.SourceDictionaryDAO;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.validation.ScraperValidator;
import org.ocha.hdx.validation.WfpValidator;
import org.ocha.hdx.validation.itemvalidator.IValidatorCreator;
import org.ocha.hdx.validation.prevalidator.IPreValidatorCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class FileEvaluatorAndExtractorImpl implements FileEvaluatorAndExtractor {

	private static Logger logger = LoggerFactory.getLogger(FileEvaluatorAndExtractorImpl.class);

	@Autowired
	private ImportFromCKANDAO importFromCKANDAO;

	@Autowired
	private EntityDAO entityDAO;

	@Autowired
	private EntityTypeDAO entityTypeDAO;

	@Autowired
	private SourceDictionaryDAO sourceDictionaryDAO;

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	@Autowired
	private SourceDAO sourceDAO;

	@Autowired
	private CuratedDataService curatedDataService;

	@Autowired
	private IndicatorCreationService indicatorCreationService;

	@Resource
	private List<IValidatorCreator> validatorCreators;

	@Resource
	private List<IPreValidatorCreator> preValidatorCreators;

	@Autowired
	private DummyConfigurationCreator dummyConfigurationCreator;

	@Override
	public ValidationReport evaluateResource(final File file, final Type type) {
		// FIXME we probably want something else here, map of HDXValidator, or
		// Factory....
		switch (type) {
		case WFP:
			return new WfpValidator().evaluateFile(file);
		case SCRAPER_CONFIGURABLE:
			final ValidationReport validationReport = new ScraperValidator().evaluateFile(file);
			// since we're using the same validator for both types, we're setting the correct type afterwards
			validationReport.setValidator(type);
			return validationReport;

		default:
			return defaultValidationFail(file);
		}
	}

	@Override
	public ImportReport transformAndImportDataFromResource(final File file, final Type type, final String resourceId, final String revisionId, final ResourceConfiguration config,
			final ValidationReport validationReport) {

		// FIXME we probably want something else here, map of HDXImporter, or
		// Factory....
		final PreparedData preparedData;
		switch (type) {
		case SCRAPER_CONFIGURABLE: {
			final ScraperValidatingImporter importer = new ScraperValidatingImporter(sourceDictionaryDAO.getSourceDictionariesByResourceConfiguration(config), config, validatorCreators,
					preValidatorCreators, validationReport, indicatorCreationService);
			creatingMissingEntities(file, importer);
			preparedData = importer.prepareDataForImport(file);
		}
			break;
		case WFP: {
			final WfpImporter importer = new WfpImporter(curatedDataService);
			preparedData = importer.prepareDataForImport(file);
		}
			break;
		default:
			preparedData = defaultImportFail(file);
		}
		if (preparedData.isSuccess()) {
			logger.info(String.format("Import successful, about to persist %d values", preparedData.getIndicatorsToImport().size()));
			final List<Indicator> indicators = indicatorCreationService.createIndicators(preparedData.getIndicatorsToImport());
			// FIXME here we used to run importer.validations, and this should as well populate one of the report
			final ImportReport importReport = saveReadIndicatorsToDatabase(indicators, resourceId, revisionId);
			return importReport;
		} else {
			logger.info("Import failed");
			final ImportReport importReport = new ImportReport();
			importReport.addEntry(ImportStatus.ERROR, "Could not perform import, IMPORTER ran additional validations and found errors. See Validation Report for details");
			return importReport;
		}

	}

	/**
	 * see {@link FileEvaluatorAndExtractor#incorporatePreparedDataForImport(PreparedData, String, String)}
	 */
	@Override
	@Deprecated
	public void incorporatePreparedDataForImport(final PreparedData preparedData, final String resourceId, final String revisionId) {
		final ImportFromCKAN importFromCKAN = importFromCKANDAO.createNewImportRecord(resourceId, revisionId, new Date());
		for (final PreparedIndicator preparedIndicator : preparedData.getIndicatorsToImport()) {
			try {
				curatedDataService.createIndicator(preparedIndicator, importFromCKAN);
			} catch (final Exception e) {
				logger.debug(String.format("Error trying to create preparedIndicator : %s", preparedIndicator.toString()));
			}
		}
	}

	private ImportReport saveReadIndicatorsToDatabase(final List<Indicator> indicators, final String resourceId, final String revisionId) {
		final ImportReport importReport = new ImportReport();

		final ImportFromCKAN importFromCKAN = importFromCKANDAO.createNewImportRecord(resourceId, revisionId, new Date());
		for (final Indicator indicator : indicators) {
			try {
				curatedDataService.createIndicator(indicator, importFromCKAN);
				importReport.addEntry(ImportStatus.SUCCESS,
						String.format("Successfully created indicator for source : %s and type : %s", indicator.getSource().getCode(), indicator.getType().getCode()));
			} catch (final Exception e) {
				logger.trace(String.format("Error trying to save Indicator : %s", indicator.toString()));
				importReport.addEntry(ImportStatus.ERROR, String.format("Failed to create indicator for source : %s and type : %s", indicator.getSource().getCode(), indicator.getType().getCode()));
			}
		}

		return importReport;
	}

	private ValidationReport defaultValidationFail(final File file) {
		final ValidationReport report = new ValidationReport(CKANDataset.Type.WFP);

		report.addEntry(ValidationStatus.ERROR, "Mocked evaluator, always failing");
		return report;
	}

	private PreparedData defaultImportFail(final File file) {
		final PreparedData preparedData = new PreparedData(false, null);
		return preparedData;
	}

	private void creatingMissingEntities(final File file, final HDXWithCountryListImporter importer) {
		for (final Entry<String, String> entry : importer.getCountryList(file).entrySet()) {
			try {
				curatedDataService.createEntity(entry.getKey(), entry.getValue(), "country", null);
			} catch (final Exception e) {
				logger.trace(String.format("Not creating country : %s already exist", entry.getKey()));
			}
		}
	}
}
