package org.ocha.hdx.exporter.country;

import java.util.List;

import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country - overview.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryRWOverview_XLSX extends Exporter_XLSX<ExporterCountryQueryData> {

	public ExporterCountryRWOverview_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountryRWOverview_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) throws Exception {

		final List<Object[]> data = exporterService.getCountryRWOverviewData(queryData);

		/* TODO i18n */

		// Creating the sheet
		final String safeName = WorkbookUtil.createSafeSheetName("Overview");
		final XSSFSheet sheet = workbook.createSheet(safeName);

		// Assign the headers to the title row
		final String[] headers = { "Attribute ID", "Attribute name", "Value", /*"Last updated", */"Source dataset" };
		createColumnHeaderCells(sheet, headers);

		// Fill with the data
		for (int i = 0; i < data.size(); i++) {
			final Object[] element = data.get(i);

			// Create the row (not overwriting the headers :-) )
			final XSSFRow row = sheet.createRow(i + 1);

			// "Attribute ID" (always set)
			createCell(row, 0, element[0].toString());

			// Other infos may be unavailable if there are no values for the indicator
			if (1 < element.length) {

				// "Attribute name"
				createCell(row, 1, element[1].toString());

				// "Value"
				final String value = element[2].toString();
				// Value can be a URL
				if ((null != value) && value.startsWith("http://")) {
					try {
						createUrlCell(row, 2, value, value);
					} catch (final IllegalArgumentException e) {
						// Some url are considered not valid, even if browsers accept them.
						// example : http://en.wikipedia.org/wiki/United_States#Geography.2C_climate.2C_and_environment
						createCell(row, 2, value);
					}
				}
				// or a simple string
				else {
					createCell(row, 2, value);
				}

				/*
				// "Last updated"
				final Object object = element[3];
				createCell(row, 3, object.toString());
				*/

				// "Source dataset"
				createCell(row, 3, element[4].toString());
			}
		}

		// Freeze the headers
		// Freeze the 2 first columns
		sheet.createFreezePane(2, 1, 2, 1);

		// Auto size the columns, except columns A and C, which have a fixed width
		for (int i = 0; i < headers.length; i++) {
			if (0 == i) {
				sheet.setColumnWidth(i, 3000);
			} else if (2 == i) {
				sheet.setColumnWidth(i, 5000);
			} else {
				sheet.autoSizeColumn(i);
			}
		}

		return super.export(workbook, queryData);
	}
}
