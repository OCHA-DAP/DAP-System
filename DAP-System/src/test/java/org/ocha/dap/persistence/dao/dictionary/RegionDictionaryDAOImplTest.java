package org.ocha.dap.persistence.dao.dictionary;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.dao.currateddata.EntityDAO;
import org.ocha.dap.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.ocha.dap.persistence.entity.curateddata.Source;
import org.ocha.dap.persistence.entity.dictionary.RegionDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class RegionDictionaryDAOImplTest {

	@Autowired
	private RegionDictionaryDAO regionDictionaryDAO;
	
	@Autowired
	private EntityTypeDAO entityTypeDAO;

	@Autowired
	private EntityDAO entityDAO;
	
	private EntityType country;
	private EntityType crisis;

	@Before
	public void setUp() {
		//set up the EntityTypes
		entityTypeDAO.addEntityType("country", "Country");
		entityTypeDAO.addEntityType("crisis", "Crisis");
		country = entityTypeDAO.getEntityTypeByCode("country");  //TODO could we (should we) make the addEntityType method return the created entity?
		crisis = entityTypeDAO.getEntityTypeByCode("crisis");
				
		//set up the Entities
		entityDAO.addEntity("FOO","Foolandia", country);
		entityDAO.addEntity("BAR","Barlandia", country);
		entityDAO.addEntity("Crisis1","First Test Crisis", crisis);
		entityDAO.addEntity("Crisis2","Second Test Crisis", crisis);
				
	}

	@After
	public void tearDown() {
				
		for (RegionDictionary rd : regionDictionaryDAO.listRegionDictionaries()){
			regionDictionaryDAO.deleteRegionDictionary(rd);
		}
		
		for (Entity entity : entityDAO.listEntities()){
			entityDAO.deleteEntityByCodeAndType(entity.getCode(), entity.getType().getCode());
		}
		
		entityTypeDAO.deleteEntityTypeByCode("country");
		entityTypeDAO.deleteEntityTypeByCode("crisis");
		
	}
	
	@Test
	public void testRegionDictionaryDAO() {
		//Get the Entities
		final Entity foolandia = entityDAO.getEntityByCodeAndType("FOO", country.getCode());
		final Entity barlandia = entityDAO.getEntityByCodeAndType("BAR", country.getCode());
		final Entity crisis1 = entityDAO.getEntityByCodeAndType("Crisis1", crisis.getCode());
		final Entity crisis2 = entityDAO.getEntityByCodeAndType("Crisis2", crisis.getCode());  //The entity codes are case sensitive
		
		//Add some RegionDictionaries
		regionDictionaryDAO.addRegionDictionary("Fooléndia", "Importer1", foolandia);
		regionDictionaryDAO.addRegionDictionary("Fooléndia", "Importer2", barlandia); //in a different importer, the same string could mean something different
		regionDictionaryDAO.addRegionDictionary("Cr1", "Importer1", crisis1);
		regionDictionaryDAO.addRegionDictionary("Cr2", "Importer1", crisis2);
		
		
		//TODO Try adding the same RegionDictionary again (currently fails with org.springframework.dao.DataIntegrityViolationException)
		//regionDictionaryDAO.addRegionDictionary("Fooléndia", "Test", foolandia);
		
		List<RegionDictionary> regionDictionaryList = regionDictionaryDAO.listRegionDictionaries();
		assertEquals("region_dictionary table should have 4 entries", 4, regionDictionaryList.size());
		
		assertNotNull(regionDictionaryList.get(0).getId()); //TODO What else could we do with the ID?
		
		//create more RegionDictionaries with the other constructors
		final RegionDictionary emptyRD = new RegionDictionary();
		final RegionDictionary noEntityRD = new RegionDictionary("Another unnormalized name","Another Importer");
		
		//set Entity for a RD, and test it
		noEntityRD.setEntity(crisis1);
		assertEquals("Entity's code should be Crisis1","Crisis1", noEntityRD.getEntity().getCode());
		
		//add another entity from the ones constructed above
		regionDictionaryDAO.addRegionDictionary(noEntityRD.getId().getUnnormalizedName(), 
				noEntityRD.getId().getImporter(), noEntityRD.getEntity());
		regionDictionaryList = regionDictionaryDAO.listRegionDictionaries();
		assertEquals("region_dictionary table should now have 5 entries", 5, regionDictionaryList.size());
		
		//delete a RegionDictionary by object
		RegionDictionary regionDictionaryToDelete = regionDictionaryList.get(0);
		regionDictionaryDAO.deleteRegionDictionary(regionDictionaryToDelete);
		regionDictionaryList = regionDictionaryDAO.listRegionDictionaries();
		assertEquals("After deletion, there should be 4 RegionDictionaries in the table.",4, regionDictionaryList.size());
		//add this back to avoid breaking the next test
		regionDictionaryDAO.addRegionDictionary(regionDictionaryToDelete.getId().getUnnormalizedName(), 
				regionDictionaryToDelete.getId().getImporter(), regionDictionaryToDelete.getEntity());
		regionDictionaryList = regionDictionaryDAO.listRegionDictionaries();
		assertEquals("region_dictionary table should now have 5 entries", 5, regionDictionaryList.size());
		
		//delete a specific RegionDictionary by unique fields
		final String unnormalizedName = regionDictionaryToDelete.getId().getUnnormalizedName();
		final String importer = regionDictionaryToDelete.getId().getImporter();
		regionDictionaryDAO.deleteRegionDictionary(unnormalizedName, importer);
		regionDictionaryList = regionDictionaryDAO.listRegionDictionaries();
		assertEquals("After deletion, there should be 4 RegionDictionaries in the table.", 4, regionDictionaryList.size());
		//TODO Should try to get the deleted RD and make sure it fails.
	}


}
