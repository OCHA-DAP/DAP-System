package org.ocha.dap.persistence.dao;

import java.util.List;

import org.ocha.dap.persistence.entity.User;
import org.ocha.dap.security.exception.AuthenticationException;
import org.ocha.dap.security.exception.InsufficientCredentialsException;

public interface UserDAO {

	public void createUser(final String id, final String password, final String apiKey) throws Exception;

	public void deleteUser(final String id);

	public List<User> listUsers();

	public String getUserApiKey(final String id) throws InsufficientCredentialsException;

	public boolean authenticate(final String id, final String password) throws AuthenticationException;

}
