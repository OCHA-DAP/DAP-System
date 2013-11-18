package org.ocha.dap.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.Indicator;

public class ScraperImporter implements DAPImporter {

	private final List<String> acceptedIndicatorTypes = new ArrayList<>();

	public ScraperImporter() {
		super();
		acceptedIndicatorTypes.add("PVX040");
	}

	@Override
	public PreparedData prepareDataForImport(final File file) {
		final List<Indicator> preparedIndicators = new ArrayList<>();
		final File parent = file.getParentFile();
		final File valueFile = new File(parent, "value.csv");

		try (final BufferedReader br = new BufferedReader(new FileReader(valueFile))) {
			String line;
			while ((line = br.readLine()) != null) {

				// use comma as separator
				final String[] values = line.split(",");
				if (acceptedIndicatorTypes.contains(values[2])) {
					final Indicator indicator = new Indicator();
					// FIXME finish this
				}
			}

			return new PreparedData(true, preparedIndicators);
		} catch (final IOException e) {
			return new PreparedData(false, null);
		}

	}
}
