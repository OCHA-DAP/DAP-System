package org.ocha.dap.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.ocha.dap.security.exception.AuthenticationException;

/**
 * 
 * @author seustachi
 * 
 */
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

	@Override
	public Response toResponse(final AuthenticationException exception) {
		return Response.status(Status.UNAUTHORIZED).entity("Invalid login/password provided").type(MediaType.TEXT_PLAIN).build();
	}

}