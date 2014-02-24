/**
 *
 */
package org.ocha.hdx.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocha.hdx.config.ConfigurationConstants;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.persistence.entity.configs.IndicatorResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.validation.Response;
import org.ocha.hdx.validation.itemvalidator.IValidator;
import org.ocha.hdx.validation.prevalidator.IPreValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author alexandru-m-g
 *
 */
public abstract class AbstractValidatingImporter implements HDXImporter {

	private static Logger logger = LoggerFactory.getLogger(AbstractValidatingImporter.class);

	private Map<String, AbstractConfigEntry> resourceEntriesMap;

	/**
	 * The key here will be indicator type code + source code
	 */
	private Map<String, Map<String, AbstractConfigEntry>> indicatorEntriesMap;

	/**
	 * The key here will be indicator type code + source code. Flag if there was already a problem reported for this ind type + source.
	 */
	private Map<String, Boolean> indicatorErrorMap;

	/**
	 * All existing validators. The key is the validator's name.
	 */
	private Map<String, IValidator> validatorsMap;

	/**
	 * All existing pre-validators. The key is the pre-validator's name.
	 */
	private Map<String, IPreValidator> preValidatorsMap;

	/**
	 * Cache of validators per ind type and source. The key is the indicator type code + source code.
	 */
	private Map<String, List<IValidator>> cachedIndicatorValidatorsMap;

	private List<IPreValidator> preValidators;

	private final ValidationReport report;

	public AbstractValidatingImporter(final ResourceConfiguration resourceConfiguration, final List<IValidator> validators,
			final List<IPreValidator> preValidators, final ValidationReport report) {
		super();
		this.report = report;
		if (resourceConfiguration != null) {
			if (resourceConfiguration.getGeneralConfigEntries() != null) {
				generaterResourceEntriesMap(resourceConfiguration);
			}
			if (resourceConfiguration.getIndicatorConfigEntries() != null) {
				generateIndicatorEntriesMap(resourceConfiguration);
			} else {
				logger.warn("No indicator and source specific configuration found. No validation will be performed");

			}
		} else {
			logger.warn("No configuration found. No validation will be performed");
		}

		if (validators != null && validators.size() > 0) {
			this.validatorsMap = new HashMap<String, IValidator>();
			for (final IValidator validator : validators) {
				this.validatorsMap.put(validator.getValidatorName(), validator);
			}
		} else {
			logger.warn("There are no validators available.");
		}

		if (preValidators != null && preValidators.size() > 0) {
			this.preValidatorsMap	= new HashMap<String, IPreValidator>();
			for (final IPreValidator preValidator : preValidators) {
				this.preValidatorsMap.put(preValidator.getPreValidatorName(), preValidator);
			}
		}
		
		this.preValidators	= this.findPreValidators();
	}

	private void generateIndicatorEntriesMap(final ResourceConfiguration resourceConfiguration) {
		this.indicatorEntriesMap = new HashMap<String, Map<String, AbstractConfigEntry>>();
		for (final IndicatorResourceConfigEntry indEntry : resourceConfiguration.getIndicatorConfigEntries()) {
			final String outerKey = indEntry.getIndicatorType().getCode() + indEntry.getSource().getCode();
			Map<String, AbstractConfigEntry> indSrcMap = this.indicatorEntriesMap.get(outerKey);
			if (indSrcMap == null) {
				indSrcMap = new HashMap<String, AbstractConfigEntry>();
				this.indicatorEntriesMap.put(outerKey, indSrcMap);
			}
			indSrcMap.put(indEntry.getEntryKey(), indEntry);
		}
	}

