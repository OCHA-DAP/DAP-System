package org.ocha.dap.persistence.dao.dictionary;

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

	@Before
	public void setUp() {
		//set up the EntityTypes
		entityTypeDAO.addEntityType("country", "Country");
		entityTypeDAO.addEntityType("crisis", "Crisis");
		List<EntityType> entityTypes = entityTypeDAO.listEntityTypes();
		EntityType country = entityTypeDAO.getEntityTypeByCode("country");  //could we (should we) make the addEntityType method return the created entity?
		EntityType crisis = entityTypeDAO.getEntityTypeByCode("crisis");
		System.out.println(country.getCode());
		System.out.println(crisis.getCode());
		
		//set up the Entities
		entityDAO.addEntity("Foolandia","FOO", country);
		System.out.println("Entity added.");
		Entity foolandia = entityDAO.getEntityByCodeAndType("FOO", country.getCode());
		System.out.println(foolandia.getCode());
	}

	@After
	public void tearDown() {
		//TODO Delete all regionDictionaries  like: sourceDictionaryDAO.deleteAllSourceDictionaries();
		entityTypeDAO.deleteEntityTypeByCode("country");
		entityTypeDAO.deleteEntityTypeByCode("crisis");
		
		
		
	}

	@Test
	public void testListSourceDictionaries() {
		Assert.assertEquals(0, sourceDictionaryDAO.listSourceDictionaries().size());

		final Source sourceWB = sourceDAO.getSourceByCode("WB");

		sourceDictionaryDAO.addSourceDictionary("World Bank", "scraper", sourceWB);
		sourceDictionaryDAO.addSourceDictionary("World B.", "another", sourceWB);

		Assert.assertEquals(2, sourceDictionaryDAO.listSourceDictionaries().size());
		Assert.assertEquals(1, sourceDictionaryDAO.getSourceDictionariesByImporter("scraper").size());
	}

}
