package org.ocha.hdx.exporter.indicator;

import javax.persistence.NoResultException;

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
public class ExporterIndicatorTypeRWOverview_XLSX extends Exporter_XLSX<ExporterIndicatorQueryData> {

	public ExporterIndicatorTypeRWOverview_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterIndicatorTypeRWOverview_XLSX(final Exporter<XSSFWorkbook, ExporterIndicatorQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterIndicatorQueryData queryData) throws Exception {

		queryData.setSourceCode("RW");

		queryData.setIndicatorTypeCode("RW001");
		IndicatorTypeOverview dataRW001 = null;
		try {
			dataRW001 = exporterService.getIndicatorTypeOverviewData(queryData);
		} catch (final NoResultException e) {
			// Nothing to do
		}

		queryData.setIndicatorTypeCode("RW002");
		IndicatorTypeOverview dataRW002 = null;
		try {
			dataRW002 = exporterService.getIndicatorTypeOverviewData(queryData);
		} catch (final NoResultException e) {
			// Nothing to do
		}

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
		createCell(row0, 1, null == dataRW001 ? "" : dataRW001.getIndicatorTypeCode());
		createCell(row0, 2, null == dataRW002 ? "" : dataRW002.getIndicatorTypeCode());

		final XSSFRow row1 = sheet.getRow(1);
		createCell(row1, 1, null == dataRW001 ? "" : dataRW001.getIndicatorTypeDefaultValue());
		createCell(row1, 2, null == dataRW002 ? "" : dataRW002.getIndicatorTypeDefaultValue());

		final XSSFRow row2 = sheet.getRow(2);
		createCell(row2, 1, null == dataRW001 ? "" : dataRW001.getSourceDefaultValue());
		createCell(row2, 2, null == dataRW002 ? "" : dataRW002.getSourceDefaultValue());

		final XSSFRow row3 = sheet.getRow(3);
		createCell(row3, 1, null == dataRW001 ? "" : dataRW001.getUnitDefaultValue());
		createCell(row3, 2, null == dataRW002 ? "" : dataRW002.getUnitDefaultValue());

		final XSSFRow row4 = sheet.getRow(4);
		createCell(row4, 1, null == dataRW001 ? "" : dataRW001.getDataSummaryDefaultValue());
		createCell(row4, 2, null == dataRW002 ? "" : dataRW002.getDataSummaryDefaultValue());

		final XSSFRow row5 = sheet.getRow(5);
		createCell(row5, 1, null == dataRW001 ? "" : dataRW001.getMoreInfoDefaultValue());
		createCell(row5, 2, null == dataRW002 ? "" : dataRW002.getMoreInfoDefaultValue());

		final XSSFRow row6 = sheet.getRow(6);
		createCell(row6, 1, null == dataRW001 ? "" : dataRW001.getTermsOfUseDefaultValue());
		createCell(row6, 2, null == dataRW002 ? "" : dataRW002.getTermsOfUseDefaultValue());

		final XSSFRow row7 = sheet.getRow(7);
		createCell(row7, 1, null == dataRW001 ? "" : dataRW001.getMethodologyDefaultValue());
		createCell(row7, 2, null == dataRW002 ? "" : dataRW002.getMethodologyDefaultValue());

		// Auto size the columns
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);

		return super.export(workbook, queryData);
	}
}
