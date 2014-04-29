package org.ocha.hdx.persistence.dao.dictionary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.persistence.dao.config.ResourceConfigurationDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.EntityType;
import org.ocha.hdx.persistence.entity.dictionary.RegionDictionary;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.ocha.hdx.service.CuratedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml", "classpath:/ctx-persistence-test.xml" })
public class RegionDictionaryDAOImplTest {

	@Autowired
	private RegionDictionaryDAO regionDictionaryDAO;

	@Autowired
	private EntityTypeDAO entityTypeDAO;

	@Autowired
	private EntityDAO entityDAO;

	@Autowired
	private TextDAO textDAO;

	@Autowired
	private CuratedDataService curatedDataService;

	@Autowired
	private ResourceConfigurationDAO resourceConfigurationDAO;

	private EntityType country;
	private EntityType crisis;

	@Before
	public void setUp() {
		// set up the EntityTypes
		final Text countryText = textDAO.createText("Country");
		final Text crisisText = textDAO.createText("Crisis");
		entityTypeDAO.createEntityType("country", countryText);
		entityTypeDAO.createEntityType("crisis", crisisText);
		country = entityTypeDAO.getEntityTypeByCode("country"); // TODO could we (should we) make the addEntityType method return the
		// created entity?
		crisis = entityTypeDAO.getEntityTypeByCode("crisis");

		// set up the Entities
		curatedDataService.createEntity("FOO", "Foolandia", "country");
		curatedDataService.createEntity("BAR", "Barlandia", "country");
		curatedDataService.createEntity("Crisis1", "First Test Crisis", "crisis");
		curatedDataService.createEntity("Crisis2", "Second Test Crisis", "crisis");

	}

	@After
	public void tearDown() {

		for (final RegionDictionary rd : regionDictionaryDAO.listRegionDictionaries()) {
			regionDictionaryDAO.deleteRegionDictionary(rd);
		}

		for (final Entity entity : entityDAO.listEntities()) {
			entityDAO.deleteEntityByCodeAndType(entity.getCode(), entity.getType().getCode());
		}

		entityTypeDAO.deleteEntityTypeByCode("country");
		entityTypeDAO.deleteEntityTypeByCode("crisis");

	}

	@Test
	public void testRegionDictionaryDAO() {
		// Get the Entities
		final Entity foolandia = entityDAO.getEntityByCodeAndType("FOO", country.getCode());
		final Entity barlandia = entityDAO.getEntityByCodeAndType("BAR", country.getCode());
		final Entity crisis1 = entityDAO.getEntityByCodeAndType("Crisis1", crisis.getCode());
		final Entity crisis2 = entityDAO.getEntityByCodeAndType("Crisis2", crisis.getCode()); // The entity codes are case sensitive

		final ResourceConfiguration configuration = resourceConfigurationDAO.createResourceConfiguration("Test Config", null, null);

		// Add some RegionDictionaries
		regionDictionaryDAO.createRegionDictionary(configuration, foolandia, "Fooléndia");
		regionDictionaryDAO.createRegionDictionary(configuration, barlandia, "Fooléndia"); // in a different importer, the same string could mean
																							// something different
		regionDictionaryDAO.createRegionDictionary(configuration, crisis1, "Cr1");
		regionDictionaryDAO.createRegionDictionary(configuration, crisis2, "Cr2");

		// TODO Try adding the same RegionDictionary again (currently fails with org.springframework.dao.DataIntegrityViolationException)
		// regionDictionaryDAO.addRegionDictionary("Fooléndia", "Test", foolandia);

		List<RegionDictionary> regionDictionaryList = regionDictionaryDAO.listRegionDictionaries();
		assertEquals("region_dictionary table should have 4 entries", 4, regionDictionaryList.size());

		assertNotNull(regionDictionaryList.get(0).getUnnormalizedName()); // TODO What else could we do with the ID?

		// add another entity from the ones constructed above
		regionDictionaryDAO.createRegionDictionary(configuration, crisis1, "Another unnormalized name");
		regionDictionaryList = regionDictionaryDAO.listRegionDictionaries();
		assertEquals("region_dictionary table should now have 5 entries", 5, regionDictionaryList.size());

		// delete a RegionDictionary by object
		final RegionDictionary regionDictionaryToDelete = regionDictionaryList.get(0);
		regionDictionaryDAO.deleteRegionDictionary(regionDictionaryToDelete);
		regionDictionaryList = regionDictionaryDAO.listRegionDictionaries();
		assertEquals("After deletion, there should be 4 RegionDictionaries in the table.", 4, regionDictionaryList.size());
		// TODO Should try to get the deleted RD and make sure it fails.
	}

}
