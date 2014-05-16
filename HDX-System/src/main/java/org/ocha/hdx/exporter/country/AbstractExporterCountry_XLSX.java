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
import org.ocha.hdx.service.ExporterService;

/**
 * Abstract exporter for all SW country-centric sheets (except overview).
 * 
 * @author bmichiels
 */
public abstract class AbstractExporterCountry_XLSX extends Exporter_XLSX<ExporterCountryQueryData> {

	public AbstractExporterCountry_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	public AbstractExporterCountry_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	protected XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData, final Map<String, ReportRow> data, final String sheetName) throws Exception {
		// TODO i18n, UT

		// Create the sheet
		final String safeName = WorkbookUtil.createSafeSheetName(sheetName);
		final XSSFSheet sheet = workbook.createSheet(safeName);

		// Define the headers
		final ArrayList<Object> headers = new ArrayList<Object>();
		headers.add("Indicator ID");
		headers.add("Indicator name");
		headers.add("Units");

		// Retrieve years from the data, as specifying 0 for fromYear/toYear in the queryData allows for earliest/latest data available.
		final int fromYear = getYear(data, "from");
		final int toYear = getYear(data, "to");

		// We may have holes in the series of years,
		// so we map each year to the corresponding column index.
		// Years are presented in reverse order in the sheet.
		final Map<Integer, Integer> yearToColum = new HashMap<Integer, Integer>();
		for (int year = toYear; year >= fromYear; year--) {
			headers.add(year);
			yearToColum.put(year, headers.size() - 1);
		}

		// Assign the headers to the title row
		createColumnHeaderCells(sheet, headers);

		// TODO Set the indicators info (cells A2:Dx), maybe create a custom query for this.

		// Fill with the data
		// We start right just after the headers row
		int rowIndex = 1;

		// We iterate over the indicator type codes
		for (final String indicatorTypeCode : data.keySet()) {

			// The data for this indicator type code
			final ReportRow reportRow = data.get(indicatorTypeCode);

			// Create a new row
			final XSSFRow row = sheet.createRow(rowIndex);
			rowIndex++;

			// With the indicator type code, we create a link to the Indicator definitions sheet
			createLinkCell(row, 0, reportRow.getIndicatorTypeCode(), "'Indicator definitions'!A1");
			createCell(row, 1, reportRow.getIndicatorName());
			// createCell(row, 2, reportRow.getSourceCode());
			createCell(row, 2, reportRow.getUnit());

			// Keep track of the indicator types processed
			trackIndicatorTypes(queryData, reportRow, sheetName);

			// createDatasetSummaryCell(reportRow, 4, row);
			// createCell(row, 4, reportRow.getMetadata().get(MetadataName.DATASET_SUMMARY));
			// createCell(row, 5, reportRow.getMetadata().get(MetadataName.MORE_INFO));
			// createCell(row, 6, reportRow.getMetadata().get(MetadataName.TERMS_OF_USE));
			// createCell(row, 7, reportRow.getMetadata().get(MetadataName.METHODOLOGY));

			// Get the values for this indicator type code for each considered year
			for (int year = fromYear; year <= toYear; year++) {
				final int columnIndex = yearToColum.get(year);
				final Double value = reportRow.getDoubleValue(year);
				// If there is a value for this year...
				if (null != value) {
					// ... we put it in its cell
					createNumCell(row, columnIndex, value);
				} else {
					// otherwise we put just a simple space to avoid unwanted effect in the sheet
					createCell(row, columnIndex, " ");
				}
			}
		}

		// Freeze the headers
		// Freeze the 3 first columns
		sheet.createFreezePane(3, 1, 3, 1);

		// Auto size the columns,
		// except Indicator ID which is fixed
		for (int i = 0; i < (headers.size() + data.keySet().size()); i++) {
			if (0 == i) {
				sheet.setColumnWidth(i, 3000);
			} else {
				sheet.autoSizeColumn(i);
			}
		}

		/*
		// Show processed indicator types so far
		final Set<DataSerieInSheet> dataSerieInSheets = (Set<DataSerieInSheet>) queryData.getChannelValue(CHANNEL_KEYS.DATA_SERIES);
		logger.debug("Indicator types after " + this.getClass().getName() + " : ");
		if(null != dataSerieInSheets) {
			for (final DataSerieInSheet dataSerieInSheet : dataSerieInSheets) {
				logger.debug("\t" + dataSerieInSheet.getDataSerie().getIndicatorCode() + " => " + dataSerieInSheet.getSheetName());
			}
		}
		else {
			logger.debug("\tNone");
		}
		*/

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
