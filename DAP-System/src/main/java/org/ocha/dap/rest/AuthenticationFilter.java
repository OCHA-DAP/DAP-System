package org.ocha.dap.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.ocha.dap.service.DAPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author seustachi
 */
@Component
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Context
	private HttpServletRequest request;

	@Autowired
	private DAPService dapService;

	private static String SESSION_PARAM_UID = "SESSION_PARAM_UID";

	@Override
	public void filter(final ContainerRequestContext containerRequest) throws IOException {
		final String path = containerRequest.getUriInfo().getAbsolutePath().toString();
		log.debug(String.format("Entering ContainerRequest.filter %s", path));

		final HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute(SESSION_PARAM_UID) != null) {
			final String uid = session.getAttribute(SESSION_PARAM_UID).toString();
			log.debug(String.format("Setting SecurityContext for uid : %s", uid));
			containerRequest.setSecurityContext(new AuthorizationSecurityContext(dapService.getUserById(uid)));
		} else {
			log.debug("No session, about to redirect to login page");

			URI newURI = null;
			try {
				newURI = new URI("/admin/login/");
			} catch (final URISyntaxException e) {
			}
			final Response response = Response.seeOther(newURI).build();

			throw new WebApplicationException(response);
		}
	}

}