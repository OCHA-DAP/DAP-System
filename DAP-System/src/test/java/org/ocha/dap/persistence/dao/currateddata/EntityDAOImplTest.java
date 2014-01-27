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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class EntityDAOImplTest {

	private static Logger logger = LoggerFactory.getLogger(EntityDAOImplTest.class);

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
		logger.info("Testing list entities...");
		Assert.assertEquals(0, entityDAO.listEntities().size());

		try {
			logger.info("Testing add entity...");
			entityDAO.createEntity("RU", new Text("Russia"), new EntityType());
			Assert.fail("Should have raised an Exception, cannot create an entity with an invalid type");
		} catch (final Exception e) {
			// expected
		}

		final EntityType country = entityTypeDAO.getEntityTypeByCode("country");

		final Text russia = textDAO.createText("Russia");
		entityDAO.createEntity("RU", russia, country);

		final Entity entityForCode = entityDAO.getEntityByCodeAndType("RU", "country");
		Assert.assertEquals("Russia", entityForCode.getName().getDefaultValue());
		Assert.assertEquals(1, entityDAO.listEntities().size());

		final Entity entityById = entityDAO.getEntityById(entityForCode.getId());
		Assert.assertEquals("Russia", entityById.getName().getDefaultValue());

		logger.info("Testing delete entity...");
		entityDAO.deleteEntityByCodeAndType("RU", "country");

		Assert.assertEquals(0, entityDAO.listEntities().size());

	}

	@Test
	public void testCreateEntity() {
		logger.info("Testing create entity...");

		try {
			entityDAO.createEntity("RU", new Text("Russia"), new EntityType());
			Assert.fail("Should have raised an Exception, cannot create an entity with an invalid type");
		} catch (final Exception e) {
			// expected
		}

		final EntityType country = entityTypeDAO.getEntityTypeByCode("country");

		final Text russia = textDAO.createText("Russia");
		entityDAO.createEntity("RU", russia, country);

		final Entity entityForCode = entityDAO.getEntityByCodeAndType("RU", "country");
		Assert.assertEquals("Russia", entityForCode.getName().getDefaultValue());
		Assert.assertEquals(1, entityDAO.listEntities().size());

		final Entity entityById = entityDAO.getEntityById(entityForCode.getId());
		Assert.assertEquals("Russia", entityById.getName().getDefaultValue());

		logger.info("Testing delete entity...");
		entityDAO.deleteEntityByCodeAndType("RU", "country");

		Assert.assertEquals(0, entityDAO.listEntities().size());
	}
	@Test
	public void testUpdateEntity() {
		logger.info("Testing update entity...");

		final EntityType country = entityTypeDAO.getEntityTypeByCode("country");

		final Text russia = textDAO.createText("Russia");
		entityDAO.createEntity("RU", russia, country);
		final Entity entity = entityDAO.getEntityByCodeAndType("RU", "country");

		entityDAO.updateEntity(entity.getId(), "NewName");
		final Entity updatedEntity = entityDAO.getEntityById(entity.getId());

		Assert.assertEquals("NewName", updatedEntity.getName().getDefaultValue());
		Assert.assertEquals(entity.getCode(), updatedEntity.getCode());
		Assert.assertEquals(entity.getId(), updatedEntity.getId());
		Assert.assertEquals(entity.getType().getId(), updatedEntity.getType().getId());
		Assert.assertEquals(entity.getType().getCode(), updatedEntity.getType().getCode());
		Assert.assertEquals(entity.getType().getName(), updatedEntity.getType().getName());

		entityDAO.deleteEntityByCodeAndType("RU", "country");
	}

	@Test
	public void testDeleteEntity() {
		logger.info("Testing delete entity...");

		final EntityType country = entityTypeDAO.getEntityTypeByCode("country");
		final Text russia = textDAO.createText("Russia");
		entityDAO.createEntity("RU", russia, country);

		final Entity entityForCode = entityDAO.getEntityByCodeAndType("RU", "country");
		final long id = entityForCode.getId();

		entityDAO.deleteEntity(id);
		
		Assert.assertNull(entityDAO.getEntityById(id));
	}
}
