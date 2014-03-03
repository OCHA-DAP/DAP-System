package org.ocha.hdx.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocha.hdx.importer.coltransformer.AbstractColumnsTransformer;
import org.ocha.hdx.importer.coltransformer.ScraperColumnsTransformer;
import org.ocha.hdx.importer.helper.IndicatorTypeInformationHolder;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.dictionary.SourceDictionary;
import org.ocha.hdx.service.IndicatorCreationService;
import org.ocha.hdx.validation.itemvalidator.IValidatorCreator;
import org.ocha.hdx.validation.prevalidator.IPreValidatorCreator;
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
	/**
	 * The key here will be indicator type code + source code
	 */
	Map<String, AbstractColumnsTransformer> colTransformers	= new HashMap<String, AbstractColumnsTransformer>();

	private final IndicatorCreationService indicatorCreationService;


	public ScraperValidatingImporter(final List<SourceDictionary> sourceDictionaries, final ResourceConfiguration resourceConfiguration,
			final List<IValidatorCreator> validatorCreators, final List<IPreValidatorCreator> preValidatorCreators,
			final ValidationReport report, final IndicatorCreationService indicatorCreationService) {

		super(resourceConfiguration, validatorCreators, preValidatorCreators, report);

		this.indicatorCreationService	= indicatorCreationService;

		if (sourceDictionaries != null) {
			for (final SourceDictionary sourceDictionary : sourceDictionaries) {
				this.sourcesMap.put(sourceDictionary.getId().getUnnormalizedName(), sourceDictionary.getSource().getCode());
			}
		}
	}

	@Override
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
	protected PreparedIndicator createPreparedIndicator(final String [] values) {
		final PreparedIndicator preparedIndicator	= new PreparedIndicator();
		if (this.sourcesMap.containsKey(values[0])) {
			preparedIndicator.setSourceCode(this.sourcesMap.get(values[0]));
		} else {
			preparedIndicator.setSourceCode(values[0]);
		}
		preparedIndicator.setIndicatorTypeCode(values[2]);
		final String key		= this.generateMapKey(preparedIndicator.getIndicatorTypeCode(), preparedIndicator.getSourceCode());
		final IndicatorTypeInformationHolder indTypeInfoHolder		= this.getIndTypeInfoHolder(key);
		final Map<String, AbstractConfigEntry> indConfigMap			= indTypeInfoHolder.getIndicatorEntries();

		if (indConfigMap != null && indConfigMap.size() > 0) {
			AbstractColumnsTransformer transformer = this.colTransformers.get(key);
			if (transformer == null) {
				transformer = new ScraperColumnsTransformer(this.resourceEntriesMap, indConfigMap, this.sourcesMap);
				this.colTransformers.put(key, transformer);
			}

			preparedIndicator.setEntityCode(transformer.getEntityCode(values));
			preparedIndicator.setEntityTypeCode(transformer.getEntityTypeCode(values));

			preparedIndicator.setStart(transformer.getStartDate(values));
			preparedIndicator.setEnd(transformer.getEndDate(values));
			preparedIndicator.setPeriodicity(transformer.getPeriodicity(values));
			preparedIndicator.setValue(transformer.getValue(values));
			preparedIndicator.setInitialValue(transformer.getInitialValue(values));

			return preparedIndicator;

		}
		else {
			if ( !indTypeInfoHolder.isErrorDisplayed() ) {
				indTypeInfoHolder.setErrorDisplayed(true);
				logger.warn( String.format("No configuration found for ind type %s and source %s",
					preparedIndicator.getIndicatorTypeCode(), preparedIndicator.getSourceCode()) );
			}

			return null;
		}

	}

	@Override
	public List<Indicator> transformToFinalFormat() {
		final List<Indicator> list	= new ArrayList<Indicator>();
		for (final PreparedIndicator preparedIndicator : this.preparedData.getIndicatorsToImport()) {
			try {
				final Indicator indicator	= this.indicatorCreationService.createIndicator(preparedIndicator);
				if ( this.validation(indicator) ) {
					list.add(indicator);
				}
			} catch (final Exception e) {
				logger.debug(String.format("Error trying to create preparedIndicator : %s", preparedIndicator.toString()));
			}
		}
		return list;
	}

}
