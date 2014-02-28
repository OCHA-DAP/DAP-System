package org.ocha.hdx.exporter.country;

import java.util.List;

import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country overview.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryOverview_XLSX extends Exporter_XLSX<ExporterCountryQueryData> {

	public ExporterCountryOverview_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountryOverview_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) {

		final List<Object[]> countryOverviewData = exporterService.getCountryOverviewData(queryData);

		/*** ***/

		// Creating the sheet
		final String safeName = WorkbookUtil.createSafeSheetName("Overview");
		final XSSFSheet sheet = workbook.createSheet(safeName);

		// Assign the headers to the title row
		final String[] headers = { "Indicator ID", "Indicator name", "Value", "Last updated", "Source dataset" };
		createHeaderCells(sheet, headers);

		// Fill with the data
		// Sample : CD010	Wikipedia: geography	http://en.wikipedia.org/wiki/Colombia#Geography	2014-01-22	wikipedia

		for (int i = 0; i < countryOverviewData.size(); i++) {
			final Object[] element = countryOverviewData.get(i);
			final XSSFRow row = sheet.createRow(i+1);
			final XSSFCell cell0 = row.createCell(0);
			cell0.setCellValue(element[0].toString());
			if (1 < element.length) {
				final XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(element[2].toString());

				createUrlCell(sheet, row, (short) 2, element[4].toString(), element[4].toString());
				// createDateCell(sheet, row1, (short) 3, new GregorianCalendar(2014, 00, 22).getTime(), DATE_FORMAT_01);
				final XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(element[5].toString());

				final XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(element[6].toString());
			}
		}

		// Freeze the headers 
		// Freeze the 2 first columns
		sheet.createFreezePane(2, 1, 2, 1);

		// Auto size the columns, except column C, which has a fixed width
		for (int i = 0; i < headers.length; i++) {
			if (2 == i) {
				sheet.setColumnWidth(i, 5000);
			} else {
				sheet.autoSizeColumn(i);
			}
		}

		return super.export(workbook, queryData);
	}

}
