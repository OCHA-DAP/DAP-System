package org.ocha.hdx.validation.prevalidator;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.config.ConfigurationConstants;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfigEntry;
import org.ocha.hdx.validation.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author alexandru-m-g
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class AllowedIndicatorTypesValidatorTest {

	@Autowired
	private AllowedIndicatorTypesValidatorCreator allowedIndicatorTypesValidatorsCreator;

	@Test
	public final void testValidate() {

		final Map<String, AbstractConfigEntry> generalConfig = new HashMap<String, AbstractConfigEntry>();
		generalConfig.put(ConfigurationConstants.GeneralConfiguration.ALLOWED_INDICATOR_TYPES.getLabel(),
				new ResourceConfigEntry(ConfigurationConstants.GeneralConfiguration.ALLOWED_INDICATOR_TYPES.getLabel(), "test1_indicator_type" + ConfigurationConstants.SEPARATOR
						+ "test2_indicator_type"));

		final String[] testLine = new String[AllowedIndicatorTypesValidatorCreator.IND_TYPE_POSITION + 1];
		testLine[AllowedIndicatorTypesValidatorCreator.IND_TYPE_POSITION] = "test1_indicator_type";

		final IPreValidator allowedIndicatorTypesValidator = this.allowedIndicatorTypesValidatorsCreator.create(generalConfig);

		final Response responseSuccess1 = allowedIndicatorTypesValidator.validate(testLine);
		assertEquals(ValidationStatus.SUCCESS, responseSuccess1.getStatus());

		testLine[AllowedIndicatorTypesValidatorCreator.IND_TYPE_POSITION] = "test2_indicator_type";
		final Response responseSuccess2 = allowedIndicatorTypesValidator.validate(testLine);
		assertEquals(ValidationStatus.SUCCESS, responseSuccess2.getStatus());

		testLine[AllowedIndicatorTypesValidatorCreator.IND_TYPE_POSITION] = "test3_indicator_type";
		final Response responseError = allowedIndicatorTypesValidator.validate(testLine);
		assertEquals(ValidationStatus.WARNING, responseError.getStatus());
	}

}
