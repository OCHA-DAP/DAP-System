/**
 *
 */
package org.ocha.hdx.validation.itemvalidator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.config.ConfigurationConstants;
import org.ocha.hdx.config.DummyConfigurationCreator;
import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.persistence.entity.configs.IndicatorResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.service.IndicatorCreationService;
import org.ocha.hdx.validation.Response;
import org.ocha.hdx.validation.exception.WrongParametersForValidationException;
import org.ocha.hdx.validation.util.DummyEntityCreatorWrapper;
import org.ocha.hdx.validation.util.DummyEntityCreatorWrapper.DummyEntityCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author alexandru-m-g
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml", "classpath:/ctx-persistence-test.xml" })
public class MinMaxValidatorTest {

	@Autowired
	private DummyConfigurationCreator dummyConfigurationCreator;

	@Autowired
	private MinMaxValidatorCreator minMaxValidatorCreator;

	@Autowired
	private DummyEntityCreatorWrapper dummyEntityCreatorWrapper;

	@Autowired
	private IndicatorCreationService indicatorCreationService;

	private DummyEntityCreator entityCreator;

	@Before
	public void setup() {
		this.entityCreator = this.dummyEntityCreatorWrapper.generateNewEntityCreator();
		this.entityCreator.createNeededIndicatorTypeAndSource();
	}

	@After
	public void tearDown() {
		this.entityCreator.deleteNeededIndicatorTypeAndSource();
	}

	/**
	 * Test method for
	 * {@link org.ocha.hdx.validation.itemvalidator.MinMaxValidatorCreator#validate(org.ocha.hdx.importer.PreparedIndicator, java.util.Map, java.util.Map)}
	 * .
	 */
	@Test
	public final void testValidateNumber() {

		final ResourceConfiguration config = this.dummyConfigurationCreator.createConfiguration();
		final Map<String, AbstractConfigEntry> generalConfig = new HashMap<String, AbstractConfigEntry>();

		for (final AbstractConfigEntry entry : config.getGeneralConfigEntries()) {
			generalConfig.put(entry.getEntryKey(), entry);
		}

		assertTrue(generalConfig.size() > 0);

		final Map<String, AbstractConfigEntry> indConfig = new HashMap<String, AbstractConfigEntry>();
		for (final IndicatorResourceConfigEntry indConfigEntry : config.getIndicatorConfigEntries()) {
			if (DummyConfigurationCreator.ESA_UNPD_WPP2012.equals(indConfigEntry.getSource().getCode()) && DummyConfigurationCreator.PSP080.equals(indConfigEntry.getIndicatorType().getCode())) {
				indConfig.put(indConfigEntry.getEntryKey(), indConfigEntry);
			}
		}

		assertTrue(indConfig.size() > 0);

		final PreparedIndicator preparedIndicator = new PreparedIndicator();

		preparedIndicator.setSourceCode(DummyConfigurationCreator.ESA_UNPD_WPP2012);
		preparedIndicator.setIndicatorTypeCode(DummyConfigurationCreator.PSP080);
		preparedIndicator.setEntityCode("Fi");
		preparedIndicator.setEntityTypeCode("country");
		preparedIndicator.setValue(new IndicatorValue(100d));

		Indicator indicator = this.indicatorCreationService.createIndicator(preparedIndicator);

		final IValidator minMaxValidator = this.minMaxValidatorCreator.create(generalConfig, indConfig);

		final Response responseSuccess = minMaxValidator.validate(indicator);
		assertEquals(ValidationStatus.SUCCESS, responseSuccess.getStatus());

		preparedIndicator.setValue(new IndicatorValue(0.0));
		indicator = this.indicatorCreationService.createIndicator(preparedIndicator);

		final Response responseError = minMaxValidator.validate(indicator);
		assertEquals(ValidationStatus.ERROR, responseError.getStatus());

		indConfig.remove(ConfigurationConstants.IndicatorConfiguration.MAX_VALUE.getLabel());
		try {
			final IValidator minMaxValidator2 = this.minMaxValidatorCreator.create(generalConfig, indConfig);
			fail("If a configuration parameter is missing it needs to raise an exception");
		} catch (final WrongParametersForValidationException e) {
			assertTrue(true);
		}

	}

