package org.ocha.hdx.exporter.indicator;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_File;
import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for an indicator metadata.
 * 
 * @author bmichiels
 * 
 */
public class ExporterIndicatorMetadata_CSV extends Exporter_File<ExporterIndicatorMetadataQueryData> {

	public static final SimpleDateFormat SDF_01 = new SimpleDateFormat("yyyy");

	public ExporterIndicatorMetadata_CSV(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterIndicatorMetadata_CSV(final Exporter<File, ExporterIndicatorMetadataQueryData> exporter) {
		super(exporter);
	}

	@Override
	public File export(final File file, final ExporterIndicatorMetadataQueryData queryData) throws IOException {

		// Gathering the data
		final List<DataSerieMetadata> indicatorMetadataData = exporterService.getIndicatorMetadataData(queryData);

		// Define the headers
		final ArrayList<String> headers = new ArrayList<String>();
		headers.add("Indicator code"); // 0
		headers.add("Indicator name"); // 1
		headers.add("Source code"); // 2
		headers.add("Source name"); // 3
		headers.add("Unit code"); // 4
		headers.add("Unit name"); // 5
		headers.add("Dataset summary"); // 6
		headers.add("Methodology"); // 7
		headers.add("Date of dataset (min)"); // 8
		headers.add("Date of dataset (max)"); // 9
		headers.add("More Info"); // 10
		headers.add("Terms of Use"); // 11

		final int dataSize = headers.size();

		// We represent the content as a List of arrays,
		// each array being a line in the final CSV file.
		final List<String[]> content = new ArrayList<String[]>();

		// Assign the headers to the title row
		final String[] headerLine = headers.toArray(new String[] {});
		content.add(headerLine);

		// Rows are unique per indicator type / source
		final Map<String, String[]> rows = new HashMap<String, String[]>();
		for (final DataSerieMetadata data : indicatorMetadataData) {
			final String code = getCode(data);
			if (!rows.keySet().contains(code)) {
				final String[] row = new String[dataSize];
				initRow(row);
				rows.put(code, row);
				content.add(row);
			}
		}

		// Fill the data
		for (final DataSerieMetadata data : indicatorMetadataData) {

			final String[] row = rows.get(getCode(data));

			row[0] = data.getIndicatorType().getCode();
			row[1] = data.getIndicatorType().getName().getDefaultValue();
			row[2] = data.getSource().getCode();
			row[3] = data.getSource().getName().getDefaultValue();
			row[4] = data.getIndicatorType().getUnit().getCode();
			row[5] = data.getIndicatorType().getUnit().getName().getDefaultValue();

			final Map<String, Timestamp> minMaxDatesForDataSeries = getExporterService().getMinMaxDatesForDataSeries(new DataSerie(data.getIndicatorType().getCode(), data.getSource().getCode()));
			row[8] = null == minMaxDatesForDataSeries.get("MIN") ? "" : SDF_01.format(minMaxDatesForDataSeries.get("MIN"));
			row[9] = null == minMaxDatesForDataSeries.get("MAX") ? "" : SDF_01.format(minMaxDatesForDataSeries.get("MAX"));

			switch (data.getEntryKey()) {
			case DATASET_SUMMARY:
				row[6] = data.getEntryValue().getDefaultValue();
				break;
			case MORE_INFO:
				row[10] = data.getEntryValue().getDefaultValue();
				break;
			case TERMS_OF_USE:
				row[11] = data.getEntryValue().getDefaultValue();
				break;
			case METHODOLOGY:
				row[7] = data.getEntryValue().getDefaultValue();
				break;
			case VALIDATION_NOTES:
				// row[10] = data.getEntryValue().getDefaultValue();
				break;

			default:
				break;
			}
		}

		// Write the file
		writeCSVFile(content, ",", file);

		return file;
	}

	private static String getCode(final DataSerieMetadata data) {
		final String indicatorTypeCode = data.getIndicatorType().getCode();
		final String sourceCode = data.getSource().getCode();
		return indicatorTypeCode.hashCode() + "&&" + sourceCode.hashCode();
	}
}
