package org.ocha.hdx.exporter.indicator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.Exporter_File;
import org.ocha.hdx.exporter.helper.ReadmeHelperImpl.ReadmeSentence;
import org.ocha.hdx.persistence.entity.view.IndicatorTypeOverview;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for an indicator type - readme.
 * 
 * @author bmichiels
 * 
 */
public class ExporterIndicatorReadme_TXT extends Exporter_File<ExporterIndicatorQueryData> {

	public ExporterIndicatorReadme_TXT(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterIndicatorReadme_TXT(final Exporter<File, ExporterIndicatorQueryData> exporter) {
		super(exporter);
	}

	@Override
	public File export(final File file, final ExporterIndicatorQueryData queryData) throws Exception {
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
		content.add("INDICATOR OVERVIEW");

		// The overview data
		IndicatorTypeOverview overviewData = null;
		try {
			overviewData = exporterService.getIndicatorTypeOverviewData(queryData);
		} catch (final NoResultException e) {
			// Nothing to do
		}

		if (null != overviewData) {
			content.add("");
			content.add("Indicator ID : " + (null == overviewData.getIndicatorTypeCode() ? "" : overviewData.getIndicatorTypeCode()));
			content.add("Indicator name : " + (null == overviewData.getIndicatorTypeDefaultValue() ? "" : overviewData.getIndicatorTypeDefaultValue()));
			content.add("Source dataset : " + (null == overviewData.getSourceDefaultValue() ? "" : overviewData.getSourceDefaultValue()));
			content.add("Units : " + (null == overviewData.getUnitDefaultValue() ? "" : overviewData.getUnitDefaultValue()));
			content.add("Data summary : " + (null == overviewData.getDataSummaryDefaultValue() ? "" : overviewData.getDataSummaryDefaultValue()));
			content.add("More info : " + (null == overviewData.getMoreInfoDefaultValue() ? "" : overviewData.getMoreInfoDefaultValue()));
			content.add("Terms of use : " + (null == overviewData.getTermsOfUseDefaultValue() ? "" : overviewData.getTermsOfUseDefaultValue()));
			content.add("HDX methodology : " + (null == overviewData.getMethodologyDefaultValue() ? "" : overviewData.getMethodologyDefaultValue()));
		}
		else {
			content.add("");
			content.add("No data for indicator.");
		}

		
		// Write the file
		writeTXTFile(content, file);

		return file;
	}
}
