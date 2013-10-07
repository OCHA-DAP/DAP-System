package org.ocha.dap.persistence.dao;

import org.ocha.dap.security.exception.AuthenticationException;

public interface UserDAO {

	public void createUser(final String id, final String password, final String apiKey) throws Exception;

	public void authenticate(final String id, final String password) throws AuthenticationException;

}
