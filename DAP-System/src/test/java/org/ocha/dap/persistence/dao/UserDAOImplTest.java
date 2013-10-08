package org.ocha.dap.persistence.dao;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml",  "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class UserDAOImplTest {
	
	@Autowired
	private UserDAO userDAO;

	@Test
	public void testCreateUser() {
		Assert.assertEquals(0, userDAO.listUsers().size());
		
		fail("Not yet implemented");
	}

}
