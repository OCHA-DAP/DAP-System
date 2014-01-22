package org.ocha.dap.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocha.dap.persistence.entity.curateddata.IndicatorValue;
import org.ocha.dap.persistence.entity.dictionary.SourceDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScraperImporter implements DAPImporter {

	private static Logger logger = LoggerFactory.getLogger(ScraperImporter.class);

	private final List<String> acceptedIndicatorTypes = new ArrayList<>();

	Map<String, String> sourcesMap = new HashMap<>();

	public ScraperImporter(final List<SourceDictionary> sourceDictionaries) {
		super();

		if (sourceDictionaries != null) {
			for (final SourceDictionary sourceDictionary : sourceDictionaries) {
				sourcesMap.put(sourceDictionary.getId().getUnnormalizedName(), sourceDictionary.getSource().getCode());
			}
		}
		acceptedIndicatorTypes.add("PVX040");
		acceptedIndicatorTypes.add("PSP080");
		acceptedIndicatorTypes.add("PSE030");
		acceptedIndicatorTypes.add("PCX051");
		acceptedIndicatorTypes.add("PVF020");
		acceptedIndicatorTypes.add("PSP010");
		acceptedIndicatorTypes.add("_emdat:total_affected");

	}

	public Map<String, String> getCountryList(final File file) {
		final Map<String, String> result = new HashMap<String, String>();
		final File parent = file.getParentFile();
		final File valueFile = new File(parent, "value.csv");

		try (final BufferedReader br = new BufferedReader(new FileReader(valueFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				// use comma as separator
				final String[] values = line.split(",");
				if ("_m49-name".equals(values[2])) {
					result.put(values[1], values[4]);
				}
			}

			return result;
		} catch (final IOException e) {
			return result;
		}
	}

	@Override
	public PreparedData prepareDataForImport(final File file) {
		// get the dictionary entries for the scraper importer
		// TODO should it be linked to the enum CKANDataset.Type ?

		final List<PreparedIndicator> preparedIndicators = new ArrayList<>();
		final File parent = file.getParentFile();
		final File valueFile = new File(parent, "value.csv");

		try (final BufferedReader br = new BufferedReader(new FileReader(valueFile))) {
			String line;
			while ((line = br.readLine()) != null) {

				// use comma as separator
				final String[] values = line.split(",");
				if (acceptedIndicatorTypes.contains(values[2])) {
					final PreparedIndicator preparedIndicator = new PreparedIndicator();

					if (sourcesMap.containsKey(values[0])) {
						preparedIndicator.setSourceCode(sourcesMap.get(values[0]));
					} else {
						preparedIndicator.setSourceCode(values[0]);
					}
					preparedIndicator.setEntityCode(values[1]);
					preparedIndicator.setEntityTypeCode("country");
					preparedIndicator.setIndicatorTypeCode(values[2]);

					final TimeRange timeRange = new TimeRange(values[3]);
					preparedIndicator.setStart(timeRange.getStart());
					preparedIndicator.setEnd(timeRange.getEnd());
					preparedIndicator.setPeriodicity(timeRange.getPeriodicity());

					final Double valueAsDouble = Double.parseDouble(values[4]);
					// FIXME we should deal about units later, here for
					// population we must X1000
					if ("PSP010".equals(values[2])) {
						preparedIndicator.setValue(new IndicatorValue(valueAsDouble * 1000));
					} else {
						preparedIndicator.setValue(new IndicatorValue(valueAsDouble));
					}

					preparedIndicator.setInitialValue(values[4]);

					preparedIndicators.add(preparedIndicator);
				}
			}

			return new PreparedData(true, preparedIndicators);
		} catch (final Exception e) {
			logger.debug(e.toString());
			return new PreparedData(false, null);
		}

	}
}