	private void generaterResourceEntriesMap(final ResourceConfiguration resourceConfiguration) {
		this.resourceEntriesMap = new HashMap<String, AbstractConfigEntry>();
		for (final AbstractConfigEntry entry : resourceConfiguration.getGeneralConfigEntries()) {
			this.resourceEntriesMap.put(entry.getEntryKey(), entry);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ocha.hdx.importer.HDXImporter#prepareDataForImport(java.io.File)
	 */
	@Override
	public PreparedData prepareDataForImport(final File file) {
		final List<PreparedIndicator> preparedIndicators = new ArrayList<>();
		final File valueFile = this.findValueFile(file);

		try (final BufferedReader br = new BufferedReader(new FileReader(valueFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				try {
					final String[] values = this.getValuesFromLine(line);
					if (this.preValidation(values)) {
						final PreparedIndicator preparedIndicator = new PreparedIndicator();
						this.populatePreparedIndicator(preparedIndicator, values);

						if (this.validation(preparedIndicator)) {
							preparedIndicators.add(preparedIndicator);
						}

					}
				} catch (RuntimeException re) {
					logger.warn(re.getMessage());
				}
				
				
			}

			return new PreparedData(true, preparedIndicators);
		} catch (final IOException e) {
			logger.debug(e.getMessage());
			return new PreparedData(false, null);
		}
	}

	protected boolean validation(final PreparedIndicator preparedIndicator) {
		boolean ret	= true;
		final List<IValidator> validators = this.findValidators(preparedIndicator);
		if (validators == null || validators.size() == 0) {
			logger.warn("No validators found for " + preparedIndicator);
		} else {
			final String key	= preparedIndicator.getIndicatorTypeCode() + preparedIndicator.getSourceCode();
			for (final IValidator validator : validators) {
				final Response response	=
						validator.validate(preparedIndicator, this.resourceEntriesMap, this.indicatorEntriesMap.get(key));
				if (! verifyResponse(response) ) {
					ret	= false;
				}
			}
		}

		return ret;

	}

	private boolean verifyResponse(final Response response) {
		switch(response.getStatus()){
			case ERROR:
				this.report.addEntry(response.getStatus(), response.getDescription());
				return false;
			case WARNING:
				this.report.addEntry(response.getStatus(), response.getDescription());
				return true;
			default:
				return true;
		}
	}
	
	protected List<IPreValidator> findPreValidators() {
		List<IPreValidator> retList	= new ArrayList<IPreValidator>();
		AbstractConfigEntry entry	= this.resourceEntriesMap.get(ConfigurationConstants.PREVALIDATORS);
		if ( entry != null ) {
			String [] preValidatorNames	= entry.getEntryValue().split(ConfigurationConstants.SEPARATOR);
			if (preValidatorNames != null)
				for (String name : preValidatorNames) {
					IPreValidator preValidator	= this.preValidatorsMap.get(name);
					if ( preValidator != null )
						retList.add(preValidator);
				}
		}
		return retList;
	}

	protected List<IValidator> findValidators(final PreparedIndicator preparedIndicator) {
		final boolean sourceCodeNotEmpty = preparedIndicator.getSourceCode() != null && preparedIndicator.getSourceCode().length() > 0;
		final boolean indicatorTypeCodeNotEmpty = preparedIndicator.getIndicatorTypeCode() != null && preparedIndicator.getIndicatorTypeCode().length() > 0;

		if (sourceCodeNotEmpty && indicatorTypeCodeNotEmpty) {
			return cachedFindValidators(preparedIndicator.getIndicatorTypeCode(), preparedIndicator.getSourceCode());

		}
		return null;

	}

	protected List<IValidator> cachedFindValidators(final String indTypeCode, final String sourceCode) {
		if (this.cachedIndicatorValidatorsMap == null) {
			this.cachedIndicatorValidatorsMap = new HashMap<String, List<IValidator>>();
		}
		final String indAndSrcCode = indTypeCode + sourceCode;
		List<IValidator> cachedValidatorList = this.cachedIndicatorValidatorsMap.get(indAndSrcCode);
		if (cachedValidatorList == null) {
			cachedValidatorList = new ArrayList<IValidator>();
			this.cachedIndicatorValidatorsMap.put(indAndSrcCode, cachedValidatorList);

			/*
			 * Find configurations specific for this ind type and source pair
			 */
			final Map<String, AbstractConfigEntry> indConfigMap = this.indicatorEntriesMap.get(indAndSrcCode);
			if (indConfigMap != null) {
				final AbstractConfigEntry validatorsEntry = indConfigMap.get(ConfigurationConstants.VALIDATORS);

				if (validatorsEntry != null && validatorsEntry.getEntryValue() != null) {
					final String[] validatorNames = validatorsEntry.getEntryValue().split(ConfigurationConstants.SEPARATOR);
					if (validatorNames != null) {
						for (final String name : validatorNames) {
							final IValidator validator = this.validatorsMap.get(name);
							if (validator != null) {
								cachedValidatorList.add(validator);
							} else {
								logger.warn("No validator found for name " + name);
							}
						}
					}
				} else {
					logger.warn("No validators configured for this ind type and source pair: " + indAndSrcCode);
				}
			} else {
				logger.warn("No configuration found for this ind type and source pair:" + indAndSrcCode);
			}

			if ( cachedValidatorList.size() == 0 ) {
				this.report.addEntry(ValidationStatus.WARNING,
						String.format("No validators found for indicator type '%s' and source '%s'", indTypeCode, sourceCode));
			}
		}
		return cachedValidatorList;
	}

	protected boolean preValidation(final String[] values) {
		boolean ret = true;
		for (final IPreValidator preValidator : this.preValidators) {
			final Response response = preValidator.validate(values, this.resourceEntriesMap);
			if (! verifyResponse(response) ) {
				ret	= false;
			}
		}
		return ret;
	}

	protected abstract String[] getValuesFromLine(final String line);

	protected abstract File findValueFile(File file);

	protected abstract void populatePreparedIndicator(final PreparedIndicator preparedIndicator, String[] values);

}
