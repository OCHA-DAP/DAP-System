package org.ocha.dap.persistence.dao.currateddata;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class EntityTypeDAOImplTest {

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
		entityTypeDAO.addEntityType("country", "Country");
		final EntityType entityTypeForCode = entityTypeDAO.getEntityTypeByCode("country");
		Assert.assertEquals("Country", entityTypeForCode.getName());
		Assert.assertEquals(1, entityTypeDAO.listEntityTypes().size());

		entityTypeDAO.deleteEntityTypeByCode("country");
		Assert.assertEquals(0, entityTypeDAO.listEntityTypes().size());
	}

}
