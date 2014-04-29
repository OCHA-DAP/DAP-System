package org.ocha.hdx.exporter.indicator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_File;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for an indicator metadata.
 * 
 * @author bmichiels
 * 
 */
public class ExporterIndicatorMetadata_CSV extends Exporter_File<ExporterIndicatorMetadataQueryData> {

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
		headers.add("Indicator code");
		headers.add("Indicator name");
		headers.add("Source code");
		headers.add("Source name");
		headers.add("Unit code");
		headers.add("Unit name");
		headers.add("Dataset summary");
		headers.add("More Info");
		headers.add("Terms of Use");
		headers.add("HDX Methodology");
		headers.add("Validation notes");

		final int dataSize = headers.size();

		// We represent the content as a List of arrays,
		// each array being a line in the final CSV file.
		final List<String[]> content = new ArrayList<String[]>();

		// Assign the headers to the title row
		final String[] headerLine = headers.toArray(new String[] {});
		content.add(headerLine);

		// There is only one row of data by source
		final Map<String, String[]> rows = new HashMap<String, String[]>();
		for (final DataSerieMetadata data : indicatorMetadataData) {
			final String code = data.getSource().getCode();
			if (!rows.keySet().contains(code)) {
				final String[] row = new String[dataSize];
				initRow(row);
				rows.put(code, row);
				content.add(row);
			}
		}

		// Fill the data
		for (final DataSerieMetadata data : indicatorMetadataData) {

			final String[] row = rows.get(data.getSource().getCode());
			row[0] = data.getIndicatorType().getCode();
			row[1] = data.getIndicatorType().getName().getDefaultValue();
			row[2] = data.getSource().getCode();
			row[3] = data.getSource().getName().getDefaultValue();
			row[4] = data.getIndicatorType().getUnit().getCode();
			row[5] = data.getIndicatorType().getUnit().getName().getDefaultValue();

			switch (data.getEntryKey()) {
			case DATASET_SUMMARY:
				row[6] = data.getEntryValue().getDefaultValue();
				break;
			case MORE_INFO:
				row[7] = data.getEntryValue().getDefaultValue();
				break;
			case TERMS_OF_USE:
				row[8] = data.getEntryValue().getDefaultValue();
				break;
			case METHODOLOGY:
				row[9] = data.getEntryValue().getDefaultValue();
				break;
			case VALIDATION_NOTES:
				row[10] = data.getEntryValue().getDefaultValue();
				break;

			default:
				break;
			}
		}

		// Write the file
		writeCSVFile(content, ",", file);

		return file;
	}
}
