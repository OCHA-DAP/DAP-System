package org.ocha.dap.persistence.dao.currateddata;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class EntityDAOImplTest {

	@Autowired
	private EntityDAO entityDAO;

	@Autowired
	private EntityTypeDAO entityTypeDAO;

	@Before
	public void setUp() {
		entityTypeDAO.addEntityType("country", "Country");
	}

	@After
	public void tearDown() {
		entityTypeDAO.deleteEntityTypeByCode("country");
	}

	@Test
	public void testListEntities() {
		Assert.assertEquals(0, entityDAO.listEntities().size());

		try {
			entityDAO.addEntity("RU", "Russia", new EntityType());
			Assert.fail("Should have raised an Exception, cannot create an entity with an invalid type");
		} catch (final Exception e) {
			// expected
		}

		final EntityType country = entityTypeDAO.getEntityTypeByCode("country");

		entityDAO.addEntity("RU", "Russia", country);
		final Entity entityForCode = entityDAO.getEntityByCodeAndType("RU", "country");
		Assert.assertEquals("Russia", entityForCode.getName());
		Assert.assertEquals(1, entityDAO.listEntities().size());

		final Entity entityById = entityDAO.getEntityById(entityForCode.getId());
		Assert.assertEquals("Russia", entityById.getName());

		entityDAO.deleteEntityByCodeAndType("RU", "country");

		Assert.assertEquals(0, entityDAO.listEntities().size());

	}

}
