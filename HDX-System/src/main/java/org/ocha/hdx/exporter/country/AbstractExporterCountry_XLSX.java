package org.ocha.hdx.exporter.country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.persistence.entity.metadata.AdditionalData.EntryKey;
import org.ocha.hdx.service.ExporterService;

/**
 * Abstract exporter for all country-centric sheets (except overview).
 * @author bmichiels
 */
public abstract class AbstractExporterCountry_XLSX extends Exporter_XLSX<ExporterCountryQueryData> {

	public AbstractExporterCountry_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	public AbstractExporterCountry_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	protected XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData, final Map<String, ReportRow> data, final String sheetName) {
		// TODO i18n, UT

		// Create the sheet
		final String safeName = WorkbookUtil.createSafeSheetName(sheetName);
		final XSSFSheet sheet = workbook.createSheet(safeName);

		// Define the headers
		final ArrayList<Object> headers = new ArrayList<Object>();
		headers.add("Indicator ID");
		headers.add("Indicator name");
		headers.add("Source dataset");
		headers.add("Units");
		headers.add("Dataset summary");
		headers.add("More Info");
		headers.add("Terms of Use");
		headers.add("HDX Methodology");

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
		for (int year = toYear; year >= fromYear; year--) {
			headers.add(year);
			yearToColum.put(year, headers.size()-1);
		}

		// Assign the headers to the title row
		createColumnHeaderCells(sheet, headers);

		// TODO Set the indicators info (cells A2:Dx), maybe create a custom query for this.

		// Fill with the data
		// We start right just after the headers row
		int rowIndex = 1;

		for (final String indicatorTypeCode : data.keySet()) {
			final ReportRow reportRow = data.get(indicatorTypeCode);

			final XSSFRow row = sheet.createRow(rowIndex);
			rowIndex++;

			createCell(row, 0, reportRow.getIndicatorTypeCode());
			createCell(row, 1, reportRow.getIndicatorName());
			createCell(row, 2, reportRow.getSourceCode());
			createCell(row, 3, reportRow.getUnit());

			// createDatasetSummaryCell(reportRow, 4, row);
			createCell(row, 4, reportRow.getMetadata().get(EntryKey.DATASET_SUMMARY));
			createCell(row, 5, reportRow.getMetadata().get(EntryKey.MORE_INFO));
			createCell(row, 6, reportRow.getMetadata().get(EntryKey.TERMS_OF_USE));
			createCell(row, 7, reportRow.getMetadata().get(EntryKey.METHODOLOGY));

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
		sheet.createFreezePane(2, 1, 2, 1);

		// Auto size the columns
		// Except Indicator ID and Dataset summary which is fixed
		for (int i = 0; i < (headers.size() + data.keySet().size()); i++) {
			if (0 == i) {
				sheet.setColumnWidth(i, 3000);
			} else if (4 == i) {
				sheet.setColumnWidth(i, 20000);
			} else {
				sheet.autoSizeColumn(i);
			}
		}

		return super.export(workbook, queryData);

	}

	// private static void createDatasetSummaryCell(final ReportRow reportRow, final short position, final XSSFRow row) {
	// final String datasetSummary = reportRow.getDatasetSummary();
	//
	// if ((null != datasetSummary) && (50 < datasetSummary.length())) {
	// final XSSFCell cell = createCell(row, position, datasetSummary.substring(0, 50) + " ...");
	// final XSSFCreationHelper creationHelper = row.getSheet().getWorkbook().getCreationHelper();
	// final Drawing drawing = row.getSheet().createDrawingPatriarch();
	//
	// // When the comment box is visible, have it show in a 1x3 space
	// final ClientAnchor anchor = creationHelper.createClientAnchor();
	// anchor.setCol1(cell.getColumnIndex());
	// anchor.setCol2(cell.getColumnIndex() + 1);
	// anchor.setRow1(row.getRowNum());
	// anchor.setRow2(row.getRowNum() + 3);
	//
	// // Create the comment and set the text+author
	// final Comment comment = drawing.createCellComment(anchor);
	// final RichTextString str = creationHelper.createRichTextString(datasetSummary);
	// comment.setString(str);
	//
	// // Assign the comment to the cell cell.setCellComment(comment); } else {
	//
	// createCell(row, position, datasetSummary);
	//
	// }
	//
	// }

}
