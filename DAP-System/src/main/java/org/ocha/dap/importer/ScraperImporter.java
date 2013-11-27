package org.ocha.dap.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScraperImporter implements DAPImporter {

	private final List<String> acceptedIndicatorTypes = new ArrayList<>();
	private final List<String> acceptedCountries = new ArrayList<>();

	public ScraperImporter() {
		super();
		acceptedIndicatorTypes.add("PVX040");
		acceptedIndicatorTypes.add("PSP080");
		acceptedCountries.add("RUS");
		acceptedCountries.add("RWA");
		acceptedCountries.add("CMR");
		acceptedCountries.add("LUX");

	}

	@Override
	public PreparedData prepareDataForImport(final File file) {
		final List<PreparedIndicator> preparedIndicators = new ArrayList<>();
		final File parent = file.getParentFile();
		final File valueFile = new File(parent, "value.csv");

		try (final BufferedReader br = new BufferedReader(new FileReader(valueFile))) {
			String line;
			while ((line = br.readLine()) != null) {

				// use comma as separator
				final String[] values = line.split(",");
				if (acceptedIndicatorTypes.contains(values[2]) && acceptedCountries.contains(values[1])) {
					final PreparedIndicator preparedIndicator = new PreparedIndicator();
					preparedIndicator.setSourceCode(values[0]);
					preparedIndicator.setEntityCode(values[1]);
					preparedIndicator.setEntityTypeCode("country");
					preparedIndicator.setIndicatorTypeCode(values[2]);

					final TimeRange timeRange = new TimeRange(values[3]);
					preparedIndicator.setStart(timeRange.getStart());
					preparedIndicator.setEnd(timeRange.getEnd());
					preparedIndicator.setPeriodicity(timeRange.getPeriodicity());
					preparedIndicator.setNumeric("0".equals((values[5])));
					preparedIndicator.setValue(values[4]);
					preparedIndicator.setInitialValue(values[4]);

					preparedIndicators.add(preparedIndicator);
				}
			}

			return new PreparedData(true, preparedIndicators);
		} catch (final IOException e) {
			return new PreparedData(false, null);
		}

	}
}
