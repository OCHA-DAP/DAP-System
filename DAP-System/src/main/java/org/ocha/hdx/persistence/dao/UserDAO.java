package org.ocha.hdx.persistence.dao;

import java.util.List;

import org.ocha.hdx.persistence.entity.User;
import org.ocha.hdx.security.exception.AuthenticationException;
import org.ocha.hdx.security.exception.InsufficientCredentialsException;

public interface UserDAO {

	public void createUser(final String id, final String password, final String role, final String apiKey) throws Exception;

	public void updateUser(final String id, final String password, final String role, final String apiKey) throws Exception;

	public void deleteUser(final String id);

	public User getUserById(String userId);

	public List<User> listUsers();

	public List<String> listRoles();

	public String getUserApiKey(final String id) throws InsufficientCredentialsException;

	public boolean authenticate(final String id, final String password) throws AuthenticationException;

	public String sha1Encrypt(final String plaintext);

}
