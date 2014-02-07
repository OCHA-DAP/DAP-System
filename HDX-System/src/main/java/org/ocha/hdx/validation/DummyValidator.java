package org.ocha.hdx.validation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset;

/**
 * performs a dummy evaluation of a CSV file
 * 
 * for this example, we assume we got some percentage for some categories, per country all countries sum should be 100.
 * 
 */
public class DummyValidator implements HDXValidator {

	/**
	 * @return SUCCESS if all countries have a sum of 100, false otherwise
	 */
	@Override
	public ValidationReport evaluateFile(final File file) {
		final ValidationReport report = new ValidationReport(CKANDataset.Type.DUMMY);
		try (final BufferedReader br = new BufferedReader(new FileReader(file))) {
			final Map<String, Integer> totalForCountries = new HashMap<>();
			String line;
			while ((line = br.readLine()) != null) {

				// use comma as separator
				final String[] values = line.split(",");

				if (values.length != 4) {
					report.addEntry(ValidationStatus.ERROR, String.format("A ligne contains an incorrect number of values, expected : 4, actual : %d", values.length));
					// In this case, the next test cannot even be performed, so
					// we return the root error
					return report;
				} else {
					report.addEntry(ValidationStatus.SUCCESS, String.format("A ligne contains the correct number of values ", values.length));
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

}
