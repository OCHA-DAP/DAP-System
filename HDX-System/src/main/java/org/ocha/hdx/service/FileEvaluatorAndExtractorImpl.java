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
import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.dao.ImportFromCKANDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorDAOImpl.ImportValueStatus;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.dictionary.SourceDictionaryDAO;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
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
			return this.defaultValidationFail(file);
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
			final ScraperValidatingImporter importer = new ScraperValidatingImporter(this.sourceDictionaryDAO.getSourceDictionariesByResourceConfiguration(config), config, this.validatorCreators,
					this.preValidatorCreators, validationReport, this.indicatorCreationService);
			this.creatingMissingEntities(file, importer);
			preparedData = importer.prepareDataForImport(file);
		}
			break;
		case WFP: {
			final WfpImporter importer = new WfpImporter(this.curatedDataService);
			preparedData = importer.prepareDataForImport(file);
		}
			break;
		default:
			preparedData = this.defaultImportFail(file);
		}
		if (preparedData.isSuccess()) {
			logger.info(String.format("Import successful, about to persist %d values", preparedData.getIndicatorsToImport().size()));
			// FIXME here we used to run importer.validations, and this should as well populate one of the report
			final ImportReport importReport = this.saveReadIndicatorsToDatabase(preparedData.getIndicatorsToImport(), resourceId, revisionId);
			return importReport;
		} else {
			logger.info("Import failed");
			final ImportReport importReport = new ImportReport();
			importReport.setErrorMessage("Could not perform import, IMPORTER ran additional validations and found errors. See Validation Report for details");
			return importReport;
		}

	}

	@Override
	public ImportReport saveReadIndicatorsToDatabase(final List<PreparedIndicator> preparedIndicators, final String resourceId, final String revisionId) {
		final ImportReport importReport = new ImportReport();

		final ImportFromCKAN importFromCKAN = this.importFromCKANDAO.createNewImportRecord(resourceId, revisionId, new Date());
		for (final PreparedIndicator preparedIndicator : preparedIndicators) {
			final ImportValueStatus status = this.curatedDataService.updateIndicatorIfNecessary(preparedIndicator, importFromCKAN);
			try {
				switch (status) {
				case NOTHING_TO_DO:
					importReport.addAlreadyExistingRecord(new DataSerie(preparedIndicator.getIndicatorTypeCode(), preparedIndicator.getSourceCode()));
					break;
				case UPDATED:
					importReport.addUpdatedRecord(new DataSerie(preparedIndicator.getIndicatorTypeCode(), preparedIndicator.getSourceCode()));
					break;
				case NEEDS_INSERT:
					this.curatedDataService.createIndicator(this.indicatorCreationService.createIndicator(preparedIndicator), importFromCKAN);
					importReport.addNewRecord(new DataSerie(preparedIndicator.getIndicatorTypeCode(), preparedIndicator.getSourceCode()));
					break;
				default:
					break;

				}

			} catch (final Exception e) {
				importReport.addRecordInError(new DataSerie(preparedIndicator.getIndicatorTypeCode(), preparedIndicator.getSourceCode()));
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
				this.curatedDataService.createEntity(entry.getKey(), entry.getValue(), "country", null);
			} catch (final Exception e) {
				logger.trace(String.format("Not creating country : %s already exist", entry.getKey()));
			}
		}
	}
}
