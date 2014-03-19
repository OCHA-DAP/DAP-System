package org.ocha.hdx.exporter.country;

import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a readme.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryReadme_XLSX extends Exporter_XLSX<ExporterCountryQueryData> {

	public ExporterCountryReadme_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountryReadme_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) {
		/* TODO i18n */

		// Creating the sheet
		final String safeName = WorkbookUtil.createSafeSheetName("Read me");
		final XSSFSheet sheet = workbook.createSheet(safeName);

		final XSSFRow row0 = sheet.createRow(0);
		createCell(row0, 0, "This document is an extract of data compiled by automated extraction of data from a variety of online sources and manually compiled sources.");

		final XSSFRow row1 = sheet.createRow(1);
		createCell(row1, 0, "The compilation of data is performed on an ongong basis, generally once per day for the automated sources and less frequently for manually compiled sources.");
		
		final XSSFRow row2 = sheet.createRow(2);
		createCell(row2, 0, "The compilation is done by the Humanitarian Data Exchange (HDX), a project of the UN Office for the Coordination of Humanitarian Affairs and a part of the ReliefWeb Labs.");
		
		final XSSFRow row3 = sheet.createRow(3);
		createCell(row3, 0, "More information is available at http://hdx.rwlabs.org");
		
		final XSSFRow row4 = sheet.createRow(4);
		createCell(row4, 0, "Indicators are organized into several thematic tabs in this file.  All are annual indicators with the exception of the \"5-year indicators\" tab which groups the small set of indicators with 5-year periods.");
		
		final XSSFRow row6 = sheet.createRow(6);
		createRowHeaderCell("Field Definitions", row6, 0);
		 
		final XSSFRow row7 = sheet.createRow(7);
		createCell(row7, 0, "Attribute ID");
		createCell(row7, 1, "A unique identifier within HDX for an attribute of an entity (typically a country or crisis).  Attributes are information that changes rarely if at all, like country name, currency code, etc.  It is provided here to assist those who may be working with the HDX API to extract data from our databases.");
		 
		final XSSFRow row8 = sheet.createRow(8);
		createCell(row8, 0, "Attribute Name");
		createCell(row8, 1, "A human readable name for an attribute of an entity (typically a country or crisis). Attributes are information that changes rarely if at all, like country name, currency code, etc.  ");
		 
		final XSSFRow row9 = sheet.createRow(9);
		createCell(row9, 0, "Source dataset");
		createCell(row9, 1, "A unique identifier within HDX for the data source from which the information was obtained.");
		 
		final XSSFRow row10 = sheet.createRow(10);
		createCell(row10, 0, "Indicator ID");
		createCell(row10, 1, "A unique identifier within HDX for an indicator.  Indicators are something that can be measured and compared in a time series and applies to a country, crisis, or other entity.  The Indicator ID is provided here to assist those who may be working with the HDX API to extract data from our databases.");
		 
		final XSSFRow row11 = sheet.createRow(11);
		createCell(row11, 0, "Indicator name");
		createCell(row11, 1, "A human-readable name for an indicator.  Indicators are something that can be measured and compared in a time series and applies to a country, crisis, or other entity.  ");
		 
		final XSSFRow row12 = sheet.createRow(12);
		createCell(row12, 0, "Units");
		createCell(row12, 1, "The units of the indicator.");
		 
		final XSSFRow row13 = sheet.createRow(13);
		createCell(row13, 0, "Dataset summary");
		createCell(row13, 1, "A summary of how the indicator was compiled by the source.  This field contains information from the data source, and may contain commentary from the HDX team regarding the indicator. ");
		 
		final XSSFRow row14 = sheet.createRow(14);
		createCell(row14, 0, "More info");
		createCell(row14, 1, "A source of additional information about the indicator, generally from the organisation from which the indicator was obtained. ");
		 
		final XSSFRow row15 = sheet.createRow(15);
		createCell(row15, 0, "Terms of use");
		createCell(row15, 1, "The terms of use from the source of the indicator.  HDX cannot provide interpretation or further information about the terms of use.  Contact the source organization for such questions (see \"more info\").");
		 
		final XSSFRow row16 = sheet.createRow(16);
		createCell(row16, 0, "HDX methodology");
		createCell(row16, 1, "A description of any data processing performed by HDX in compiling the data.  If there is no information in this field, you can assume that the data has been pulled from the source using some form of script, has been validated against acceptable minimum and maxiumum values, and that the units have not changed from the source.  Additional processing will be described here. ");
		 
		// Auto size first column
		sheet.autoSizeColumn(0);

		
		return super.export(workbook, queryData);
	}
}
