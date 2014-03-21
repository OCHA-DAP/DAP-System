package org.ocha.hdx.exporter.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.exporter.QueryData;
import org.ocha.hdx.exporter.country.ExporterCountryReadme_XLSX;
import org.ocha.hdx.exporter.indicator.ExporterIndicatorReadme_XLSX;

public class ReadmeHelperImpl implements ReadmeHelper {

	public static final class ReadmeSentence {
		private final String sentence;
		private final int row;
		private final int column;

		public ReadmeSentence(final String sentence, final int row, final int column) {
			super();
			this.sentence = sentence;
			this.row = row;
			this.column = column;
		}

		public String getSentence() {
			return sentence;
		}

		public int getRow() {
			return row;
		}

		public int getColumn() {
			return column;
		}
	}

	private static final List<String> SENTENCES = new ArrayList<String>();
	private static final Map<Class<? extends Exporter_XLSX<? extends QueryData>>, List<ReadmeSentence>> DESCRIPTORS = new HashMap<Class<? extends Exporter_XLSX<? extends QueryData>>, List<ReadmeSentence>>();

	public ReadmeHelperImpl() {
		SENTENCES.add("This document is an extract of data compiled by automated extraction of data from a variety of online sources and manually compiled sources.");
		SENTENCES.add("The compilation of data is performed on an ongong basis, generally once per day for the automated sources and less frequently for manually compiled sources.");
		SENTENCES.add("The compilation is done by the Humanitarian Data Exchange (HDX), a project of the UN Office for the Coordination of Humanitarian Affairs and a part of the ReliefWeb Labs.");
		SENTENCES.add("More information is available at http://hdx.rwlabs.org");
		SENTENCES
				.add("Indicators are organized into several thematic tabs in this file.  All are annual indicators with the exception of the \"5-year indicators\" tab which groups the small set of indicators with 5-year periods.");
		SENTENCES.add("Field Definitions");
		SENTENCES.add("Attribute ID");
		SENTENCES
				.add("A unique identifier within HDX for an attribute of an entity (typically a country or crisis).  Attributes are information that changes rarely if at all, like country name, currency code, etc.  It is provided here to assist those who may be working with the HDX API to extract data from our databases.");
		SENTENCES.add("Attribute Name");
		SENTENCES
				.add("A human readable name for an attribute of an entity (typically a country or crisis). Attributes are information that changes rarely if at all, like country name, currency code, etc.  ");
		SENTENCES.add("Source dataset");
		SENTENCES.add("A unique identifier within HDX for the data source from which the information was obtained.");
		SENTENCES.add("Indicator ID");
		SENTENCES
				.add("A unique identifier within HDX for an indicator.  Indicators are something that can be measured and compared in a time series and applies to a country, crisis, or other entity.  The Indicator ID is provided here to assist those who may be working with the HDX API to extract data from our databases.");
		SENTENCES.add("Indicator name");
		SENTENCES.add("A human-readable name for an indicator.  Indicators are something that can be measured and compared in a time series and applies to a country, crisis, or other entity.  ");
		SENTENCES.add("Units");
		SENTENCES.add("The units of the indicator.");
		SENTENCES.add("Dataset summary");
		SENTENCES
				.add("A summary of how the indicator was compiled by the source.  This field contains information from the data source, and may contain commentary from the HDX team regarding the indicator. ");
		SENTENCES.add("More info");
		SENTENCES.add("A source of additional information about the indicator, generally from the organisation from which the indicator was obtained. ");
		SENTENCES.add("Terms of use");
		SENTENCES
				.add("The terms of use from the source of the indicator.  HDX cannot provide interpretation or further information about the terms of use.  Contact the source organization for such questions (see \"more info\").");
		SENTENCES.add("HDX methodology");
		SENTENCES
				.add("A description of any data processing performed by HDX in compiling the data.  If there is no information in this field, you can assume that the data has been pulled from the source using some form of script, has been validated against acceptable minimum and maxiumum values, and that the units have not changed from the source.  Additional processing will be described here. ");

		DESCRIPTORS.put(ExporterCountryReadme_XLSX.class, getCountryReadme());
		DESCRIPTORS.put(ExporterIndicatorReadme_XLSX.class, getIndicatorReadme());
	}

	private static List<ReadmeSentence> getCountryReadme() {
		final List<ReadmeSentence> result = new ArrayList<ReadmeHelperImpl.ReadmeSentence>();
		int rowIndex = 0;
		result.add(new ReadmeSentence(SENTENCES.get(0), rowIndex++, 0));
		result.add(new ReadmeSentence(SENTENCES.get(1), rowIndex++, 0));
		result.add(new ReadmeSentence(SENTENCES.get(2), rowIndex++, 0));
		result.add(new ReadmeSentence(SENTENCES.get(3), rowIndex++, 0));
		result.add(new ReadmeSentence(SENTENCES.get(4), rowIndex++, 0));
		result.add(new ReadmeSentence("", rowIndex++, 0));
		result.add(new ReadmeSentence(SENTENCES.get(5), rowIndex++, 0));
		result.add(new ReadmeSentence(SENTENCES.get(6), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(7), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(8), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(9), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(10), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(11), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(12), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(13), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(14), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(15), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(16), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(17), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(18), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(19), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(20), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(21), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(22), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(23), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(24), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(25), rowIndex++, 1));
		return result;
	}

	private static List<ReadmeSentence> getIndicatorReadme() {
		final List<ReadmeSentence> result = new ArrayList<ReadmeHelperImpl.ReadmeSentence>();
		int rowIndex = 0;
		result.add(new ReadmeSentence(SENTENCES.get(0), rowIndex++, 0));
		result.add(new ReadmeSentence(SENTENCES.get(1), rowIndex++, 0));
		result.add(new ReadmeSentence(SENTENCES.get(2), rowIndex++, 0));
		result.add(new ReadmeSentence(SENTENCES.get(3), rowIndex++, 0));
		result.add(new ReadmeSentence("", rowIndex++, 0));
		result.add(new ReadmeSentence(SENTENCES.get(5), rowIndex++, 0));
		result.add(new ReadmeSentence(SENTENCES.get(10), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(11), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(12), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(13), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(14), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(15), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(16), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(17), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(18), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(19), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(20), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(21), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(22), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(23), rowIndex++, 1));
		result.add(new ReadmeSentence(SENTENCES.get(24), rowIndex, 0));
		result.add(new ReadmeSentence(SENTENCES.get(25), rowIndex++, 1));
		return result;
	}

	@Override
	public List<ReadmeSentence> getReadme(final Class<? extends Exporter_XLSX<? extends QueryData>> clazz) {
		return DESCRIPTORS.get(clazz);
	}
}
