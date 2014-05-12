package org.ocha.hdx.exporter.indicator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_File;
import org.ocha.hdx.persistence.entity.view.IndicatorData;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for an indicator - all data.
 * 
 * @author bmichiels
 * 
 */
public class ExporterIndicatorData_CSV extends Exporter_File<ExporterIndicatorQueryData> {

	public ExporterIndicatorData_CSV(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterIndicatorData_CSV(final Exporter<File, ExporterIndicatorQueryData> exporter) {
		super(exporter);
	}

	@Override
	public File export(final File file, final ExporterIndicatorQueryData queryData) throws IOException {

		// Gathering the data
		// 1. Indicator data

		final Map<Long, Map<String, IndicatorData>> data = exporterService.getIndicatorDataData(queryData);

		// Find min and max year values 
		// (there may be holes in the list of years, but we want a column for each year between minYear and maxYear)
		long minYear = Long.MAX_VALUE;
		long maxYear = Long.MIN_VALUE;
		for (final Long year : data.keySet()) {
			if (year < minYear) {
				minYear = year;
			}
			if (year > maxYear) {
				maxYear = year;
			}
		}

		// Define the headers
		final ArrayList<String> headers = new ArrayList<String>();
		headers.add("Country code");
		headers.add("Country name");
		
		final int headersSize = headers.size();

		final Map<Long, Integer> yearColumns = new HashMap<Long, Integer>();
		int columnIndex = headersSize;
		for (Long year = maxYear; year >= minYear; year--) {
			headers.add("" + year);
			yearColumns.put(year, columnIndex);
			++columnIndex;
		}

		// For each year, there may be a different set of countries, so we have to parse them all first.
		// we also map country codes to country names for the row header creation.
		// And we get a reference to the source (there is only one source for now as it is a report query parameter).
		// We will sort the set after.
		final Map<String, String> countryCodes = new HashMap<String, String>();
		for (final Long year : data.keySet()) {
			final Map<String, IndicatorData> countryMap = data.get(year);
			for (final String countryCode : countryMap.keySet()) {
				final IndicatorData indicatorData = countryMap.get(countryCode);
				countryCodes.put(countryCode, indicatorData.getCountryDefaultValue());
			}
		}

		// We represent the content as a List of arrays,
		// each array being a line in the final CSV file.
		final List<String[]> content = new ArrayList<String[]>();

		// Assign the headers to the title row
		final String[] headerLine = headers.toArray(new String[] {});
		content.add(headerLine);

		// Create country rows with country header for code and name
		final Map<String, String[]> countryRows = new HashMap<String, String[]>();
		for (final String countryCode : new TreeSet<String>(countryCodes.keySet())) {
			final String[] countryRow = new String[headers.size()];
			countryRow[0] = countryCode;
			countryRow[1] = countryCodes.get(countryCode);
			countryRows.put(countryCode, countryRow);
			content.add(countryRow);
		}

		// Fill the data
		for (final Long year : data.keySet()) {
			// logger.debug("Indicator data year : " + year);
			final Map<String, IndicatorData> countryMap = data.get(year);
			for (final String countryCode : new TreeSet<String>(countryMap.keySet())) {
				final IndicatorData indicatorData = countryMap.get(countryCode);
				final String[] countryRow = countryRows.get(countryCode);
				final Integer index = yearColumns.get(year);
				countryRow[index] = "" + indicatorData.getIndicatorValue();
			}
		}


		// Write the file
		writeCSVFile(content, ",", file);

		return file;
	}
}
