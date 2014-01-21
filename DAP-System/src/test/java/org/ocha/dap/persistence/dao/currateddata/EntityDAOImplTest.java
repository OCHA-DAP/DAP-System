package org.ocha.dap.persistence.dao.currateddata;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.dao.i18n.TextDAO;
import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.ocha.dap.persistence.entity.i18n.Text;
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

	@Autowired
	private TextDAO textDAO;

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
		System.out.println("Testing list entities...");
		Assert.assertEquals(0, entityDAO.listEntities().size());

		try {
			System.out.println("Testing add entity...");
			entityDAO.createEntity("RU", new Text("Russia"), new EntityType());
			Assert.fail("Should have raised an Exception, cannot create an entity with an invalid type");
		} catch (final Exception e) {
			// expected
		}

		final EntityType country = entityTypeDAO.getEntityTypeByCode("country");

		final Text russia = textDAO.addText("Russia");
		entityDAO.createEntity("RU", russia, country);

		final Entity entityForCode = entityDAO.getEntityByCodeAndType("RU", "country");
		Assert.assertEquals("Russia", entityForCode.getName().getDefaultValue());
		Assert.assertEquals(1, entityDAO.listEntities().size());

		final Entity entityById = entityDAO.getEntityById(entityForCode.getId());
		Assert.assertEquals("Russia", entityById.getName().getDefaultValue());



		System.out.println("Testing delete entity...");
		entityDAO.deleteEntityByCodeAndType("RU", "country");

		Assert.assertEquals(0, entityDAO.listEntities().size());

	}


	@Test
	public void testUpdateEntity() {
		System.out.println("Testing update entity...");

		final EntityType country = entityTypeDAO.getEntityTypeByCode("country");

		final Text russia = textDAO.addText("Russia");
		entityDAO.createEntity("RU", russia, country);
		final Entity entity = entityDAO.getEntityByCodeAndType("RU", "country");

		entityDAO.updateEntity(entity.getId(), "NewName");
		final Entity updatedEntity = entityDAO.getEntityById(entity.getId());

		Assert.assertEquals("NewName", updatedEntity.getName());
		Assert.assertEquals(entity.getCode(), updatedEntity.getCode());
		Assert.assertEquals(entity.getId(), updatedEntity.getId());
		Assert.assertEquals(entity.getType().getId(), updatedEntity.getType().getId());
		Assert.assertEquals(entity.getType().getCode(), updatedEntity.getType().getCode());
		Assert.assertEquals(entity.getType().getName(), updatedEntity.getType().getName());

		entityDAO.deleteEntityByCodeAndType("RU", "country");
	}
}
