package org.ocha.hdx.exporter.indicator;

import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.persistence.entity.view.IndicatorTypeOverview;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for an indicator - overview.
 * 
 * @author bmichiels
 * 
 */
public class ExporterIndicatorTypeOverview_XLSX extends Exporter_XLSX<ExporterIndicatorQueryData> {

	public ExporterIndicatorTypeOverview_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterIndicatorTypeOverview_XLSX(final Exporter<XSSFWorkbook, ExporterIndicatorQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterIndicatorQueryData queryData) throws Exception {

		final IndicatorTypeOverview data = exporterService.getIndicatorTypeOverviewData(queryData);

		/* TODO i18n */

		// Creating the sheet
		final String safeName = WorkbookUtil.createSafeSheetName("Overview");
		final XSSFSheet sheet = workbook.createSheet(safeName);

		// Assign the headers to the title row
		final String[] headers = { "Indicator ID", "Indicator name", "Source dataset", "Units", "Data summary", "More info", "Terms of use", "HDX methodology" };
		createRowHeaderCells(sheet, headers);

		// Get the rows (should have been created at header creation time)
		// and set the value
		final XSSFRow row0 = sheet.getRow(0);
		createCell(row0, 1, data.getIndicatorTypeCode());

		final XSSFRow row1 = sheet.getRow(1);
		createCell(row1, 1, data.getIndicatorTypeDefaultValue());

		final XSSFRow row2 = sheet.getRow(2);
		createCell(row2, 1, data.getSourceDefaultValue());

		final XSSFRow row3 = sheet.getRow(3);
		createCell(row3, 1, data.getUnitDefaultValue());

		final XSSFRow row4 = sheet.getRow(4);
		createCell(row4, 1, data.getDataSummaryDefaultValue());

		final XSSFRow row5 = sheet.getRow(5);
		createCell(row5, 1, data.getMoreInfoDefaultValue());

		final XSSFRow row6 = sheet.getRow(6);
		createCell(row6, 1, data.getTermsOfUseDefaultValue());

		final XSSFRow row7 = sheet.getRow(7);
		createCell(row7, 1, data.getMethodologyDefaultValue());

		// Auto size the columns
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);

		return super.export(workbook, queryData);
	}
}
