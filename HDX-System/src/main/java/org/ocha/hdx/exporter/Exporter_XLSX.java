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
 * XLSX implementation of the export strategy. This class should be implemented for every report needing an XLSX export.
 * 
 * @author bmichiels
 * 
 */
public abstract class Exporter_XLSX<QD extends QueryData> extends AbstractExporter<XSSFWorkbook, QD> {

	public Exporter_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public Exporter_XLSX(final Exporter<XSSFWorkbook, QD> exporter) {
		super(exporter);
	}

	public static final String DATE_FORMAT_01 = "yyyy-mm-dd";

	// /////
	// Fonts
	// /////

	/**
	 * Get the default header font.
	 * 
	 * @param workbook
	 * @return The default header font
	 */
	protected static Font getHeaderFont(final Workbook workbook) {
		final Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
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

	// /////
	// Cells
	// /////

	/**
	 * Create header cells. Headers are bold and horizontally centered.
	 * 
	 * @param wb
	 *            the workbook
	 * @param headers
	 *            the headers
	 */
	public static void createHeaderCells(final XSSFSheet sheet, final List<Object> headers) {
		createHeaderCells(sheet, headers.toArray(new Object[] {}));
	}

	public static void createHeaderCells(final XSSFSheet sheet, final Object[] headers) {

		// Create the title row
		final XSSFRow titleRow = sheet.createRow((short) 0);

		// Create the header cells
		for (int i = 0; i < headers.length; i++) {
			createHeaderCell(headers[i], titleRow, i);
		}
	}

	public static XSSFCell createHeaderCell(final Object header, final XSSFRow titleRow, final int columnIndex) {
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

	public static XSSFCell createCell(final XSSFRow row, final short columnIndex, final String value) {
		final XSSFCell cell = row.createCell(columnIndex);
		cell.setCellValue(value);
		return cell;
	}

	public static XSSFCell createNumCell(final XSSFRow row, final short columnIndex, final Number value) {
		final XSSFCell cell = row.createCell(columnIndex);
		if(null != value) {
			cell.setCellValue((Double) value);
		}
		return cell;
	}

	public static XSSFCell createUrlCell(final XSSFRow row, final short columnIndex, final String value, final String address) {
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

	public static XSSFCell createDateCell(final XSSFRow row, final short columnIndex, final Date date, final String format) {
		final Workbook workbook = row.getSheet().getWorkbook();
		final CreationHelper createHelper = workbook.getCreationHelper();

		final CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(format));

		final XSSFCell cell = row.createCell(columnIndex);
		cell.setCellValue(date);
		cell.setCellStyle(cellStyle);
		return cell;
	}

	// /////
	// I/O
	// /////

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
