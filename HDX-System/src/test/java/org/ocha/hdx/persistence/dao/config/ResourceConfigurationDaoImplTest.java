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
public class ResourceConfigurationDaoImplTest {

	private final String DUMMY_KEY = "dummy_key";

	private final String DUMMY_VALUE = "dummy_value";

	private static final String DUMMY_SOURCE_CODE = "DummySource";

	private static final String DUMMY_INDICATOR_TYPE_CODE = "DummyIndicatorType";

	private static final int NUM_OF_ITEMS = 3;
	private static final String CONFIG_NAME = "Config Name";

	@Autowired
	private ResourceConfigurationDao resourceConfigurationDao;

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
		this.source = this.createDummySource();

		final Text dollarText = this.textDAO.createText("dollar");
		this.dollar = this.unitDAO.createUnit("dollar", dollarText);

		this.indicatorType = this.createDummyIndicatorType();
	}

	@After
	public void tearDown() throws Exception {

		this.sourceDAO.deleteSource(this.source.getId());
		this.indicatorTypeDAO.deleteIndicatorType(this.indicatorType.getId());

		this.unitDAO.deleteUnit(this.dollar.getId());
	}

	@Test
	public final void testListSources() {

		final List<ResourceConfiguration> initialList = this.resourceConfigurationDao.listResourceConfigurations();
		assertEquals(0, initialList.size());

		for (int i = 0; i < NUM_OF_ITEMS; i++) {
			final ResourceConfiguration config = this.createDummyConfiguration(CONFIG_NAME + i, NUM_OF_ITEMS, NUM_OF_ITEMS, this.source, this.indicatorType);

			this.resourceConfigurationDao.createResourceConfig(config.getName(), config.getGeneralConfigEntries(), config.getIndicatorConfigEntries());
		}

		final List<ResourceConfiguration> modifiedList = this.resourceConfigurationDao.listResourceConfigurations();

		assertEquals(NUM_OF_ITEMS, modifiedList.size());

		for (int i = 0; i < modifiedList.size(); i++) {
			final ResourceConfiguration resourceConfiguration = modifiedList.get(i);
			assertEquals(CONFIG_NAME + i, resourceConfiguration.getName());

			assertEquals(NUM_OF_ITEMS, resourceConfiguration.getGeneralConfigEntries().size());
			for (final ResourceConfigEntry entry : resourceConfiguration.getGeneralConfigEntries()) {
				assertTrue(entry.getEntryKey().contains(this.DUMMY_KEY));
				assertTrue(entry.getEntryValue().contains(this.DUMMY_VALUE));
			}

			assertEquals(NUM_OF_ITEMS, resourceConfiguration.getIndicatorConfigEntries().size());

			for (final IndicatorResourceConfigEntry entry : resourceConfiguration.getIndicatorConfigEntries()) {
				assertTrue(entry.getEntryKey().contains(this.DUMMY_KEY));
				assertTrue(entry.getEntryValue().contains(this.DUMMY_VALUE));

				assertNotNull(entry.getSource());
				assertEquals(this.source.getId(), entry.getSource().getId());

				assertNotNull(entry.getIndicatorType());
				assertEquals(this.indicatorType.getId(), entry.getIndicatorType().getId());
			}
		}

		for (final ResourceConfiguration resourceConfiguration : modifiedList) {
			this.resourceConfigurationDao.deleteResourceConfiguration(resourceConfiguration.getId());
		}

		assertEquals(0, this.resourceConfigurationDao.listResourceConfigurations().size());

	}

	@Test
	public final void testDeleteResourceConfiguration() {
		final ResourceConfiguration config = this.createDummyConfiguration(CONFIG_NAME, NUM_OF_ITEMS, NUM_OF_ITEMS, this.source, this.indicatorType);

		final ResourceConfiguration newConfig = this.resourceConfigurationDao.createResourceConfig(config.getName(), config.getGeneralConfigEntries(), config.getIndicatorConfigEntries());

		assertTrue(newConfig.getId() > 0);

		final ResourceConfiguration loadedConfig = this.resourceConfigurationDao.getResourceConfigurationById(newConfig.getId());
		assertNotNull(loadedConfig);

		final long id = loadedConfig.getId();
		this.resourceConfigurationDao.deleteResourceConfiguration(loadedConfig.getId());
		final ResourceConfiguration deletedConfig = this.resourceConfigurationDao.getResourceConfigurationById(id);
		assertNull(deletedConfig);
	}

	@Test
	public final void testCreateResourceConfig() {
		final ResourceConfiguration config = this.createDummyConfiguration(CONFIG_NAME, NUM_OF_ITEMS, NUM_OF_ITEMS, this.source, this.indicatorType);

		final ResourceConfiguration newConfig = this.resourceConfigurationDao.createResourceConfig(config.getName(), config.getGeneralConfigEntries(), config.getIndicatorConfigEntries());

		assertTrue(newConfig.getId() > 0);

		// Testing getResourceConfigurationById()
		final ResourceConfiguration loadedConfig = this.resourceConfigurationDao.getResourceConfigurationById(newConfig.getId());
		assertNotNull(loadedConfig);

		final long id = loadedConfig.getId();
		this.resourceConfigurationDao.deleteResourceConfiguration(loadedConfig.getId());
		final ResourceConfiguration deletedConfig = this.resourceConfigurationDao.getResourceConfigurationById(id);
		assertNull(deletedConfig);
	}

	@Test
	public final void testCreateEmptyResourceConfig() {
		final ResourceConfiguration config = this.createDummyConfiguration(CONFIG_NAME, NUM_OF_ITEMS, NUM_OF_ITEMS, this.source, this.indicatorType);

		final ResourceConfiguration newConfig = this.resourceConfigurationDao.createResourceConfig(config.getName(), null, null);

		assertTrue(newConfig.getId() > 0);

		// Testing getResourceConfigurationById()
		final ResourceConfiguration loadedConfig = this.resourceConfigurationDao.getResourceConfigurationById(newConfig.getId());
		assertNotNull(loadedConfig);

		final long id = loadedConfig.getId();
		this.resourceConfigurationDao.deleteResourceConfiguration(loadedConfig.getId());
		final ResourceConfiguration deletedConfig = this.resourceConfigurationDao.getResourceConfigurationById(id);
		assertNull(deletedConfig);
	}

	@Test
	public final void testUpdateResourceConfiguration() {
		final ResourceConfiguration config = this.createDummyConfiguration(CONFIG_NAME, NUM_OF_ITEMS, NUM_OF_ITEMS, this.source, this.indicatorType);

		final ResourceConfiguration newConfig = this.resourceConfigurationDao.createResourceConfig(config.getName(), config.getGeneralConfigEntries(), config.getIndicatorConfigEntries());

		final long id = newConfig.getId();

		final ResourceConfiguration configModified = this.createDummyConfiguration(CONFIG_NAME + "modified", NUM_OF_ITEMS + 2, NUM_OF_ITEMS + 2, this.source, this.indicatorType);
		this.resourceConfigurationDao.updateResourceConfiguration(id, configModified.getName(), configModified.getGeneralConfigEntries(), configModified.getIndicatorConfigEntries());

		final ResourceConfiguration loadedConfig = this.resourceConfigurationDao.getResourceConfigurationById(id);

		assertEquals(CONFIG_NAME + "modified", loadedConfig.getName());
		assertEquals(NUM_OF_ITEMS + 2, loadedConfig.getGeneralConfigEntries().size());
		assertEquals(NUM_OF_ITEMS + 2, loadedConfig.getIndicatorConfigEntries().size());

		this.resourceConfigurationDao.deleteResourceConfiguration(loadedConfig.getId());

	}

	private final ResourceConfiguration createDummyConfiguration(final String name, final int numOfResourcesConfig, final int numOfIndResourceConfig, final Source dummySource,
			final IndicatorType dummyIndicatorType) {

		final ResourceConfiguration resourceConfiguration = new ResourceConfiguration();
		resourceConfiguration.setName(name);

		final Set<ResourceConfigEntry> resourceConfigEntries = new HashSet<ResourceConfigEntry>();
		for (int i = 0; i < numOfResourcesConfig; i++) {
			final ResourceConfigEntry entry = new ResourceConfigEntry(this.DUMMY_KEY + i, this.DUMMY_VALUE + i);
			resourceConfigEntries.add(entry);
		}
		if (resourceConfigEntries.size() > 0) {
			resourceConfiguration.setGeneralConfigEntries(resourceConfigEntries);
		}

		final Set<IndicatorResourceConfigEntry> indicatorResourceConfigEntries = new HashSet<IndicatorResourceConfigEntry>();
		for (int i = 0; i < numOfIndResourceConfig; i++) {
			final IndicatorResourceConfigEntry entry = new IndicatorResourceConfigEntry(this.DUMMY_KEY + i, this.DUMMY_VALUE + i, dummySource, dummyIndicatorType);
			indicatorResourceConfigEntries.add(entry);
		}

		if (indicatorResourceConfigEntries.size() > 0) {
			resourceConfiguration.setIndicatorConfigEntries(indicatorResourceConfigEntries);
		}

		return resourceConfiguration;
	}

	private final Source createDummySource() {
		final Text text = this.textDAO.createText("Dummy Source");
		this.sourceDAO.createSource(DUMMY_SOURCE_CODE, text, "www.dummy-source.com");

		return this.sourceDAO.getSourceByCode(DUMMY_SOURCE_CODE);

	}

	private final IndicatorType createDummyIndicatorType() {
		final Text textForTest = this.textDAO.createText("Dummy Indicator Type");

		this.indicatorTypeDAO.createIndicatorType(DUMMY_INDICATOR_TYPE_CODE, textForTest, this.dollar, ValueType.NUMBER);

		return this.indicatorTypeDAO.getIndicatorTypeByCode(DUMMY_INDICATOR_TYPE_CODE);

	}

}
