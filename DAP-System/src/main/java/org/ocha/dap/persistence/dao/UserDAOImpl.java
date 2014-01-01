package org.ocha.dap.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.dap.persistence.entity.User;
import org.ocha.dap.security.exception.AuthenticationException;
import org.ocha.dap.security.exception.InsufficientCredentialsException;
import org.ocha.dap.security.tools.AESCipher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class UserDAOImpl implements UserDAO {

	private static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private AESCipher aesCipher;

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void createUser(final String id, final String password, final String ckanApiKey) throws Exception {
		final User userToCreate = new User(id, sha1Encrypt(password), aesCipher.encrypt(ckanApiKey));

		em.persist(userToCreate);
	}

	@Override
	@Transactional
	public void deleteUser(final String id) {
		final User user = em.find(User.class, id);
		em.remove(user);
	}

	@Override
	public List<User> listUsers() {
		final TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}

	@Override
	public String getUserApiKey(final String id) throws InsufficientCredentialsException {
		final String apiKey;
		try {
			final User user = em.find(User.class, id);
			apiKey = aesCipher.decrypt(user.getCkanApiKey());
		} catch (final Exception e) {
			throw new InsufficientCredentialsException();
		}

		if (apiKey == null)
			throw new InsufficientCredentialsException();

		return apiKey;
	}

	@Override
	public boolean authenticate(final String id, final String password) throws AuthenticationException {
		logger.debug(String.format("about to authenticate user : %s", id));
		if (id == null || password == null) {
			throw new AuthenticationException();
		}
		final User user = em.find(User.class, id);

		if (user == null)
			throw new AuthenticationException();

		if (!sha1Encrypt(password).equals(user.getPassword()))
			throw new AuthenticationException();

		return true;
	}

	// encryption method
	private String sha1Encrypt(final String plaintext) {
		MessageDigestPasswordEncoder md = null;
		md = new MessageDigestPasswordEncoder("SHA-1");
		md.setEncodeHashAsBase64(true);
		return "{SHA}" + md.encodePassword(plaintext, null);
	}

	@Override
	public User getUserById(final String userId) {
		return em.find(User.class, userId);
	}

}
