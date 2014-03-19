package org.ocha.hdx.exporter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.service.ExporterService;

/**
 * XLSX implementation of the export of reports. 
 * This class should be implemented for every report needing an XLSX export.
 * 
 * Exporters follow the Decorator pattern (see constructors).
 * 
 * @author bmichiels
 * 
 */
public abstract class Exporter_XLSX<QD extends QueryData> extends AbstractExporter<XSSFWorkbook, QD> {

	/**
	 * This constructor is for the final exporter of a chain of exporters.
	 * It propagates the exporter service to its predecessors for proper generation.
	 * @param exporterService
	 */
	public Exporter_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}
	
	/**
	 * This constructor takes the following exporter in the global export chain.
	 * @param exporter
	 */
	public Exporter_XLSX(final Exporter<XSSFWorkbook, QD> exporter) {
		super(exporter);
	}

	/**
	 * Simple date format for formatting / parsing dates.
	 */
	public static final String DATE_FORMAT_01 = "yyyy-mm-dd";

	/* ***** */
	/* Fonts */
	/* ***** */

	/**
	 * Get the default header font.
	 * 
	 * @param workbook
	 * @return The default header font
	 */
	protected static Font getHeaderFont(final Workbook workbook) {
		final Font font = workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		return font;
	}

	/**
	 * Get the URL font.
	 * 
	 * @param workbook
	 * @return The URL font
	 */
	private static Font getUrlFont(final Workbook workbook) {
		final Font font = workbook.createFont();
		font.setUnderline(Font.U_SINGLE);
		font.setColor(IndexedColors.BLUE.getIndex());
		return font;
	}

	/* ***** */
	/* Cells */
	/* ***** */

	/**
	 * Create column header cells. 
	 * Column headers are bold, vertically-bottom aligned, and horizontally centered.
	 * Headers start at column 0.
	 * 
	 * @param wb
	 *            the workbook
	 * @param headers
	 *            the headers
	 */
	public static void createColumnHeaderCells(final XSSFSheet sheet, final Object[] headers) {
		// Create the title row
		final XSSFRow titleRow = sheet.createRow(0);

		// Create the header cells
		for (int i = 0; i < headers.length; i++) {
			createColumnHeaderCell(headers[i], titleRow, i);
		}
	}

	public static void createColumnHeaderCells(final XSSFSheet sheet, final List<Object> headers) {
		createColumnHeaderCells(sheet, headers.toArray(new Object[] {}));
	}

	public static XSSFCell createColumnHeaderCell(final Object header, final XSSFRow titleRow, final int columnIndex) {
		final XSSFCell cell = titleRow.createCell(columnIndex);
		final CellStyle cellStyle = titleRow.getSheet().getWorkbook().createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setFont(getHeaderFont(titleRow.getSheet().getWorkbook()));
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_BOTTOM);
		cell.setCellStyle(cellStyle);
		if (header instanceof Number) {
			cell.setCellValue(((Number) header).doubleValue());
		} else {
			cell.setCellValue(header.toString());
		}
		return cell;
	}

	/**
	 * Create row header cells. 
	 * Row headers are bold, vertically-top aligned and left-aligned.
	 * Headers start at row 0.
	 * 
	 * @param wb
	 *            the workbook
	 * @param headers
	 *            the headers
	 */
	public static void createRowHeaderCells(final XSSFSheet sheet, final Object[] headers) {
		for (int i = 0; i < headers.length; i++) {
			// Create the title row
			XSSFRow row = sheet.getRow(i);
			if (null == row) {
				row = sheet.createRow(i);
			}

			// Create the header cell
			createRowHeaderCell(headers[i], row, 0);
		}
	}

	public static XSSFCell createRowHeaderCell(final Object header, final XSSFRow titleRow, final int columnIndex) {
		final XSSFCell cell = titleRow.createCell(columnIndex);
		final CellStyle cellStyle = titleRow.getSheet().getWorkbook().createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		cellStyle.setFont(getHeaderFont(titleRow.getSheet().getWorkbook()));
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		cell.setCellStyle(cellStyle);
		if (header instanceof Number) {
			cell.setCellValue(((Number) header).doubleValue());
		} else {
			cell.setCellValue(header.toString());
		}
		return cell;
	}

	/**
	 * Create a simple cell.
	 * @param row The row to create the cell into
	 * @param columnIndex The column index to create the cell into
	 * @param value The value to put in the cell 
	 * @return The newly created cell
	 */
	public static XSSFCell createCell(final XSSFRow row, final int columnIndex, final String value) {
		final XSSFCell cell = row.createCell(columnIndex);
		cell.setCellValue(null == value ? "" : value);
		return cell;
	}

	/**
	 * Create a numeric cell.
	 * @param row The row to create the cell into
	 * @param columnIndex The column index to create the cell into
	 * @param value The value to put in the cell 
	 * @return The newly created cell
	 */
	public static XSSFCell createNumCell(final XSSFRow row, final int columnIndex, final Number value) {
		final XSSFCell cell = row.createCell(columnIndex);
		if (null != value) {
			cell.setCellValue((Double) value);
		}
		return cell;
	}

	/**
	 * Create a URL cell.
	 * @param row The row to create the cell into
	 * @param columnIndex The column index to create the cell into
	 * @param value The value to put in the cell 
	 * @param address The actual link
	 * @return The newly created cell
	 */
	public static XSSFCell createUrlCell(final XSSFRow row, final int columnIndex, final String value, final String address) {
		final Workbook workbook = row.getSheet().getWorkbook();
		final CreationHelper createHelper = workbook.getCreationHelper();

		final CellStyle hlink_style = workbook.createCellStyle();
		hlink_style.setFont(getUrlFont(workbook));

		final XSSFCell cell = row.createCell(columnIndex);
		cell.setCellValue(value);

		final org.apache.poi.ss.usermodel.Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);
		link.setAddress(address);
		cell.setHyperlink(link);
		cell.setCellStyle(hlink_style);
		return cell;
	}

	/**
	 * Create a date cell.
	 * @param row The row to create the cell into
	 * @param columnIndex The column index to create the cell into
	 * @param date The date to put in the cell 
	 * @param format The date format
	 * @return The newly created cell
	 */
	public static XSSFCell createDateCell(final XSSFRow row, final int columnIndex, final Date date, final String format) {
		final Workbook workbook = row.getSheet().getWorkbook();
		final CreationHelper createHelper = workbook.getCreationHelper();

		final CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(format));

		final XSSFCell cell = row.createCell(columnIndex);
		cell.setCellValue(date);
		cell.setCellStyle(cellStyle);
		return cell;
	}

	/* *** */
	/* I/O */
	/* *** */

	public static void writeFile(final Workbook wb, final String path) {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(path);
			wb.write(fileOut);
			fileOut.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
