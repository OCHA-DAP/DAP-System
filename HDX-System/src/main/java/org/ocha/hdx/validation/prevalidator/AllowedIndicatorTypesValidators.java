/**
 * 
 */
package org.ocha.hdx.validation.prevalidator;

import java.util.Map;

import org.ocha.hdx.config.ConfigurationConstants;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.validation.Response;
import org.springframework.stereotype.Component;

/**
 * @author alexandru-m-g
 *
 */
@Component
public class AllowedIndicatorTypesValidators implements IPreValidator {
	
	public static final int IND_TYPE_POSITION=2;

	public static final String NAME	= "Allowed Indicator Types";
	@Override
	public Response validate(String[] line, Map<String, AbstractConfigEntry> generalConfig) {
		final Response response 	= new Response();
		final AbstractConfigEntry allowedIndicatorTypesEntry	= generalConfig.get(ConfigurationConstants.ALLOWED_INDICATOR_TYPES);
		if ( allowedIndicatorTypesEntry == null ) {
			response.setDescription("Success");
			response.setStatus(ValidationStatus.SUCCESS);
		}
		else {
			String [] types	= allowedIndicatorTypesEntry.getEntryValue().split(ConfigurationConstants.SEPARATOR);
			if ( types != null && types.length > 0 ) {
				String indTypeCode	= line[IND_TYPE_POSITION];
				response.setDescription(String.format("Indicator type code %s not allowed.", indTypeCode));
				response.setStatus(ValidationStatus.ERROR);
				for (String type : types) {
					if ( indTypeCode != null && indTypeCode.equals(type) ) {
						response.setDescription("Success");
						response.setStatus(ValidationStatus.SUCCESS);
						break;
					}
				}
			}
			else {
				response.setDescription("Success");
				response.setStatus(ValidationStatus.SUCCESS);
			}
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see org.ocha.hdx.validation.prevalidator.IPreValidator#getPreValidatorName()
	 */
	@Override
	public String getPreValidatorName() {
		return NAME;
	}

}
