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
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata.EntryKey;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country - all data.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryData_CSV extends Exporter_File<ExporterCountryQueryData> {

	public ExporterCountryData_CSV(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountryData_CSV(final Exporter<File, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public File export(final File file, final ExporterCountryQueryData queryData) throws IOException {

		// Gathering the data
		// 1. Country overview
		// 2. Country crisis history
		// 3. Country socio-economic
		// 4. Country vulnerability
		// 5. Country capacity
		// 6. Country - other
		// 7. Country - 5-years data
		// 8. Read me

		final Map<String, ReportRow> crisisHistoryData = exporterService.getCountryCrisisHistoryData(queryData);
		final Map<String, ReportRow> socioEconomicData = exporterService.getCountrySocioEconomicData(queryData);
		final Map<String, ReportRow> vulnerabilityData = exporterService.getCountryVulnerabilityData(queryData);
		final Map<String, ReportRow> capacityData = exporterService.getCountryCapacityData(queryData);
		final Map<String, ReportRow> otherData = exporterService.getCountryOtherData(queryData);
		final Map<String, ReportRow> _5YearsData = exporterService.getCountry5YearsData(queryData);

		final List<Map<String, ReportRow>> allData = new ArrayList<Map<String, ReportRow>>();
		allData.add(crisisHistoryData);
		allData.add(socioEconomicData);
		allData.add(vulnerabilityData);
		allData.add(capacityData);
		allData.add(otherData);
		allData.add(_5YearsData);

		// Define the headers
		final ArrayList<String> headers = new ArrayList<String>();
		headers.add("Indicator ID");
		headers.add("Indicator name");
		headers.add("Source dataset");
		headers.add("Units");
		headers.add("Dataset summary");
		headers.add("More Info");
		headers.add("Terms of Use");
		headers.add("HDX Methodology");

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
		for (int year = toYear; year >= fromYear; year--) {
			headers.add("" + year);
			yearToColum.put(year, headers.size() - 1);
		}
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

				row[0] = reportRow.getIndicatorTypeCode();
				row[1] = reportRow.getIndicatorName();
				row[2] = reportRow.getSourceCode();
				row[3] = reportRow.getUnit();

				row[4] = reportRow.getMetadata().get(EntryKey.DATASET_SUMMARY);
				row[5] = reportRow.getMetadata().get(EntryKey.MORE_INFO);
				row[6] = reportRow.getMetadata().get(EntryKey.TERMS_OF_USE);
				row[7] = reportRow.getMetadata().get(EntryKey.METHODOLOGY);

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

	/**
	 * Avoid null values in CSV content
	 * 
	 * @param row
	 */
	private static void initRow(final String[] row) {
		for (int i = 0; i < row.length; i++) {
			row[i] = "";
		}
	}
}
