package org.ocha.dap.service;

import java.io.File;

import org.ocha.dap.model.ValidationReport;
import org.ocha.dap.model.ValidationStatus;
import org.ocha.dap.persistence.entity.ckan.CKANDataset;
import org.ocha.dap.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.dap.validation.DummyValidator;
import org.ocha.dap.validation.ScraperValidator;

public class FileEvaluatorAndExtractorImpl implements FileEvaluatorAndExtractor {

	private ValidationReport defaultFail(final File file) {
		final ValidationReport report = new ValidationReport(CKANDataset.Type.SCRAPER);

		report.addEntry(ValidationStatus.ERROR, "Mocked evaluator, always failing");
		return report;
	}

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
			return defaultFail(file);
		}
	}

	@Override
	public boolean transformAndImportDataFromResource(final File file, final Type type) {
		switch (type) {
		case DUMMY:
			return false;

		case SCRAPER:
			return false;

		default:
			return false;
		}
	}

}
