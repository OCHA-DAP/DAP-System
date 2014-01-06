package org.ocha.dap.persistence.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.entity.User;
import org.ocha.dap.security.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml",  "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class UserDAOImplTest {
	
	@Autowired
	private UserDAO userDAO;

	@Test
	public void testCreateUser() throws Exception {
		Assert.assertEquals(0, userDAO.listUsers().size());
		
		userDAO.createUser("seustachi", "dummyPwd", "admin", "079f6194-45e1-4534-8ca7-1bd4130ef897");
		
		final List<User> users = userDAO.listUsers();
		Assert.assertEquals(1, users.size());
		
		userDAO.authenticate("seustachi", "dummyPwd");
		
		try {
			userDAO.authenticate("seustachi", "wrong");
			Assert.fail("Should have raised an AuthenticationException");
		} catch (final AuthenticationException e) {
		}
		
		try {
			userDAO.authenticate("cj", "dummyPwd");
			Assert.fail("Should have raised an AuthenticationException");
		} catch (final AuthenticationException e) {
		}
		
		final String userApiKey = userDAO.getUserApiKey("seustachi");
		Assert.assertEquals("079f6194-45e1-4534-8ca7-1bd4130ef897", userApiKey);
		
		userDAO.deleteUser("seustachi");
	}

}
