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
public class ColumnNumPreValidatorTest {

	@Autowired
	private ColumnNumPreValidatorCreator columnNumPreValidatorCreator;

	@Test
	public final void testValidate() {

		final Map<String, AbstractConfigEntry> generalConfig = new HashMap<String, AbstractConfigEntry>();
		generalConfig.put(ConfigurationConstants.GeneralConfiguration.MIN_NUM_OF_COLUMNS.getLabel(), new ResourceConfigEntry(ConfigurationConstants.GeneralConfiguration.MIN_NUM_OF_COLUMNS.getLabel(),
				"6"));

		final IPreValidator columnNumPreValidator = this.columnNumPreValidatorCreator.create(generalConfig);

		final Response responseSuccess1 = columnNumPreValidator.validate(new String[6]);
		assertEquals(ValidationStatus.SUCCESS, responseSuccess1.getStatus());

		final Response responseSuccess2 = columnNumPreValidator.validate(new String[7]);
		assertEquals(ValidationStatus.SUCCESS, responseSuccess2.getStatus());

		final Response responseError1 = columnNumPreValidator.validate(new String[5]);
		assertEquals(ValidationStatus.ERROR, responseError1.getStatus());
	}

}
