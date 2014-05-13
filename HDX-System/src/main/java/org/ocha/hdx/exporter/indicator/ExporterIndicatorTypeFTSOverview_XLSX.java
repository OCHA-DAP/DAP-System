package org.ocha.hdx.exporter.indicator;

import javax.persistence.NoResultException;

import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.persistence.entity.view.IndicatorTypeOverview;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for an indicator FTS - overview.
 * 
 * @author bmichiels
 * 
 */
public class ExporterIndicatorTypeFTSOverview_XLSX extends Exporter_XLSX<ExporterIndicatorQueryData> {

	public ExporterIndicatorTypeFTSOverview_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterIndicatorTypeFTSOverview_XLSX(final Exporter<XSSFWorkbook, ExporterIndicatorQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterIndicatorQueryData queryData) throws Exception {

		queryData.setSourceCode("fts");

		// Creating the sheet
		final String safeName = WorkbookUtil.createSafeSheetName("Overview");
		final XSSFSheet sheet = workbook.createSheet(safeName);

		// Assign the headers to the title row
		final String[] headers = { "Indicator ID", "Indicator name", "Source dataset", "Units", "Data summary", "More info", "Terms of use", "HDX methodology" };
		createRowHeaderCells(sheet, headers);

		final String[] indicators = DataSerie.COUNTRY_OVERVIEW_FTS_indicatorsList;
		for (int i = 0; i < indicators.length; i++) {
			final String indicator = indicators[i];

			queryData.setIndicatorTypeCode(indicator);
			IndicatorTypeOverview data = null;
			try {
				data = exporterService.getIndicatorTypeOverviewData(queryData);
			} catch (final NoResultException e) {
				// Nothing to do
			}

			/* TODO i18n */

			final XSSFRow row0 = sheet.getRow(0);
			createCell(row0, i + 1, null == data ? "" : data.getIndicatorTypeCode());

			final XSSFRow row1 = sheet.getRow(1);
			createCell(row1, i + 1, null == data ? "" : data.getIndicatorTypeDefaultValue());

			final XSSFRow row2 = sheet.getRow(2);
			createCell(row2, i + 1, null == data ? "" : data.getSourceDefaultValue());

			final XSSFRow row3 = sheet.getRow(3);
			createCell(row3, i + 1, null == data ? "" : data.getUnitDefaultValue());

			final XSSFRow row4 = sheet.getRow(4);
			createCell(row4, i + 1, null == data ? "" : data.getDataSummaryDefaultValue());

			final XSSFRow row5 = sheet.getRow(5);
			createCell(row5, i + 1, null == data ? "" : data.getMoreInfoDefaultValue());

			final XSSFRow row6 = sheet.getRow(6);
			createCell(row6, i + 1, null == data ? "" : data.getTermsOfUseDefaultValue());

			final XSSFRow row7 = sheet.getRow(7);
			createCell(row7, i + 1, null == data ? "" : data.getMethodologyDefaultValue());

		}

		// Auto size the columns
		sheet.autoSizeColumn(0);

		return super.export(workbook, queryData);
	}
}
