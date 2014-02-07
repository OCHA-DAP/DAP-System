package org.ocha.hdx.persistence.dao.currateddata;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.curateddata.EntityType;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml", "classpath:/ctx-persistence-test.xml" })
public class EntityTypeDAOImplTest {

	private static Logger logger = LoggerFactory.getLogger(EntityTypeDAOImplTest.class);

	@Autowired
	private TextDAO textDAO;

	@Autowired
	private EntityTypeDAO entityTypeDAO;

	@Test
	public void testListEntityTypes() {
		try {
			entityTypeDAO.getEntityTypeByCode("CodeFromUnitTest");
			Assert.fail("Should have raised a NoResultException");
		} catch (final NoResultException e) {
			// expected
		}

		Assert.assertEquals(0, entityTypeDAO.listEntityTypes().size());
		final Text text = textDAO.createText("Country");
		entityTypeDAO.createEntityType("country", text);
		final EntityType entityTypeForCode = entityTypeDAO.getEntityTypeByCode("country");
		Assert.assertEquals("Country", entityTypeForCode.getName().getDefaultValue());
		Assert.assertEquals(1, entityTypeDAO.listEntityTypes().size());

		entityTypeDAO.deleteEntityTypeByCode("country");
		Assert.assertEquals(0, entityTypeDAO.listEntityTypes().size());
	}


	@Test
	public void testCreateEntityType() {
		logger.info("Testing create entityType...");

		final Text country = textDAO.createText("Country");
		entityTypeDAO.createEntityType("country", country);

		final EntityType entityTypeForCode = entityTypeDAO.getEntityTypeByCode("country");
		Assert.assertEquals("Country", entityTypeForCode.getName().getDefaultValue());
		Assert.assertEquals(1, entityTypeDAO.listEntityTypes().size());

		final EntityType entityTypeById = entityTypeDAO.getEntityTypeById(entityTypeForCode.getId());
		Assert.assertEquals("Country", entityTypeById.getName().getDefaultValue());

		logger.info("Testing delete entityType...");
		entityTypeDAO.deleteEntityTypeByCode("country");

		Assert.assertEquals(0, entityTypeDAO.listEntityTypes().size());
	}
	
	@Test
	public void testUpdateEntityType() {
		logger.info("Testing update entityType...");

		final Text country = textDAO.createText("Country");
		entityTypeDAO.createEntityType("country", country);
		final EntityType entityType = entityTypeDAO.getEntityTypeByCode("country");

		entityTypeDAO.updateEntityType(entityType.getId(), "NewName");
		final EntityType updatedEntityType = entityTypeDAO.getEntityTypeById(entityType.getId());

		Assert.assertEquals("NewName", updatedEntityType.getName().getDefaultValue());
		Assert.assertEquals(entityType.getCode(), updatedEntityType.getCode());
		Assert.assertEquals(entityType.getId(), updatedEntityType.getId());

		entityTypeDAO.deleteEntityTypeByCode("country");
	}

	@Test
	public void testDeleteEntityType() {
		logger.info("Testing delete entityType...");

		final Text country = textDAO.createText("Country");
		entityTypeDAO.createEntityType("country", country);

		final EntityType entityTypeForCode = entityTypeDAO.getEntityTypeByCode("country");
		final long id = entityTypeForCode.getId();

		entityTypeDAO.deleteEntityType(id);
		
		Assert.assertNull(entityTypeDAO.getEntityTypeById(id));
	}
}
