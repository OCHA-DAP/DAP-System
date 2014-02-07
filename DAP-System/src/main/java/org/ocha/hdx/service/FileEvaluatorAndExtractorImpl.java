package org.ocha.hdx.service;

import java.io.File;
import java.util.Date;
import java.util.Map.Entry;

import org.ocha.hdx.importer.PreparedData;
import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.importer.ScraperImporter;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.dao.ImportFromCKANDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.hdx.persistence.dao.dictionary.SourceDictionaryDAO;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.hdx.validation.DummyValidator;
import org.ocha.hdx.validation.ScraperValidator;
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
	private CuratedDataService curatedDataService;

	@Override
	public ValidationReport evaluateResource(final File file, final Type type) {
		// FIXME we probably want something else here, map of DAPValidator, or
		// Factory....
		switch (type) {
		case DUMMY:
			return new DummyValidator().evaluateFile(file);

		case SCRAPER:
			return new ScraperValidator().evaluateFile(file);

		default:
			return defaultValidationFail(file);
		}
	}

	@Override
	public boolean transformAndImportDataFromResource(final File file, final Type type, final String resourceId, final String revisionId) {
		// FIXME we probably want something else here, map of DAPImporter, or
		// Factory....
		final PreparedData preparedData;
		switch (type) {
		case DUMMY:
			preparedData = defaultImportFail(file);
			break;
		case SCRAPER:
			final ScraperImporter scraperImporter = new ScraperImporter(sourceDictionaryDAO.getSourceDictionariesByImporter("scraper"));
			for (final Entry<String, String> entry : scraperImporter.getCountryList(file).entrySet()) {
				try {
					curatedDataService.addEntity(entry.getKey(), entry.getValue(), "country");
				} catch (final Exception e) {
					logger.debug(String.format("Not creating country : %s already exist", entry.getKey()));
				}
			}
			preparedData = scraperImporter.prepareDataForImport(file);
			break;
		default:
			preparedData = defaultImportFail(file);
		}
		if (preparedData.isSuccess()) {
			incorporatePreparedDataForImport(preparedData, resourceId, revisionId);
		}

		return preparedData.isSuccess();

	}

	@Override
	public void incorporatePreparedDataForImport(final PreparedData preparedData, final String resourceId, final String revisionId) {
		final ImportFromCKAN importFromCKAN = importFromCKANDAO.createNewImportRecord(resourceId, revisionId, new Date());
		for (final PreparedIndicator preparedIndicator : preparedData.getIndicatorsToImport()) {
			try {
				curatedDataService.addIndicator(preparedIndicator, importFromCKAN);
			} catch (final Exception e) {
				logger.debug(String.format("Error trying to create preparedIndicator : %s", preparedIndicator.toString()));
			}
		}
	}

	private ValidationReport defaultValidationFail(final File file) {
		final ValidationReport report = new ValidationReport(CKANDataset.Type.SCRAPER);

		report.addEntry(ValidationStatus.ERROR, "Mocked evaluator, always failing");
		return report;
	}

	private PreparedData defaultImportFail(final File file) {
		final PreparedData preparedData = new PreparedData(false, null);
		return preparedData;
	}
}
