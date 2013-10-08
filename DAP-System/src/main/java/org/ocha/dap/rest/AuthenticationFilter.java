package org.ocha.dap.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

/**
 * 
 * @author seustachi
 */
@Component
public class AuthenticationFilter implements ContainerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Context
	private HttpServletRequest request;

	private static String SESSION_PARAM_UID = "SESSION_PARAM_UID";

	@Override
	public ContainerRequest filter(final ContainerRequest containerRequest) {
		final String path = containerRequest.getAbsolutePath().toString();
		log.debug(String.format("Entering ContainerRequest.filter %s", path));
		if (path.matches(".*login/")) {
			log.debug("Do nothing, login page must be allowed");
		} else {
			checkSessionAndSetCommonRequestAttributesForJspViews();
		}
		return containerRequest;
	}

	/**
	 * checks session and sets request attributes that are required by all JSP
	 * views.
	 */
	private void checkSessionAndSetCommonRequestAttributesForJspViews() {
		final HttpSession session = request.getSession(false);
		if (session != null) {
			final String uid = session.getAttribute(SESSION_PARAM_UID).toString();
			log.debug(String.format("got uid : %s in session", uid));
			request.setAttribute("uid", uid);
		} else {
			log.debug("No session, about to redirect to login page");

			URI newURI = null;
			try {
				newURI = new URI("/login/");
			} catch (final URISyntaxException e) {
			}
			final Response response = Response.seeOther(newURI).build();

			throw new WebApplicationException(response);
		}

	}

}