package org.ocha.dap.persistence.dao;

import java.util.List;

import org.ocha.dap.persistence.entity.User;
import org.ocha.dap.security.exception.AuthenticationException;

public interface UserDAO {

	public void createUser(final String id, final String password, final String apiKey) throws Exception;
	public List<User> listUsers();
	public String getUserApiKey(final String id) throws Exception;

	public boolean authenticate(final String id, final String password) throws AuthenticationException;

}
