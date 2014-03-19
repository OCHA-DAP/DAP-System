package org.ocha.hdx.exporter.indicator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.persistence.entity.view.IndicatorData;
import org.ocha.hdx.service.ExporterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractExporterIndicator_XLSX extends Exporter_XLSX<ExporterIndicatorQueryData> {

	private static Logger logger = LoggerFactory.getLogger(AbstractExporterIndicator_XLSX.class);

	public AbstractExporterIndicator_XLSX(final Exporter<XSSFWorkbook, ExporterIndicatorQueryData> exporter) {
		super(exporter);
	}

	public AbstractExporterIndicator_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	protected XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterIndicatorQueryData queryData, final Map<Long, Map<String, IndicatorData>> data, final String sheetName) {
		// TODO i18n, UT

		// Create the sheet
		final String safeName = WorkbookUtil.createSafeSheetName(sheetName);
		final XSSFSheet sheet = workbook.createSheet(safeName);

		// Find min and max year values 
		// (there may be holes in the list of years, but we want a column for each year between minYear and maxYear)
		long minYear = Long.MAX_VALUE;
		long maxYear = Long.MIN_VALUE;
		for (final Long year : data.keySet()) {
			if (year < minYear) {
				minYear = year;
			}
			if (year > maxYear) {
				maxYear = year;
			}
		}
		logger.debug("Min year = " + minYear + ", max year = " + maxYear);

		// Create header cells for years
		final Map<Long, Integer> yearColumns = new HashMap<Long, Integer>();
		final XSSFRow headersRow = sheet.createRow(0);
		int columnIndex = 1;
		for (Long year = maxYear; year >= minYear; year--) {
			createColumnHeaderCell(year, headersRow, columnIndex);
			yearColumns.put(year, columnIndex);
			logger.debug("Year " + year + " is in column " + columnIndex);
			++columnIndex;
		}

		// For each year, there may be a different set of countries, so we have to parse them all first.
		// We will sort it after.
		final Set<String> countryCodes = new HashSet<String>();
		for (final Long year : data.keySet()) {
			final Map<String, IndicatorData> countryMap = data.get(year);
			countryCodes.addAll(countryMap.keySet());
		}
		logger.debug("# of countries = " + countryCodes.size());

		// Create country rows with country header
		final Map<String, XSSFRow> countryRows = new HashMap<String, XSSFRow>();
		int rowIndex = 1;
		for (final String countryCode : new TreeSet<String>(countryCodes)) {
			final XSSFRow countryRow = sheet.createRow(rowIndex);
			createRowHeaderCell(countryCode, countryRow, 0);
			countryRows.put(countryCode, countryRow);
			logger.debug("Country " + countryCode + " is in row " + rowIndex);
			++rowIndex;
		}

		for (final Long year : data.keySet()) {
			logger.debug("Indicator data year : " + year);
			final Map<String, IndicatorData> countryMap = data.get(year);
			for (final String countryCode : new TreeSet<String>(countryMap.keySet())) {
				logger.debug("\tCountry code : " + countryCode);
				final IndicatorData indicatorData = countryMap.get(countryCode);
				final XSSFRow countryRow = countryRows.get(countryCode);
				createNumCell(countryRow, yearColumns.get(year), indicatorData.getIndicatorValue());
			}
		}

		// Freeze the headers
		// Freeze the first column
		sheet.createFreezePane(1, 1, 1, 1);

		// Auto size the columns
		sheet.autoSizeColumn(0);
		for (final int i: yearColumns.values()) {
			sheet.autoSizeColumn(i);
		}
		return super.export(workbook, queryData);
	}
}
