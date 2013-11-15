package org.ocha.dap.persistence.dao.currateddata;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml",
		"classpath:/ctx-persistence-test.xml" })
public class EntityTypeDAOImplTest {
	
	@Autowired
	private EntityTypeDAO entityTypeDAO;

	@Test
	public void testListEntityTypes() {
		Assert.assertEquals(0, entityTypeDAO.listEntityTypes().size());
		entityTypeDAO.addEntityType("CodeFromUnitTest", "nameFromUnitTest");
		Assert.assertEquals(1, entityTypeDAO.listEntityTypes().size());
	}

}
