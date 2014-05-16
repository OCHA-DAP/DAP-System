package org.ocha.hdx.exporter.country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.service.ExporterService;

/**
 * Abstract exporter for all RW country-centric sheets.
 * 
 * @author bmichiels
 */
public abstract class AbstractExporterCountryRW_XLSX extends Exporter_XLSX<ExporterCountryQueryData> {

	public AbstractExporterCountryRW_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	public AbstractExporterCountryRW_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	protected XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData, final Map<String, ReportRow> data, final String sheetName) throws Exception {
		// TODO i18n, UT

		// Create the sheet
		final String safeName = WorkbookUtil.createSafeSheetName(sheetName);
		final XSSFSheet sheet = workbook.createSheet(safeName);

		// Define the headers
		final ArrayList<Object> headers = new ArrayList<Object>();
		headers.add("Indicator");

		// Retrieve years from the data, as specifying 0 for fromYear/toYear in the queryData allows for earliest/latest data available.
		final int fromYear = getYear(data, "from");
		final int toYear = getYear(data, "to");

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

		/*
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
		*/
		return super.export(workbook, queryData);
	}
}
