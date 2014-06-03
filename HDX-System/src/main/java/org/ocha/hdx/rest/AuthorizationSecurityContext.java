package org.ocha.hdx.rest;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import org.ocha.hdx.persistence.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthorizationSecurityContext implements SecurityContext {

	private static final Logger log = LoggerFactory.getLogger(AuthorizationSecurityContext.class);

	private final User user;

	public AuthorizationSecurityContext(final User user) {
		super();
		this.user = user;
	}

	public AuthorizationSecurityContext() {
		super();
		this.user = null;
	}

	@Override
	public String getAuthenticationScheme() {
		return null;
	}

	@Override
	public Principal getUserPrincipal() {
		return null;
	}

	@Override
	public boolean isSecure() {
		return false;
	}

	@Override
	public boolean isUserInRole(final String role) {
		if (user == null) {
			log.debug("about to reject null user");
			return false;
		}

		log.debug(String.format("about to evaluate user : %s and role : %s", user.getId(), role));
		return role.equals(user.getRole());
	}

}
