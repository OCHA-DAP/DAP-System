package org.ocha.hdx.persistence.dao.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.currateddata.UnitDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.configs.IndicatorResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.curateddata.Unit;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class ResourceConfigurationDAOImplTest {

	private final String DUMMY_KEY = "dummy_key";

	private final String DUMMY_VALUE = "dummy_value";

	private static final String DUMMY_SOURCE_CODE = "DummySource";

	private static final String DUMMY_INDICATOR_TYPE_CODE = "DummyIndicatorType";

	private static final int NUM_OF_ITEMS = 3;
	private static final String CONFIG_NAME = "Config Name";

	@Autowired
	private ResourceConfigurationDAO resourceConfigurationDAO;

	@Autowired
	private SourceDAO sourceDAO;

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	@Autowired
	private TextDAO textDAO;

	@Autowired
	private UnitDAO unitDAO;

	private Source source;

	private IndicatorType indicatorType;

	private Unit dollar;

	@Before
	public void setUp() throws Exception {
		source = createDummySource();

		final Text dollarText = textDAO.createText("dollar");
		dollar = unitDAO.createUnit("dollar", dollarText);

		indicatorType = createDummyIndicatorType();
	}

	@After
	public void tearDown() throws Exception {

		sourceDAO.deleteSource(source.getId());
		indicatorTypeDAO.deleteIndicatorType(indicatorType.getId());

		unitDAO.deleteUnit(dollar.getId());
	}

	@Test
	public final void testListSources() {

		final List<ResourceConfiguration> initialList = resourceConfigurationDAO.listResourceConfigurations();
		assertEquals(0, initialList.size());

		for (int i = 0; i < NUM_OF_ITEMS; i++) {
			final ResourceConfiguration config = createDummyConfiguration(CONFIG_NAME + i, NUM_OF_ITEMS, NUM_OF_ITEMS, source, indicatorType);

			resourceConfigurationDAO.createResourceConfiguration(config.getName(), config.getGeneralConfigEntries(), config.getIndicatorConfigEntries());
		}

		final List<ResourceConfiguration> modifiedList = resourceConfigurationDAO.listResourceConfigurations();

		assertEquals(NUM_OF_ITEMS, modifiedList.size());

		for (int i = 0; i < modifiedList.size(); i++) {
			final ResourceConfiguration resourceConfiguration = modifiedList.get(i);
			assertEquals(CONFIG_NAME + i, resourceConfiguration.getName());

			assertEquals(NUM_OF_ITEMS, resourceConfiguration.getGeneralConfigEntries().size());
			for (final ResourceConfigEntry entry : resourceConfiguration.getGeneralConfigEntries()) {
				assertTrue(entry.getEntryKey().contains(DUMMY_KEY));
				assertTrue(entry.getEntryValue().contains(DUMMY_VALUE));
			}

			assertEquals(NUM_OF_ITEMS, resourceConfiguration.getIndicatorConfigEntries().size());

			for (final IndicatorResourceConfigEntry entry : resourceConfiguration.getIndicatorConfigEntries()) {
				assertTrue(entry.getEntryKey().contains(DUMMY_KEY));
				assertTrue(entry.getEntryValue().contains(DUMMY_VALUE));

				assertNotNull(entry.getSource());
				assertEquals(source.getId(), entry.getSource().getId());

				assertNotNull(entry.getIndicatorType());
				assertEquals(indicatorType.getId(), entry.getIndicatorType().getId());
			}
		}

		for (final ResourceConfiguration resourceConfiguration : modifiedList) {
			resourceConfigurationDAO.deleteResourceConfiguration(resourceConfiguration.getId());
		}

		assertEquals(0, resourceConfigurationDAO.listResourceConfigurations().size());

	}

	@Test
	public final void testDeleteResourceConfiguration() {
		final ResourceConfiguration config = createDummyConfiguration(CONFIG_NAME, NUM_OF_ITEMS, NUM_OF_ITEMS, source, indicatorType);

		final ResourceConfiguration newConfig = resourceConfigurationDAO.createResourceConfiguration(config.getName(), config.getGeneralConfigEntries(), config.getIndicatorConfigEntries());

		assertTrue(newConfig.getId() > 0);

		final ResourceConfiguration loadedConfig = resourceConfigurationDAO.getResourceConfigurationById(newConfig.getId());
		assertNotNull(loadedConfig);

		final long id = loadedConfig.getId();
		resourceConfigurationDAO.deleteResourceConfiguration(loadedConfig.getId());
		final ResourceConfiguration deletedConfig = resourceConfigurationDAO.getResourceConfigurationById(id);
		assertNull(deletedConfig);
	}

	@Test
	public final void testCreateResourceConfig() {
		final ResourceConfiguration config = createDummyConfiguration(CONFIG_NAME, NUM_OF_ITEMS, NUM_OF_ITEMS, source, indicatorType);

		final ResourceConfiguration newConfig = resourceConfigurationDAO.createResourceConfiguration(config.getName(), config.getGeneralConfigEntries(), config.getIndicatorConfigEntries());

		assertTrue(newConfig.getId() > 0);

		// Testing getResourceConfigurationById()
		final ResourceConfiguration loadedConfig = resourceConfigurationDAO.getResourceConfigurationById(newConfig.getId());
		assertNotNull(loadedConfig);

		final long id = loadedConfig.getId();
		resourceConfigurationDAO.deleteResourceConfiguration(loadedConfig.getId());
		final ResourceConfiguration deletedConfig = resourceConfigurationDAO.getResourceConfigurationById(id);
		assertNull(deletedConfig);
	}

	@Test
	public final void testCreateEmptyResourceConfig() {
		final ResourceConfiguration config = createDummyConfiguration(CONFIG_NAME, NUM_OF_ITEMS, NUM_OF_ITEMS, source, indicatorType);

		final ResourceConfiguration newConfig = resourceConfigurationDAO.createResourceConfiguration(config.getName(), null, null);

		assertTrue(newConfig.getId() > 0);

		// Testing getResourceConfigurationById()
		final ResourceConfiguration loadedConfig = resourceConfigurationDAO.getResourceConfigurationById(newConfig.getId());
		assertNotNull(loadedConfig);

		final long id = loadedConfig.getId();
		resourceConfigurationDAO.deleteResourceConfiguration(loadedConfig.getId());
		final ResourceConfiguration deletedConfig = resourceConfigurationDAO.getResourceConfigurationById(id);
		assertNull(deletedConfig);
	}

	@Test
	public final void testUpdateResourceConfiguration() {
		final ResourceConfiguration config = createDummyConfiguration(CONFIG_NAME, NUM_OF_ITEMS, NUM_OF_ITEMS, source, indicatorType);

		final ResourceConfiguration newConfig = resourceConfigurationDAO.createResourceConfiguration(config.getName(), config.getGeneralConfigEntries(), config.getIndicatorConfigEntries());

		final long id = newConfig.getId();

		final ResourceConfiguration configModified = createDummyConfiguration(CONFIG_NAME + "modified", NUM_OF_ITEMS + 2, NUM_OF_ITEMS + 2, source, indicatorType);
		resourceConfigurationDAO.updateResourceConfiguration(id, configModified.getName(), configModified.getGeneralConfigEntries(), configModified.getIndicatorConfigEntries());

		final ResourceConfiguration loadedConfig = resourceConfigurationDAO.getResourceConfigurationById(id);

		assertEquals(CONFIG_NAME + "modified", loadedConfig.getName());
		assertEquals(NUM_OF_ITEMS + 2, loadedConfig.getGeneralConfigEntries().size());
		assertEquals(NUM_OF_ITEMS + 2, loadedConfig.getIndicatorConfigEntries().size());

		resourceConfigurationDAO.deleteResourceConfiguration(loadedConfig.getId());

	}

	private final ResourceConfiguration createDummyConfiguration(final String name, final int numOfResourcesConfig, final int numOfIndResourceConfig, final Source dummySource,
			final IndicatorType dummyIndicatorType) {

		final ResourceConfiguration resourceConfiguration = new ResourceConfiguration();
		resourceConfiguration.setName(name);

		final Set<ResourceConfigEntry> resourceConfigEntries = new HashSet<ResourceConfigEntry>();
		for (int i = 0; i < numOfResourcesConfig; i++) {
			final ResourceConfigEntry entry = new ResourceConfigEntry(DUMMY_KEY + i, DUMMY_VALUE + i);
			resourceConfigEntries.add(entry);
		}
		if (resourceConfigEntries.size() > 0) {
			resourceConfiguration.setGeneralConfigEntries(resourceConfigEntries);
		}

		final Set<IndicatorResourceConfigEntry> indicatorResourceConfigEntries = new HashSet<IndicatorResourceConfigEntry>();
		for (int i = 0; i < numOfIndResourceConfig; i++) {
			final IndicatorResourceConfigEntry entry = new IndicatorResourceConfigEntry(DUMMY_KEY + i, DUMMY_VALUE + i, dummySource, dummyIndicatorType);
			indicatorResourceConfigEntries.add(entry);
		}

		if (indicatorResourceConfigEntries.size() > 0) {
			resourceConfiguration.setIndicatorConfigEntries(indicatorResourceConfigEntries);
		}

		return resourceConfiguration;
	}

	private final Source createDummySource() {
		final Text text = textDAO.createText("Dummy Source");
		sourceDAO.createSource(DUMMY_SOURCE_CODE, text, "www.dummy-source.com", null);

		return sourceDAO.getSourceByCode(DUMMY_SOURCE_CODE);

	}

	private final IndicatorType createDummyIndicatorType() {
		final Text textForTest = textDAO.createText("Dummy Indicator Type");

		indicatorTypeDAO.createIndicatorType(DUMMY_INDICATOR_TYPE_CODE, textForTest, dollar, ValueType.NUMBER);

		return indicatorTypeDAO.getIndicatorTypeByCode(DUMMY_INDICATOR_TYPE_CODE);

	}

}
