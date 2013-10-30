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
import org.ocha.dap.persistence.entity.ckan.CKANDataset;
import org.ocha.dap.persistence.entity.ckan.CKANDataset.Type;

public class FileEvaluatorAndExtractorImpl implements FileEvaluatorAndExtractor {

	/**
	 * performs a dummy evaluation of a CSV file
	 * 
	 * for this example, we assume we got some percentage for some categories,
	 * per country all countries sum should be 100.
	 * 
	 * @return true if all countries have a sum of 100, false otherwise
	 * 
	 */
	ValidationReport evaluateDummyCSVFile(final File file) {
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
					// In this case, the next test cannot even be performed, so
					// we return the root error
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

	private ValidationReport evaluateScraper(final File file) {
		final ValidationReport report = new ValidationReport(CKANDataset.Type.SCRAPER);

		report.addEntry(ValidationStatus.ERROR, "Mocked evaluator, always failing");
		return report;
	}

	private ValidationReport defaultFail(final File file) {
		final ValidationReport report = new ValidationReport(CKANDataset.Type.SCRAPER);

		report.addEntry(ValidationStatus.ERROR, "Mocked evaluator, always failing");
		return report;
	}

	@Override
	public ValidationReport evaluateResource(final File file, final Type type) {
		switch (type) {
		case DUMMY:
			return evaluateDummyCSVFile(file);

		case SCRAPER:
			return evaluateScraper(file);

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
