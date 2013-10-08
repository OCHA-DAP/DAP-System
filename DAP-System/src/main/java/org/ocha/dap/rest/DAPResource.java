package org.ocha.dap.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ocha.dap.security.exception.AuthenticationException;
import org.ocha.dap.service.DAPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.view.Viewable;

@Path("/")
@Produces(MediaType.TEXT_HTML)
@Component
public class DAPResource {

	private static Logger logger = LoggerFactory.getLogger(DAPResource.class);

	private static String SESSION_PARAM_UID = "SESSION_PARAM_UID";

	@Context
	private HttpServletRequest request;

	@Autowired
	private DAPService dapService;

	/**
	 * Create a session from the token
	 * 
	 * @param token
	 *            the token
	 * @return response with session
	 * @throws AuthenticationException
	 *             if authentication exception occur
	 * @throws URISyntaxException
	 *             the URI syntax exception occur
	 */
	@POST
	@Path("/login/")
	public Response login(@FormParam("id") final String id, @FormParam("password") final String password) throws AuthenticationException, URISyntaxException {
		logger.debug(String.format("Entering login for user : %s", id));
		if (dapService != null) {
			dapService.authenticate(id, password);
			final HttpSession session = request.getSession(true);
			session.setAttribute(SESSION_PARAM_UID, id);
			// 300 seconds = 5 minutes
			session.setMaxInactiveInterval(300);
		}

		URI newURI = null;
		newURI = new URI("/index");

		return Response.seeOther(newURI).build();
	}
	
	@GET
	public Response index(){
		return Response.ok(new Viewable("/index", null)).build();
	}

}
