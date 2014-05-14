package org.ocha.hdx.exporter.country;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.exporter.QueryData.CHANNEL_KEYS;
import org.ocha.hdx.exporter.country.ExporterCountryQueryData.DataSerieInSheet;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country - definitions.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryDefinitions_XLSX extends Exporter_XLSX<ExporterCountryQueryData> {

	public ExporterCountryDefinitions_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountryDefinitions_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) throws Exception {

		final TreeSet<DataSerieInSheet> dataSeries = new TreeSet<DataSerieInSheet>();

		if (null != queryData.getChannelValue(CHANNEL_KEYS.DATA_SERIES)) {
			dataSeries.addAll((Set<DataSerieInSheet>) queryData.getChannelValue(CHANNEL_KEYS.DATA_SERIES));
		}

		/* TODO i18n */

		// Creating the sheet
		final String safeName = WorkbookUtil.createSafeSheetName("Indicator definitions");
		final XSSFSheet sheet = workbook.createSheet(safeName);

		workbook.setSheetOrder(safeName, 1);

		// Assign the headers to the title row
		final ArrayList<Object> headers = new ArrayList<Object>();
		headers.add("Indicator type");
		headers.add("Definition");
		headers.add("Source dataset");
		headers.add("Dataset summary");
		headers.add("More Info");
		headers.add("Terms of Use");
		headers.add("HDX Methodology");
		createColumnHeaderCells(sheet, headers);

		// Fill with the data
		int rowIndex = 0;
		for (final DataSerieInSheet dataSerie : dataSeries) {

			// Create the row (not overwriting the headers :-) )
			final XSSFRow row = sheet.createRow(++rowIndex);

			// Indicator type
			createLinkCell(row, 0, dataSerie.getDataSerie().getIndicatorCode(), "'" + dataSerie.getSheetName() + "'!A1");

			// Definition
			final IndicatorType indicatorType = exporterService.getIndicatorTypeByCode(dataSerie.getDataSerie().getIndicatorCode());
			createCell(row, 1, indicatorType.getName().getDefaultValue());

			if ((dataSerie.getDataSerie().getSourceCode() != null) && !dataSerie.getDataSerie().getSourceCode().isEmpty()) {
				final Source source = exporterService.getSourceByCode(dataSerie.getDataSerie().getSourceCode());
				if (source != null) {
					createCell(row, 2, source.getName().getDefaultValue());
				}
			}

			final List<DataSerieMetadata> results = exporterService.getMetadataForDataSerie(dataSerie.getDataSerie());
			for (final DataSerieMetadata dataSerieMetadata : results) {
				switch (dataSerieMetadata.getEntryKey()) {
				case DATASET_SUMMARY:
					createCell(row, 3, dataSerieMetadata.getEntryValue().getDefaultValue());
					break;

				case MORE_INFO:
					createCell(row, 4, dataSerieMetadata.getEntryValue().getDefaultValue());
					break;
				case TERMS_OF_USE:
					createCell(row, 5, dataSerieMetadata.getEntryValue().getDefaultValue());
					break;
				case METHODOLOGY:
					createCell(row, 6, dataSerieMetadata.getEntryValue().getDefaultValue());
					break;
				default:
					break;
				}
			}

		}

		// Auto size the columns, except DATASET_SUMMARY, which have a fixed width
		for (int i = 0; i < headers.size(); i++) {
			if (i > 2) {
				sheet.setColumnWidth(i, 20000);
			} else {
				sheet.autoSizeColumn(i);
			}
		}

		return super.export(workbook, queryData);
	}
}
