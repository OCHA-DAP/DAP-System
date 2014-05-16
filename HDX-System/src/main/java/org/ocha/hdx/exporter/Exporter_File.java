package org.ocha.hdx.exporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.service.ExporterService;

/**
 * Abstract class for File report generation. This class should be implemented for every report needing a File export (CSV, PDF, TXT, etc.).
 * 
 * Exporters follow the Decorator pattern (see constructors).
 * 
 * @author bmichiels
 * 
 * @param <QD>
 *            The information needed to query and gather the report data.
 */
public abstract class Exporter_File<QD extends QueryData> extends AbstractExporter<File, QD> {

	/**
	 * This constructor is for the final exporter of a chain of exporters. It propagates the exporter service to its predecessors for proper generation.
	 * 
	 * @param exporterService
	 */
	public Exporter_File(final ExporterService exporterService) {
		super(exporterService);
	}

	/**
	 * This constructor takes the following exporter in the global export chain.
	 * 
	 * @param exporter
	 */
	public Exporter_File(final Exporter<File, QD> exporter) {
		super(exporter);
	}

	/* ********* */
	/* Utilities */
	/* ********* */

	/**
	 * Retrieve years from the data, as specifying 0 for fromYear/toYear in the queryData allows for earliest/latest data available.
	 * 
	 * @param data
	 * @param which
	 * @return
	 */
	protected static int getYear(final List<Map<String, ReportRow>> allData, final String which) {
		int year = 0;
		if ("from".equals(which)) {
			year = Integer.MAX_VALUE;
			for (final Map<String, ReportRow> map : allData) {
				for (final String indicatorTypeCode : map.keySet()) {
					final ReportRow reportRow = map.get(indicatorTypeCode);
					if (year > reportRow.getMinYear()) {
						year = reportRow.getMinYear();
					}
				}
			}
		} else {
			year = Integer.MIN_VALUE;
			for (final Map<String, ReportRow> map : allData) {
				for (final String indicatorTypeCode : map.keySet()) {
					final ReportRow reportRow = map.get(indicatorTypeCode);
					if (year < reportRow.getMaxYear()) {
						year = reportRow.getMaxYear();
					}
				}
			}
		}
		return year;
	}

	/* *** */
	/* I/O */
	/* *** */

	/**
	 * Write a CSV file.
	 * 
	 * @param content
	 * @param separator
	 * @param file
	 * @throws IOException
	 */
	protected static void writeCSVFile(final List<String[]> content, final String separator, final File file) throws IOException {
		final FileWriter writer = new FileWriter(file);
		// Iterate over content
		for (final String[] line : content) {
			// Grab an iterator for this line of content
			for (final Iterator<String> iterator = Arrays.asList(line).iterator(); iterator.hasNext();) {
				final String cell = iterator.next();
				// Avoid null values in file
				if (cell != null) {
					// Escape for proper CSV
					final String escaped = StringEscapeUtils.escapeCsv(cell);
					writer.write(escaped);
				} else {
					writer.write("");
				}
				// If we're not at the end of the line, write a separator
				if (iterator.hasNext()) {
					writer.write(separator);
				}
			}
			// Write a new line
			writer.write(System.getProperty("line.separator"));
		}
		writer.close();
	}

	/**
	 * Write a TXT file.
	 * 
	 * @param content
	 * @param file
	 * @throws IOException
	 */
	protected static void writeTXTFile(final List<String> content, final File file) throws IOException {
		final FileWriter writer = new FileWriter(file);
		// Write each line
		for (final String line : content) {
			writer.write(line);
			writer.write(System.getProperty("line.separator"));
		}
		writer.close();
	}

	/**
	 * Avoid null values in File content
	 * 
	 * @param row
	 */
	protected static void initRow(final String[] row) {
		for (int i = 0; i < row.length; i++) {
			row[i] = "";
		}
	}
}
