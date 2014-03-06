package org.ocha.hdx.exporter.country;

import java.util.ArrayList;
import java.util.Map;

import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country crisis history.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryCrisisHistory_XLSX extends Exporter_XLSX<ExporterCountryQueryData> {

	public ExporterCountryCrisisHistory_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountryCrisisHistory_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) {

		final Map<String, ReportRow> data = exporterService.getCountryCrisisHistoryData(queryData);

		// TODO i18n, UT

		// Create the sheet
		final String safeName = WorkbookUtil.createSafeSheetName("Crisis history");
		final XSSFSheet sheet = workbook.createSheet(safeName);

		// Define the headers
		@SuppressWarnings("serial")
		final ArrayList<String> headers = new ArrayList<String>() {
			{
				add("Indicator ID");
				add("Indicator name");
				add("Dataset ID");
				add("Units");
			}
		};

		// Assign the headers to the title row
		createHeaderCells(sheet, headers);

		// TODO Set the indicators info (cells A2:D5), maybe create a custom query for this.

		// Fill with the data
		final int index = 0;
		/*
		 * for (final Integer year : data.keySet()) { final List<Object[]> list = data.get(year);
		 * 
		 * final int columnIndex = headers.size() + index;
		 * 
		 * // Create the header for the given year createHeaderCell(year.toString(), sheet.getRow(0), columnIndex);
		 * 
		 * // Add the year indicators for (int i = 0; i < list.size(); i++) { final Object[] element = list.get(i);
		 * 
		 * // Get an existing row or creating a new one (not overwriting the headers :-) ) XSSFRow row = sheet.getRow(1 + i); if (null == row) { row = sheet.createRow(i + 1); }
		 * 
		 * // The indicator value for the current year if (0 < element.length) { // "Value" createCell(row, (short) columnIndex, element[0].toString()); } } }
		 */

		// Freeze the headers
		// Freeze the 4 first columns
		sheet.createFreezePane(4, 1, 4, 1);

		// Auto size the columns
		for (int i = 0; i < (headers.size() + data.keySet().size()); i++) {
			sheet.autoSizeColumn(i);
		}

		return super.export(workbook, queryData);
	}
}
