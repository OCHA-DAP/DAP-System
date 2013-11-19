package org.ocha.dap.service;

import javax.persistence.NoResultException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.dao.currateddata.EntityDAO;
import org.ocha.dap.persistence.dao.currateddata.EntityTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml", "classpath:/ctx-persistence-test.xml" })
public class CuratedDataServiceImplTest {

	@Autowired
	CuratedDataService curatedDataService;

	@Autowired
	private EntityTypeDAO entityTypeDAO;

	@Autowired
	private EntityDAO entityDAO;

	@Before
	public void setUp() {
		entityTypeDAO.addEntityType("country", "Country");
	}

	@After
	public void tearDown() {
		entityDAO.deleteEntityByCodeAndType("RU", "country");
		entityTypeDAO.deleteEntityTypeByCode("country");
	}

	@Test
	public void testListEntities() {
		Assert.assertEquals(0, curatedDataService.listEntities().size());

		try {
			curatedDataService.addEntity("RU", "Russia", "crisis");
			Assert.fail("entity type crisis does not exist");
		} catch (final NoResultException e) {
			// expected
		}

		curatedDataService.addEntity("RU", "Russia", "country");

		Assert.assertEquals(1, curatedDataService.listEntities().size());
	}

}
