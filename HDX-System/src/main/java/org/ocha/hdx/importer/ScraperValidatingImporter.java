package org.ocha.hdx.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocha.hdx.importer.coltransformer.AbstractColumnsTransformer;
import org.ocha.hdx.importer.coltransformer.ScraperColumnsTransformer;
import org.ocha.hdx.importer.helper.IndicatorTypeInformationHolder;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.persistence.entity.configs.IndicatorResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.dictionary.SourceDictionary;
import org.ocha.hdx.service.IndicatorCreationService;
import org.ocha.hdx.validation.itemvalidator.IValidatorCreator;
import org.ocha.hdx.validation.prevalidator.IPreValidatorCreator;

/**
 * 
 * Sample implementation of the {@link AbstractValidatingImporter} that has the same functionality as {@link ScraperImporter} plus the validation part
 * 
 * @author alexandru-m-g
 * 
 */
public class ScraperValidatingImporter extends AbstractValidatingImporter {

	Map<String, String> sourcesMap = new HashMap<>();
	/**
	 * The key here will be indicator type code + source code
	 */
	Map<String, AbstractColumnsTransformer> colTransformers = new HashMap<String, AbstractColumnsTransformer>();

	Map<String, IndicatorType> indicatorTypeCache = new HashMap<String, IndicatorType>();

	private final IndicatorCreationService indicatorCreationService;

	public ScraperValidatingImporter(final List<SourceDictionary> sourceDictionaries, final ResourceConfiguration resourceConfiguration, final List<IValidatorCreator> validatorCreators,
			final List<IPreValidatorCreator> preValidatorCreators, final ValidationReport report, final IndicatorCreationService indicatorCreationService) {

		super(resourceConfiguration, validatorCreators, preValidatorCreators, report);

		this.indicatorCreationService = indicatorCreationService;

		if (sourceDictionaries != null) {
			for (final SourceDictionary sourceDictionary : sourceDictionaries) {
				sourcesMap.put(sourceDictionary.getUnnormalizedName(), sourceDictionary.getSource().getCode());
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
				if ("CG310".equals(values[2])) {
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
	protected PreparedIndicator createPreparedIndicator(final String[] values) {
		final PreparedIndicator preparedIndicator = new PreparedIndicator();
		if (sourcesMap.containsKey(values[0])) {
			preparedIndicator.setSourceCode(sourcesMap.get(values[0]));
		} else {
			preparedIndicator.setSourceCode(values[0]);
		}
		preparedIndicator.setIndicatorTypeCode(values[2]);
		final String key = generateMapKey(preparedIndicator.getIndicatorTypeCode(), preparedIndicator.getSourceCode());
		final IndicatorTypeInformationHolder indTypeInfoHolder = getIndTypeInfoHolder(key);
		final Map<String, AbstractConfigEntry> indConfigMap = indTypeInfoHolder.getIndicatorEntries();

		final AbstractColumnsTransformer transformer = generateColumnsTransformer(key, indConfigMap, preparedIndicator.getIndicatorTypeCode(), preparedIndicator.getSourceCode());
		if (!transformer.isDisabled()) {

			preparedIndicator.setEntityCode(transformer.getEntityCode(values));
			preparedIndicator.setEntityTypeCode(transformer.getEntityTypeCode(values));

			preparedIndicator.setStart(transformer.getStartDate(values));
			preparedIndicator.setEnd(transformer.getEndDate(values));
			preparedIndicator.setPeriodicity(transformer.getPeriodicity(values));
			preparedIndicator.setValue(transformer.getValue(values));
			preparedIndicator.setIndicatorImportConfig(transformer.getIndicatorImportConfig(values));

			return preparedIndicator;
		}
		return null;

	}

	/**
	 * @param key
	 * @param indConfigMap
	 * @return
	 */
	private AbstractColumnsTransformer generateColumnsTransformer(final String key, final Map<String, AbstractConfigEntry> indConfigMap, final String indTypeCode, final String sourceCode) {
		AbstractColumnsTransformer transformer = colTransformers.get(key);
		if (transformer == null) {
			final List<IndicatorResourceConfigEntry> embeddedConfigs = indicatorCreationService.findEmbeddedConfigs(indTypeCode, sourceCode);
			for (final IndicatorResourceConfigEntry indicatorResourceConfigEntry : embeddedConfigs) {
				final AbstractConfigEntry value = indConfigMap.get(indicatorResourceConfigEntry.getEntryKey());
				if (value == null) {
					indConfigMap.put(indicatorResourceConfigEntry.getEntryKey(), indicatorResourceConfigEntry);
				}
			}
			transformer = new ScraperColumnsTransformer(key, resourceEntriesMap, indConfigMap, sourcesMap);
			colTransformers.put(key, transformer);
		}
		return transformer;
	}

}
