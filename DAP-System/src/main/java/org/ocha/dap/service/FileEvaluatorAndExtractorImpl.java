package org.ocha.dap.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.ocha.dap.model.ValidationReport;
import org.ocha.dap.model.ValidationStatus;
import org.ocha.dap.persistence.dao.CKANDatasetDAO;
import org.ocha.dap.persistence.dao.CKANResourceDAO;
import org.ocha.dap.persistence.entity.ckan.CKANDataset;
import org.ocha.dap.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.dap.persistence.entity.ckan.CKANResource;
import org.springframework.beans.factory.annotation.Autowired;

public class FileEvaluatorAndExtractorImpl implements FileEvaluatorAndExtractor {

	private final File stagingDirectory;

	public FileEvaluatorAndExtractorImpl(final File stagingDirectory) {
		super();
		if (!stagingDirectory.isDirectory()) {
			throw new IllegalArgumentException("staging  directory doesn't exist: " + stagingDirectory.getAbsolutePath());
		}

		this.stagingDirectory = stagingDirectory;
	}

	@Autowired
	private CKANResourceDAO resourceDAO;

	@Autowired
	private CKANDatasetDAO datasetDAO;

	@Override
	public ValidationReport evaluateDummyCSVFile(final String id, final String revision_id) {
		final File reourceFolder = new File(stagingDirectory, id);
		final File revisionFile = new File(reourceFolder, revision_id);

		return evaluateDummyCSVFile(revisionFile);

	}

	@Override
	public ValidationReport evaluateDummyCSVFile(final File file) {
		final ValidationReport report = new ValidationReport(CKANDataset.Type.DUMMY);
		try (final BufferedReader br = new BufferedReader(new FileReader(file))) {
			final Map<String, Integer> totalForCountries = new HashMap<>();
			String line;
			while ((line = br.readLine()) != null) {

				// use comma as separator
				final String[] values = line.split(",");

				if (values.length != 4) {
					report.addEntry(ValidationStatus.ERROR,
							String.format("A ligne contains an incorrect number of values, expected : 4, actual : %d", values.length));
					//In this case, the next test cannot even be performed, so we return the root error
					return report;
				}
				
				final String country = values[0];
				final Integer value = Integer.parseInt(values[2]);

				final Integer total = totalForCountries.get(country);
				if (total != null) {
					totalForCountries.put(country, total + value);
				} else {
					totalForCountries.put(country, value);
				}

			}
			for (final Entry<String, Integer> entry : totalForCountries.entrySet()) {
				if (entry.getValue() != 100)
					report.addEntry(ValidationStatus.ERROR, String.format("Total for region : %s is not 100", entry.getKey()));
			}

		} catch (final IOException e) {
			report.addEntry(ValidationStatus.ERROR, "Error caused by an exception");
		}
		return report;
	}

	private ValidationReport evaluateScraper(final String id, final String revision_id) {
		final ValidationReport report = new ValidationReport(CKANDataset.Type.SCRAPER);

		report.addEntry(ValidationStatus.ERROR, "Mocked evaluator, always failing");
		return report;
	}

	private ValidationReport defaultFail(final String id, final String revision_id) {
		final ValidationReport report = new ValidationReport(CKANDataset.Type.SCRAPER);

		report.addEntry(ValidationStatus.ERROR, "Mocked evaluator, always failing");
		return report;
	}

	@Override
	public Type getTypeForFile(final String id, final String revision_id) {
		final CKANResource ckanResource = resourceDAO.getCKANResource(id, revision_id);
		return datasetDAO.getTypeForName(ckanResource.getParentDataset_name());
	}

	@Override
	public ValidationReport evaluateResource(final String id, final String revision_id, final Type type) {
		switch (type) {
		case DUMMY:
			return evaluateDummyCSVFile(id, revision_id);

		case SCRAPER:
			return evaluateScraper(id, revision_id);

		default:
			return defaultFail(id, revision_id);
		}
	}

	@Override
	public boolean transformAndImportDataFromResource(final String id, final String revision_id, final Type type) {
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
