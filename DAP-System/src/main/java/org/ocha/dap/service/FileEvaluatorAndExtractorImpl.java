package org.ocha.dap.service;

import java.io.File;
import java.util.Date;

import org.ocha.dap.importer.PreparedData;
import org.ocha.dap.importer.PreparedIndicator;
import org.ocha.dap.importer.ScraperImporter;
import org.ocha.dap.model.validation.ValidationReport;
import org.ocha.dap.model.validation.ValidationStatus;
import org.ocha.dap.persistence.dao.ImportFromCKANDAO;
import org.ocha.dap.persistence.entity.ImportFromCKAN;
import org.ocha.dap.persistence.entity.ckan.CKANDataset;
import org.ocha.dap.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.dap.validation.DummyValidator;
import org.ocha.dap.validation.ScraperValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class FileEvaluatorAndExtractorImpl implements FileEvaluatorAndExtractor {

	@Autowired
	private ImportFromCKANDAO importFromCKANDAO;

	@Autowired
	private CuratedDataService curatedDataService;

	@Override
	public ValidationReport evaluateResource(final File file, final Type type) {
		// FIXME we probably want something else here, map of DAPValidator, or Factory....
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
	@Transactional
	public boolean transformAndImportDataFromResource(final File file, final Type type, final String resourceId, final String revisionId) {
		// FIXME we probably want something else here, map of DAPImporter, or Factory....
		final PreparedData preparedData;
		switch (type) {
		case DUMMY:
			preparedData = defaultImportFail(file);
			break;
		case SCRAPER:
			preparedData = new ScraperImporter().prepareDataForImport(file);
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
			curatedDataService.addIndicator(preparedIndicator, importFromCKAN);
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
