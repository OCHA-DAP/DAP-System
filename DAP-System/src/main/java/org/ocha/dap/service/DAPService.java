package org.ocha.dap.service;

import org.ocha.dap.security.exception.AuthenticationException;

public interface DAPService {
	
	public void getFilesFromCKAN();
	
	public boolean authenticate(final String id, final String password) throws AuthenticationException;

}
