/**
 *
 */
package org.ocha.hdx.validation.prevalidator;

import java.util.HashSet;
import java.util.Map;

import org.ocha.hdx.config.ConfigurationConstants;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.validation.Response;
import org.ocha.hdx.validation.exception.WrongParametersForValidationException;
import org.springframework.stereotype.Component;

/**
 * @author alexandru-m-g
 * 
 */
@Component
public class AllowedIndicatorTypesValidatorCreator implements IPreValidatorCreator {

	public static final int IND_TYPE_POSITION = 2;

	public static final String NAME = "Allowed Indicator Types";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ocha.hdx.validation.prevalidator.IPreValidator#getPreValidatorName()
	 */
	@Override
	public String getPreValidatorName() {
		return NAME;
	}

	@Override
	public IPreValidator create(final Map<String, AbstractConfigEntry> generalConfig) throws WrongParametersForValidationException {
		return new AllowedIndicatorTypesValidator(generalConfig);
	}

	public class AllowedIndicatorTypesValidator implements IPreValidator {

		private final HashSet<String> allowedTypesSet = new HashSet<String>();

		public AllowedIndicatorTypesValidator(final Map<String, AbstractConfigEntry> generalConfig) {
			final AbstractConfigEntry allowedIndicatorTypesEntry = generalConfig.get(ConfigurationConstants.GeneralConfiguration.ALLOWED_INDICATOR_TYPES.getLabel());
			if (allowedIndicatorTypesEntry != null) {
				final String[] types = allowedIndicatorTypesEntry.getEntryValue().split(ConfigurationConstants.SEPARATOR);
				if (types != null && types.length > 0) {
					for (final String type : types) {
						if (type != null && type.length() > 0) {
							this.allowedTypesSet.add(type.trim());
						}
					}
				}
			}
		}

		@Override
		public String getPreValidatorName() {
			return NAME;
		}

		@Override
		public Response validate(final String[] line) {
			final Response response = new Response();

			final String indTypeCode = line[IND_TYPE_POSITION];

			if (indTypeCode != null && this.allowedTypesSet.contains(indTypeCode.trim())) {
				response.setDescription(String.format("Indicator type code %s is allowed", indTypeCode));
				response.setStatus(ValidationStatus.SUCCESS);
			} else {
				response.setDescription(String.format("Indicator type code %s not allowed.", indTypeCode));
				response.setStatus(ValidationStatus.WARNING);
			}

			return response;
		}

		@Override
		public boolean useable() {
			return this.allowedTypesSet.size() > 0;
		}

	}

}
