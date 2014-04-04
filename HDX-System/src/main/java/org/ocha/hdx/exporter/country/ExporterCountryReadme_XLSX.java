package org.ocha.hdx.exporter.country;

import java.util.List;

import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.exporter.helper.ReadmeHelperImpl.ReadmeSentence;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country - readme.
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
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) throws Exception {
		/* TODO i18n */

		// Creating the sheet
		final String safeName = WorkbookUtil.createSafeSheetName("Read me");
		final XSSFSheet sheet = workbook.createSheet(safeName);
		
		final List<ReadmeSentence> list = queryData.getReadmeHelper().getReadme(queryData.getClass());
		for (int i = 0; i < list.size(); i++) {
			final ReadmeSentence readmeSentence = list.get(i);
			XSSFRow row = sheet.getRow(readmeSentence.getRow());
			if(null == row) {
				row = sheet.createRow(readmeSentence.getRow());
			}
			if(6 == i) {
				createRowHeaderCell(readmeSentence.getSentence(), row, readmeSentence.getColumn());
			}
			else {
				createCell(row, readmeSentence.getColumn(), readmeSentence.getSentence());
			}
		}
		 
		// Fix-width first column
		sheet.setColumnWidth(0, 8000);
		
		return super.export(workbook, queryData);
	}
}
