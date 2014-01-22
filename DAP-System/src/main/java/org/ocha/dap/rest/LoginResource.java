package org.ocha.dap.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.security.PermitAll;
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
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.mvc.Viewable;
import org.ocha.dap.security.exception.AuthenticationException;
import org.ocha.dap.service.DAPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@PermitAll
@Produces(MediaType.TEXT_HTML)
@Path("/")
@Component
public class LoginResource {
	private static Logger logger = LoggerFactory.getLogger(LoginResource.class);

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
	 *             if authentication exception occurs
	 * @throws URISyntaxException
	 *             the URI syntax exception occurs
	 */
	@POST
	@Path("login/")
	public Response login(@FormParam("userId") final String userId, @FormParam("password") final String password, @Context final UriInfo uriInfo, @Context final SecurityContext sc)
			throws AuthenticationException, URISyntaxException {
		logger.debug(String.format("Entering login for user : %s", userId));
		if ((dapService != null) && dapService.authenticate(userId, password)) {
			final HttpSession session = request.getSession(true);
			session.setAttribute(SESSION_PARAM_UID, userId);
			// 1800 seconds = 30 minutes
			session.setMaxInactiveInterval(1800);

			if ("admin".equals(dapService.getUserById(userId).getRole())) {
				final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/datasets/").build();
				return Response.seeOther(newURI).build();
			} else {
				final URI newURI = uriInfo.getBaseUriBuilder().path("api/yearly/source/acled/indicatortype/PVX040/BarChart/").build();
				return Response.seeOther(newURI).build();
			}

		}

		throw new AuthenticationException();
	}

	@GET
	public Response defaultPage(@Context final UriInfo uriInfo) {
		final URI newURI = uriInfo.getBaseUriBuilder().path("/login/").build();
		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("login/")
	public Response loginForm() {
		return Response.ok(new Viewable("/login", null)).build();
	}

	@GET
	@Path("logout/")
	public Response logout(@Context final UriInfo uriInfo) {
		final HttpSession session = request.getSession(false);
		session.invalidate();
		final URI newURI = uriInfo.getBaseUriBuilder().path("/login/").build();
		return Response.seeOther(newURI).build();
	}

}
