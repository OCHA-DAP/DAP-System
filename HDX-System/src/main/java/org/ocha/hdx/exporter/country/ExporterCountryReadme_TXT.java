package org.ocha.hdx.exporter.country;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_File;
import org.ocha.hdx.exporter.helper.ReadmeHelperImpl.ReadmeSentence;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country - readme.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryReadme_TXT extends Exporter_File<ExporterCountryQueryData> {

	public ExporterCountryReadme_TXT(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterCountryReadme_TXT(final Exporter<File, ExporterCountryQueryData> exporter) {
		super(exporter);
	}

	@Override
	public File export(final File file, final ExporterCountryQueryData queryData) throws Exception {
		/* TODO i18n */

		// The readme sentences
		final List<ReadmeSentence> readme = queryData.getReadmeHelper().getReadme(queryData.getClass());
		final List<String> content = new ArrayList<String>();
		ReadmeSentence previousSentence = null;
		for (final ReadmeSentence sentence : readme) {
			if ((null != previousSentence) && (sentence.getRow() == previousSentence.getRow())) {
				final int index = content.size() - 1;
				final String line = content.get(index) + " : " + sentence.getSentence();
				content.set(index, line);

			} else {
				final String line = sentence.getSentence();
				content.add(line);
			}
			previousSentence = sentence;
		}

		content.add("");
		content.add("");
		content.add("");
		content.add("COUNTRY OVERVIEW");

		// The overview data
		final List<Object[]> overviewData = exporterService.getCountryOverviewData(queryData);
		for (final Object[] objects : overviewData) {
			content.add(objects[1] + " : " + objects[2]);
		}

		// Write the file
		writeTXTFile(content, file);

		return file;
	}
}
