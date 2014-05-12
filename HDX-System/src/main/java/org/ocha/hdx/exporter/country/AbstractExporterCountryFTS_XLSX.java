package org.ocha.hdx.exporter.country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.exporter.QueryData.CHANNEL_KEYS;
import org.ocha.hdx.exporter.country.ExporterCountryQueryData.DataSerieInSheet;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.service.ExporterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract exporter for all FTS country-centric sheets.
 * 
 * @author bmichiels
 */
public abstract class AbstractExporterCountryFTS_XLSX extends Exporter_XLSX<ExporterCountryQueryData> {

	private static Logger logger = LoggerFactory.getLogger(AbstractExporterCountryFTS_XLSX.class);

	public AbstractExporterCountryFTS_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	public AbstractExporterCountryFTS_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	@SuppressWarnings("unchecked")
	protected XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData, final Map<String, ReportRow> data, final String sheetName) throws Exception {
		// TODO i18n, UT

		// Create the sheet
		final String safeName = WorkbookUtil.createSafeSheetName(sheetName);
		final XSSFSheet sheet = workbook.createSheet(safeName);

		// Define the headers
		final ArrayList<Object> headers = new ArrayList<Object>();
		headers.add("Indicator");

		// Retrieve years from the data, as specifying 0 for fromYear/toYear in the queryData allows for earliest/latest data available.
		int fromYear = Integer.MAX_VALUE;
		int toYear = Integer.MIN_VALUE;
		for (final String indicatorTypeCode : data.keySet()) {
			final ReportRow reportRow = data.get(indicatorTypeCode);
			if (fromYear > reportRow.getMinYear()) {
				fromYear = reportRow.getMinYear();
			}
			if (toYear < reportRow.getMaxYear()) {
				toYear = reportRow.getMaxYear();
			}
		}

		// We may have holes in the series of years,
		// so we map each year to the corresponding column index.
		final Map<Integer, Integer> yearToColum = new HashMap<Integer, Integer>();
		
		for (int year = fromYear; year <= toYear; year++) {
			headers.add(year);
			yearToColum.put(year, headers.size() - 1);
		}
		// Switch to the following for reverse order
		/*
		for (int year = toYear; year >= fromYear; year--) {
			headers.add(year);
			yearToColum.put(year, headers.size() - 1);
		}
		*/

		// Assign the headers to the title row
		createColumnHeaderCells(sheet, headers);

		// Fill with the data
		// We start right just after the headers row
		int rowIndex = 1;

		for (final String indicatorTypeCode : new TreeSet<String>(data.keySet())) {
			final ReportRow reportRow = data.get(indicatorTypeCode);

			final XSSFRow row = sheet.createRow(rowIndex);
			rowIndex++;

			createCell(row, 0, reportRow.getIndicatorName());

			// Keep track of the indicator types processed
			trackIndicatorTypes(queryData, reportRow, sheetName);

			for (int year = fromYear; year <= toYear; year++) {
				final int columnIndex = yearToColum.get(year);
				final Double value = reportRow.getDoubleValue(year);
				if (null != value) {
					createNumCell(row, columnIndex, value);
				} else {
					createCell(row, columnIndex, " ");
				}
			}
		}

		// Freeze the headers
		// Freeze the 2 first columns
		sheet.createFreezePane(1, 1, 1, 1);

		// Auto size the columns
		// Except Indicator ID and Dataset summary which is fixed
		for (int i = 0; i < (headers.size() + data.keySet().size()); i++) {
			sheet.autoSizeColumn(i);
		}

		// Show processed indicator types so far
		final Set<DataSerieInSheet> dataSerieInSheets = (Set<DataSerieInSheet>) queryData.getChannelValue(CHANNEL_KEYS.DATA_SERIES);
		logger.debug("Indicators type after " + this.getClass().getName() + " : ");
		if (null != dataSerieInSheets) {
			for (final DataSerieInSheet dataSerieInSheet : dataSerieInSheets) {
				logger.debug("\t" + dataSerieInSheet.getDataSerie().getIndicatorCode() + " => " + dataSerieInSheet.getSheetName());
			}
		} else {
			logger.debug("\tNone");
		}

		return super.export(workbook, queryData);

	}

	private static void trackIndicatorTypes(final ExporterCountryQueryData queryData, final ReportRow reportRow, final String sheetName) {
		@SuppressWarnings("unchecked")
		Set<DataSerieInSheet> indicatorTypes = (Set<DataSerieInSheet>) queryData.getChannelValue(CHANNEL_KEYS.DATA_SERIES);
		if (null == indicatorTypes) {
			indicatorTypes = new HashSet<DataSerieInSheet>();
			queryData.setChannelValue(CHANNEL_KEYS.DATA_SERIES, indicatorTypes);
		}
		final DataSerieInSheet dataSerieInSheet = queryData.new DataSerieInSheet(reportRow.getIndicatorTypeCode(), reportRow.getSourceCode(), sheetName);
		indicatorTypes.add(dataSerieInSheet);
	}
}
