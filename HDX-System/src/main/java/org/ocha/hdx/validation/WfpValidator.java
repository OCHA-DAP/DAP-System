package org.ocha.hdx.validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset;

/**
 * Validator for a WFP input file.
 */
public class WfpValidator implements HDXValidator {

	@Override
	public ValidationReport evaluateFile(final File file) {
		final ValidationReport report = new ValidationReport(CKANDataset.Type.WFP);
		try {

			// Read the XLSX file
			final InputStream inp = new FileInputStream(file);

			// Get the workbook
			final Workbook wb = WorkbookFactory.create(inp);

			// Get the first sheet
			final Sheet sheet = wb.getSheetAt(0);

			// Iterate the rows (first row is headers)
			final Iterator<Row> rowIterator = sheet.rowIterator();
			rowIterator.next();

			while (rowIterator.hasNext()) {
				final Row row = rowIterator.next();

				// Cell 0 contains country code (cannot be null)
				final Cell countryCodeCell = row.getCell(0);
				final String countryCode = countryCodeCell.getStringCellValue();
				if ((null == countryCode)  || (3 != countryCode.length())) {
					report.addEntry(ValidationStatus.ERROR, String.format("Row %d contains invalid country code (expected XXX) <%s>", row.getRowNum(), countryCode));
				}

				// Cell 1 contains country name (cannot be null)
				final Cell countryNameCell = row.getCell(1);
				final String countryName = countryNameCell.getStringCellValue();
				if ((null == countryName)  || (0 == countryName.length())) {
					report.addEntry(ValidationStatus.ERROR, String.format("Row %d contains invalid country name <%s>", row.getRowNum(), countryCode));
				}

				// Cell 4 contains year
				final Cell yearCell = row.getCell(4);
				final String yearAsString = "" + (Cell.CELL_TYPE_NUMERIC == yearCell.getCellType() ? yearCell.getNumericCellValue() : yearCell.getStringCellValue());
				switch (yearAsString.length()) {
				case 6:
					try {
						Integer.valueOf(yearAsString.substring(0, 4));
					} catch (final Exception e) {
						report.addEntry(ValidationStatus.ERROR, String.format("Row %d contains invalid year (expected yyyy or yyyy-yyyy) <%s>", row.getRowNum(), yearAsString));
					}
					break;
				case 9:
					try {
						Integer.valueOf(yearAsString.substring(0, 4));
					} catch (final Exception e) {
						report.addEntry(ValidationStatus.ERROR, String.format("Row %d contains invalid year (expected yyyy or yyyy-yyyy) <%s>", row.getRowNum(), yearAsString));
					}
					break;

				default:
					report.addEntry(ValidationStatus.ERROR, String.format("Row %d contains invalid year (expected yyyy or yyyy-yyyy) <%s>", row.getRowNum(), yearAsString));
					break;
				}

				// Cell 5 contains "poor" data
				final Cell poorCell = row.getCell(5);
				final String poorAsString = "" + poorCell.getNumericCellValue();
				try {
					Double.valueOf(poorAsString);
				} catch (final Exception e) {
					report.addEntry(ValidationStatus.ERROR, String.format("Row %d contains invalid 'poor' data (number) <%s>", row.getRowNum(), poorAsString));
				}

				// Cell 6 contains "borderline" data
				final Cell borderlineCell = row.getCell(6);
				final String borderlineAsString = "" + borderlineCell.getNumericCellValue();
				try {
					Double.valueOf(borderlineAsString);
				} catch (final Exception e) {
					report.addEntry(ValidationStatus.ERROR, String.format("Row %d contains invalid 'borderline' data (number) <%s>", row.getRowNum(), borderlineAsString));
				}

				// Cell 7 contains "acceptable" data
				final Cell acceptableCell = row.getCell(7);
				final String acceptableAsString = "" + acceptableCell.getNumericCellValue();
				try {
					Double.valueOf(acceptableAsString);
				} catch (final Exception e) {
					report.addEntry(ValidationStatus.ERROR, String.format("Row %d contains invalid 'acceptable' data (number) <%s>", row.getRowNum(), acceptableAsString));
				}
			}
		} catch (final Exception e) {
			report.addEntry(ValidationStatus.ERROR, String.format("Error caused by an exception <%s>", e.getMessage()));
		}
		return report;
	}
}
