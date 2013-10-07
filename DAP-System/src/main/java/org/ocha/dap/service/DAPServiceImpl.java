package org.ocha.dap.service;

import org.ocha.dap.persistence.dao.UserDAO;
import org.ocha.dap.security.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;

public class DAPServiceImpl implements DAPService {

	@Autowired
	private UserDAO userDao;

	@Override
	public void getFilesFromCKAN() {
		// TODO Auto-generated method stub

	}

	@Override
	public void authenticate(final String id, final String password) throws AuthenticationException {
		userDao.authenticate(id, password);
	}

}