	@Test
	public final void testValidateDate() {

		final ResourceConfiguration config = this.dummyConfigurationCreator.createConfiguration();
		final Map<String, AbstractConfigEntry> generalConfig = new HashMap<String, AbstractConfigEntry>();

		for (final AbstractConfigEntry entry : config.getGeneralConfigEntries()) {
			generalConfig.put(entry.getEntryKey(), entry);
		}

		final Map<String, AbstractConfigEntry> indConfig = new HashMap<String, AbstractConfigEntry>();

		final AbstractConfigEntry minConfigEntry = new AbstractConfigEntry(ConfigurationConstants.IndicatorConfiguration.MIN_VALUE.getLabel(), "05/05/2013") {
		};
		final AbstractConfigEntry maxConfigEntry = new AbstractConfigEntry(ConfigurationConstants.IndicatorConfiguration.MAX_VALUE.getLabel(), "31/12/2014") {
		};
		indConfig.put(minConfigEntry.getEntryKey(), minConfigEntry);
		indConfig.put(maxConfigEntry.getEntryKey(), maxConfigEntry);

		final PreparedIndicator preparedIndicator = new PreparedIndicator();

		final Date testDate = LocalDate.parse("05/05/2013", MinMaxValidatorCreator.DATE_FORMATTER).toDate();

		preparedIndicator.setSourceCode(DummyConfigurationCreator.ESA_UNPD_WPP2012);
		preparedIndicator.setIndicatorTypeCode(DummyConfigurationCreator.PSP080);
		preparedIndicator.setEntityCode("Fi");
		preparedIndicator.setEntityTypeCode("country");
		preparedIndicator.setValue(new IndicatorValue(testDate, ValueType.DATE));

		final Indicator indicator = this.indicatorCreationService.createIndicator(preparedIndicator);

		IValidator minMaxValidator = this.minMaxValidatorCreator.create(generalConfig, indConfig);
		final Response responseSuccess1 = minMaxValidator.validate(indicator);
		assertEquals(ValidationStatus.SUCCESS, responseSuccess1.getStatus());

		indConfig.put(ConfigurationConstants.IndicatorConfiguration.MIN_VALUE.getLabel(), new AbstractConfigEntry(ConfigurationConstants.IndicatorConfiguration.MIN_VALUE.getLabel(), "04/05/2013") {
		});
		minMaxValidator = this.minMaxValidatorCreator.create(generalConfig, indConfig);
		final Response responseSuccess2 = minMaxValidator.validate(indicator);
		assertEquals(ValidationStatus.SUCCESS, responseSuccess2.getStatus());

		indConfig.put(ConfigurationConstants.IndicatorConfiguration.MAX_VALUE.getLabel(), new AbstractConfigEntry(ConfigurationConstants.IndicatorConfiguration.MAX_VALUE.getLabel(), "04/05/2012") {
		});
		minMaxValidator = this.minMaxValidatorCreator.create(generalConfig, indConfig);
		final Response responseError = minMaxValidator.validate(indicator);
		assertEquals(ValidationStatus.ERROR, responseError.getStatus());

	}

	@Test
	public final void testValidateDatetime() {

		final ResourceConfiguration config = this.dummyConfigurationCreator.createConfiguration();
		final Map<String, AbstractConfigEntry> generalConfig = new HashMap<String, AbstractConfigEntry>();

		for (final AbstractConfigEntry entry : config.getGeneralConfigEntries()) {
			generalConfig.put(entry.getEntryKey(), entry);
		}

		final Map<String, AbstractConfigEntry> indConfig = new HashMap<String, AbstractConfigEntry>();

		final AbstractConfigEntry minConfigEntry = new AbstractConfigEntry(ConfigurationConstants.IndicatorConfiguration.MIN_VALUE.getLabel(), "05/05/2013 07:07:07") {
		};
		final AbstractConfigEntry maxConfigEntry = new AbstractConfigEntry(ConfigurationConstants.IndicatorConfiguration.MAX_VALUE.getLabel(), "31/12/2014 07:07:07") {
		};
		indConfig.put(minConfigEntry.getEntryKey(), minConfigEntry);
		indConfig.put(maxConfigEntry.getEntryKey(), maxConfigEntry);

		final PreparedIndicator preparedIndicator = new PreparedIndicator();

		final Date testDate = LocalDateTime.parse("05/05/2013 07:07:07", MinMaxValidatorCreator.DATE_TIME_FORMATTER).toDate();

		preparedIndicator.setSourceCode(DummyConfigurationCreator.ESA_UNPD_WPP2012);
		preparedIndicator.setIndicatorTypeCode(DummyConfigurationCreator.PSP080);
		preparedIndicator.setEntityCode("Fi");
		preparedIndicator.setEntityTypeCode("country");
		preparedIndicator.setValue(new IndicatorValue(testDate, ValueType.DATETIME));

		final Indicator indicator = this.indicatorCreationService.createIndicator(preparedIndicator);

		IValidator minMaxValidator = this.minMaxValidatorCreator.create(generalConfig, indConfig);

		final Response responseSuccess1 = minMaxValidator.validate(indicator);
		assertEquals(ValidationStatus.SUCCESS, responseSuccess1.getStatus());

		indConfig.put(ConfigurationConstants.IndicatorConfiguration.MIN_VALUE.getLabel(), new AbstractConfigEntry(ConfigurationConstants.IndicatorConfiguration.MIN_VALUE.getLabel(),
				"04/05/2013 06:06:06") {
		});
		minMaxValidator = this.minMaxValidatorCreator.create(generalConfig, indConfig);
		final Response responseSuccess2 = minMaxValidator.validate(indicator);
		assertEquals(ValidationStatus.SUCCESS, responseSuccess2.getStatus());

		indConfig.put(ConfigurationConstants.IndicatorConfiguration.MAX_VALUE.getLabel(), new AbstractConfigEntry(ConfigurationConstants.IndicatorConfiguration.MAX_VALUE.getLabel(),
				"05/05/2013 06:06:06") {
		});
		minMaxValidator = this.minMaxValidatorCreator.create(generalConfig, indConfig);
		final Response responseError = minMaxValidator.validate(indicator);
		assertEquals(ValidationStatus.ERROR, responseError.getStatus());

	}

}
