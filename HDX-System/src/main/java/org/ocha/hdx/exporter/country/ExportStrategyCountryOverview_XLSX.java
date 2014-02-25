package org.ocha.hdx.exporter.country;

import java.util.GregorianCalendar;

import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.ocha.hdx.exporter.ExportStrategy;
import org.ocha.hdx.exporter.ExportStrategy_XLSX;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.service.CuratedDataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Export strategy for a country in XSLX.
 * @author bmichiels
 *
 */
public class ExportStrategyCountryOverview_XLSX extends ExportStrategy_XLSX implements ExportStrategy<ExporterCountryOverview, XSSFSheet> {

	@Autowired 
	protected CuratedDataService curatedDataService;

	@Override
	public XSSFSheet export(final ExporterCountryOverview countryOverview) {

		// Creating the sheet
		final String safeName = WorkbookUtil.createSafeSheetName("Overview");
		final XSSFSheet sheet = countryOverview.workbook.createSheet(safeName);

		// Assign the headers to the title row
		final String[] headers = { "Indicator ID", "Indicator name", "Value", "Last updated", "Source dataset" };
		createHeaderCells(sheet, headers);

		// Test
		final IndicatorType indicatorType = curatedDataService.getIndicatorType(1);
		System.out.println(indicatorType.getCode());
		
		
		// Fill with the data
		// Sample : CD010	Wikipedia: geography	http://en.wikipedia.org/wiki/Colombia#Geography	2014-01-22	wikipedia
		for (short i = 1; i <= 30; i++) {
			final XSSFRow row1 = sheet.createRow(i);
			final XSSFCell cell0 = row1.createCell(0);
			cell0.setCellValue("CD010");
			final XSSFCell cell1 = row1.createCell(1);
			cell1.setCellValue("Wikipedia: geography");

			createUrlCell(sheet, row1, (short) 2, "http://en.wikipedia.org/wiki/Colombia#Geography", "http://en.wikipedia.org/wiki/Colombia#Geography");
			createDateCell(sheet, row1, (short) 3, new GregorianCalendar(2014, 00, 22).getTime(), DATE_FORMAT_01);

			final XSSFCell cell4 = row1.createCell(4);
			cell4.setCellValue("wikipedia");
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

		System.out.println("XLSX : " + countryOverview.getQueryData());
		return sheet;
	}
}
