package org.ocha.hdx.exporter.country;

import java.util.ArrayList;
import java.util.Map;

import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
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
				for (int year = queryData.getFromYear(); year <= queryData.getToYear(); year++) {
					add(Integer.valueOf(year).toString());
				}
			}
		};

		// Assign the headers to the title row
		createHeaderCells(sheet, headers);

		// TODO Set the indicators info (cells A2:D5), maybe create a custom query for this.

		// Fill with the data
		int rowIndex = 1;

		for (final String indicatorCode : data.keySet()) {
			final ReportRow reportRow = data.get(indicatorCode);

			final XSSFRow row = sheet.createRow(rowIndex);
			rowIndex++;

			createCell(row, (short) 0, reportRow.getIndicatorCode());
			createCell(row, (short) 1, reportRow.getIndicatorName());
			createCell(row, (short) 2, reportRow.getDatasetId());
			createCell(row, (short) 3, reportRow.getUnit());
			for (int year = queryData.getFromYear(); year <= queryData.getToYear(); year++) {
				final short columnIndex = (short) (4 + year - queryData.getFromYear());
				createCell(row, columnIndex, reportRow.getValue(year));
			}

		}

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
