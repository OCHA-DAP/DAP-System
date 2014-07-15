package org.ocha.hdx.exporter.hierarchical;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.exporter.QueryData;
import org.ocha.hdx.exporter.country.ExporterCountryQueryData;
import org.ocha.hdx.exporter.helper.WFPReportRow;
import org.ocha.hdx.service.ExporterService;

public class HierarchicalEntityMultiIndicatorTypesExporter_XLSX extends Exporter_XLSX<QueryData> {

	List<WFPReportRow> rows;

	long minYear = Long.MAX_VALUE;
	long maxYear = Long.MIN_VALUE;

	public HierarchicalEntityMultiIndicatorTypesExporter_XLSX(final ExporterService exporterService, final List<WFPReportRow> rows) {
		super(exporterService);
		this.rows = rows;
		calculateBoundaries();

		// TODO Auto-generated constructor stub
	}

	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) throws Exception {
		final String safeName = WorkbookUtil.createSafeSheetName("");
		final XSSFSheet sheet = workbook.createSheet(safeName);

		// Define the headers

		final ArrayList<Object> headers = new ArrayList<Object>();

		headers.add("Administrative Unit");
		headers.add(" ");
		headers.add(" ");

		for (long i = minYear; i < maxYear + 1; i++) {
			headers.add(i);
			headers.add(" ");
			headers.add(" ");
		}

		createColumnHeaderCells(sheet, headers);

		sheet.addMergedRegion(new CellRangeAddress(0, // mention first row here
				0, // mention last row here, it is 1 as we are doing a column wise merging
				0, // mention first column of merging
				2 // mention last column to include in merge
		));

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 5));

		for (int i = 0; i < maxYear - minYear + 1; i++) {
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 6 + 3 * i, 8 + 3 * i));
		}

		final ArrayList<Object> headers2 = new ArrayList<Object>();
		headers2.add("Country");
		headers2.add("Adm1");
		headers2.add("Adm2");

		for (int i = 0; i < maxYear - minYear + 1; i++) {
			headers2.add("Poor");
			headers2.add("Borderline");
			headers2.add("Acceptable");
		}

		final XSSFRow titleRow = sheet.createRow(1);

		createColumnHeaderCells(sheet, titleRow, headers2);

		// Fill with the data
		// We start right just after the headers row
		int rowIndex = 2;

		for (final WFPReportRow wfpPreportRow : rows) {
			int cellIndex = 0;
			final XSSFRow xssfRow = sheet.createRow(rowIndex);
			rowIndex++;

			createCell(xssfRow, cellIndex++, wfpPreportRow.getHighestLevelAsString());
			createCell(xssfRow, cellIndex++, wfpPreportRow.getMediumLevelAsString());
			createCell(xssfRow, cellIndex++, wfpPreportRow.getLowestEntityAsString());

			for (long i = minYear; i < maxYear + 1; i++) {
				addValueToRow(xssfRow, cellIndex++, wfpPreportRow.getValue(Long.valueOf(i).intValue(), "PVF040"));
				addValueToRow(xssfRow, cellIndex++, wfpPreportRow.getValue(Long.valueOf(i).intValue(), "PVF050"));
				addValueToRow(xssfRow, cellIndex++, wfpPreportRow.getValue(Long.valueOf(i).intValue(), "WFP_ACCEPTABLE"));
			}

		}

		for (int i = 0; i < headers2.size(); i++) {

			sheet.autoSizeColumn(i);
		}

		return workbook;
	}

	private void addValueToRow(final XSSFRow xssfRow, final int position, final Double value) {
		if (null != value) {
			createNumCell(xssfRow, position, value);
		} else {
			createCell(xssfRow, position, " ");
		}
	}

	private void calculateBoundaries() {
		for (final WFPReportRow wfpPreportRow : rows) {
			minYear = Math.min(minYear, wfpPreportRow.getMinYear());
			maxYear = Math.max(maxYear, wfpPreportRow.getMaxYear());
		}

	}

}
