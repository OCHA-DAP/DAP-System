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
	private ColumnNumPreValidator columnNumPreValidator;

	@Test
	public final void testValidate() {

		final Map<String, AbstractConfigEntry> generalConfig	= new HashMap<String, AbstractConfigEntry>();
		generalConfig.put(ConfigurationConstants.NUM_OF_ALLOWED_COLUMNS, new ResourceConfigEntry(ConfigurationConstants.NUM_OF_ALLOWED_COLUMNS, "6"));

		final Response responseSuccess1	= this.columnNumPreValidator.validate(new String[6], generalConfig);
		assertEquals(ValidationStatus.SUCCESS, responseSuccess1.getStatus());

		final Response responseError2	= this.columnNumPreValidator.validate(new String[7], generalConfig);
		assertEquals(ValidationStatus.ERROR, responseError2.getStatus());

		final Response responseError1	= this.columnNumPreValidator.validate(new String[5], generalConfig);
		assertEquals(ValidationStatus.ERROR, responseError1.getStatus());
	}

}
