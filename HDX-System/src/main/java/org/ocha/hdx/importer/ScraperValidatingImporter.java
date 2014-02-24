package org.ocha.hdx.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.persistence.entity.dictionary.SourceDictionary;
import org.ocha.hdx.validation.itemvalidator.IValidator;
import org.ocha.hdx.validation.prevalidator.IPreValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * Sample implementation of the {@link AbstractValidatingImporter} that has the same functionality as {@link ScraperImporter}
 * plus the validation part
 *
 * @author alexandru-m-g
 *
 */
public class ScraperValidatingImporter extends AbstractValidatingImporter {

	private static Logger logger = LoggerFactory.getLogger(ScraperValidatingImporter.class);


	Map<String, String> sourcesMap = new HashMap<>();

	public ScraperValidatingImporter(final List<SourceDictionary> sourceDictionaries, final ResourceConfiguration resourceConfiguration,
			final List<IValidator> validators, final List<IPreValidator> preValidators,
			final ValidationReport report) {

		super(resourceConfiguration, validators, preValidators, report);

		if (sourceDictionaries != null) {
			for (final SourceDictionary sourceDictionary : sourceDictionaries) {
				this.sourcesMap.put(sourceDictionary.getId().getUnnormalizedName(), sourceDictionary.getSource().getCode());
			}
		}
	}

	public Map<String, String> getCountryList(final File file) {
		final Map<String, String> result = new HashMap<String, String>();
		final File parent = file.getParentFile();
		final File valueFile = new File(parent, "value.csv");

		try (final BufferedReader br = new BufferedReader(new FileReader(valueFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				// use comma as separator
				final String[] values = line.split(",");
				if ("_m49-name".equals(values[2])) {
					result.put(values[1], values[4]);
				}
			}
			return result;
		} catch (final IOException e) {
			return result;
		}
	}

	@Override
	protected String[] getValuesFromLine(final String line) {
		return line.split(",");
	}

	@Override
	protected File findValueFile(final File file) {
		final File parent = file.getParentFile();
		final File valueFile = new File(parent, "value.csv");
		return valueFile;
	}

	@Override
	protected void populatePreparedIndicator(final PreparedIndicator preparedIndicator, final String [] values) {
		if (this.sourcesMap.containsKey(values[0])) {
			preparedIndicator.setSourceCode(this.sourcesMap.get(values[0]));
		} else {
			preparedIndicator.setSourceCode(values[0]);
		}
		preparedIndicator.setEntityCode(values[1]);
		preparedIndicator.setEntityTypeCode("country");
		preparedIndicator.setIndicatorTypeCode(values[2]);

		final TimeRange timeRange = new TimeRange(values[3]);
		preparedIndicator.setStart(timeRange.getStart());
		preparedIndicator.setEnd(timeRange.getEnd());
		preparedIndicator.setPeriodicity(timeRange.getPeriodicity());

		final Double valueAsDouble = Double.parseDouble(values[4]);
		// FIXME we should deal about units later, here for
		// population we must X1000
		if ("PSP010".equals(values[2])) {
			preparedIndicator.setValue(new IndicatorValue(valueAsDouble * 1000));
		} else {
			preparedIndicator.setValue(new IndicatorValue(valueAsDouble));
		}

		preparedIndicator.setInitialValue(values[4]);


	}
}
