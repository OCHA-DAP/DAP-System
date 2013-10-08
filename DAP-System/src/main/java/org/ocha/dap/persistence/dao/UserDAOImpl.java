package org.ocha.dap.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ocha.dap.persistence.entity.User;
import org.ocha.dap.security.exception.AuthenticationException;
import org.ocha.dap.security.tools.AESCipher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private AESCipher aesCipher;

	@PersistenceContext
	private EntityManager em;

	@Override
	public void createUser(final String id, final String password, final String ckanApiKey) throws Exception {
		final User userToCreate = new User(id, sha1Encrypt(password), aesCipher.encrypt(ckanApiKey));

		em.persist(userToCreate);
	}

	@Override
	public void authenticate(final String id, final String password) throws AuthenticationException {
		if (id == null || password == null) {
			throw new AuthenticationException();
		}
		final User user = em.find(User.class, id);

		if (!sha1Encrypt(password).equals(user.getPassword()))
			throw new AuthenticationException();
	}

	// encryption method
	private String sha1Encrypt(final String plaintext) {
		MessageDigestPasswordEncoder md = null;
		md = new MessageDigestPasswordEncoder("SHA-1");
		md.setEncodeHashAsBase64(true);
		return "{SHA}" + md.encodePassword(plaintext, null);
	}

}
