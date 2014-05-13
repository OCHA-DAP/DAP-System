package org.ocha.hdx.exporter.country;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_File;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country - FTS.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryFTSData_CSV extends Exporter_File<ExporterCountryQueryData> {

	public ExporterCountryFTSData_CSV(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountryFTSData_CSV(final Exporter<File, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public File export(final File file, final ExporterCountryQueryData queryData) throws IOException {

		// Gathering the data
		// 1. FTS

		final Map<String, ReportRow> rwData = exporterService.getCountryData(queryData, DataSerie.COUNTRY_FTS_dataSeries);

		final List<Map<String, ReportRow>> allData = new ArrayList<Map<String, ReportRow>>();
		allData.add(rwData);

		// Define the headers
		final ArrayList<String> headers = new ArrayList<String>();
		headers.add("Indicator name");

		// Retrieve years from the data, as specifying 0 for fromYear/toYear in the queryData allows for earliest/latest data available.
		int fromYear = Integer.MAX_VALUE;
		int toYear = Integer.MIN_VALUE;
		for (final Map<String, ReportRow> map : allData) {
			for (final String indicatorTypeCode : map.keySet()) {
				final ReportRow reportRow = map.get(indicatorTypeCode);
				if (fromYear > reportRow.getMinYear()) {
					fromYear = reportRow.getMinYear();
				}
				if (toYear < reportRow.getMaxYear()) {
					toYear = reportRow.getMaxYear();
				}
			}
		}

		// We may have holes in the series of years,
		// so we map each year to the corresponding column index.
		final Map<Integer, Integer> yearToColum = new HashMap<Integer, Integer>();
		for (int year = fromYear; year <= toYear; year++) {
			headers.add("" + year);
			yearToColum.put(year, headers.size() - 1);
		}
		// Switch to the following for reverse order
		/*
		for (int year = toYear; year >= fromYear; year--) {
			headers.add("" + year);
			yearToColum.put(year, headers.size() - 1);
		}
		*/
		
		// Number of columns
		final int dataSize = headers.size();

		// We represent the content as a List of arrays,
		// each array being a line in the final CSV file.
		final List<String[]> content = new ArrayList<String[]>();

		// Assign the headers to the title row
		final String[] headerLine = headers.toArray(new String[] {});
		content.add(headerLine);

		// Fill the data
		for (final Map<String, ReportRow> map : allData) {
			for (final String indicatorTypeCode : map.keySet()) {
				final ReportRow reportRow = map.get(indicatorTypeCode);

				final String[] row = new String[dataSize];
				initRow(row);

				row[0] = reportRow.getIndicatorName();

				for (int year = fromYear; year <= toYear; year++) {
					final int columnIndex = yearToColum.get(year);
					final Double value = reportRow.getDoubleValue(year);
					if (null != value) {
						row[columnIndex] = "" + value;
					} else {
						row[columnIndex] = "";
					}
				}
				content.add(row);
			}
		}

		// Write the file
		writeCSVFile(content, ",", file);

		return file;
	}
}
