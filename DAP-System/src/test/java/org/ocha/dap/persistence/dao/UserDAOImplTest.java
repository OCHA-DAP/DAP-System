package org.ocha.dap.persistence.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.entity.User;
import org.ocha.dap.security.exception.AuthenticationException;
import org.ocha.dap.security.tools.AESCipher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class UserDAOImplTest {

	private static Logger logger = LoggerFactory.getLogger(UserDAOImplTest.class);

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AESCipher aesCipher;

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

	@Test
	public void testListUsers() throws Exception {
		logger.info("Testing list users...");

		List<User> listUsers = userDAO.listUsers();
		Assert.assertEquals(0, listUsers.size());

		userDAO.createUser("fakeId1", "fakePassword1", "fakeRole1", "fakeAPIKey1");
		userDAO.createUser("fakeId2", "fakePassword2", "fakeRole2", "fakeAPIKey2");

		listUsers = userDAO.listUsers();
		Assert.assertEquals(2, listUsers.size());

		userDAO.deleteUser("fakeId1");
		userDAO.deleteUser("fakeId2");

		listUsers = userDAO.listUsers();
		Assert.assertEquals(0, listUsers.size());
	}

	@Test
	public void testListRoles() throws Exception {
		logger.info("Testing list roles...");

		List<String> listRoles = userDAO.listRoles();
		Assert.assertEquals(0, listRoles.size());

		userDAO.createUser("fakeId1", "fakePassword1", "fakeRole1", "fakeAPIKey1");
		userDAO.createUser("fakeId2", "fakePassword2", "fakeRole2", "fakeAPIKey2");
		userDAO.createUser("fakeId3", "fakePassword3", "fakeRole2", "fakeAPIKey3");

		listRoles = userDAO.listRoles();
		Assert.assertEquals(2, listRoles.size());

		userDAO.deleteUser("fakeId1");

		listRoles = userDAO.listRoles();
		Assert.assertEquals(1, listRoles.size());

		userDAO.deleteUser("fakeId2");

		listRoles = userDAO.listRoles();
		Assert.assertEquals(1, listRoles.size());

		userDAO.deleteUser("fakeId3");

		listRoles = userDAO.listRoles();
		Assert.assertEquals(0, listRoles.size());
	}

	@Test
	public void testUpdateUser() throws Exception {
		logger.info("Testing update user...");

		userDAO.createUser("fakeId", "fakePassword", "fakeRole", "fakeAPIKey");
		final User user = userDAO.getUserById("fakeId");

		userDAO.updateUser(user.getId(), "newPassword", "newRole", "newAPIKey");
		final User updatedUser = userDAO.getUserById(user.getId());

		Assert.assertEquals(user.getId(), updatedUser.getId());
		Assert.assertEquals(userDAO.sha1Encrypt("newPassword"), updatedUser.getPassword());
		Assert.assertEquals("newRole", updatedUser.getRole());
		Assert.assertEquals(aesCipher.encrypt("newAPIKey"), updatedUser.getCkanApiKey());

		userDAO.deleteUser("fakeId");
	}

	@Test
	public void testDeleteUser() throws Exception {
		logger.info("Testing delete user...");

		userDAO.createUser("fakeId", "fakePassword", "fakeRole", "fakeAPIKey");
		final User user = userDAO.getUserById("fakeId");

		userDAO.deleteUser(user.getId());
		final User deletedUser = userDAO.getUserById(user.getId());

		Assert.assertNull(deletedUser);
	}
}
