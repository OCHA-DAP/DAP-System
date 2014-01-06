package org.ocha.dap.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ocha.dap.security.exception.InsufficientCredentialsException;
import org.springframework.stereotype.Component;

/**
 * 
 * @author seustachi
 *
 */
@Provider
@Component
public class InsufficientCredentialsExceptionMapper implements ExceptionMapper<InsufficientCredentialsException>{

  @Override
  public Response toResponse(final InsufficientCredentialsException exception) {
    return Response.status(Status.FORBIDDEN).entity("INSUFFICIENT_CREDENTIALS").type(MediaType.TEXT_PLAIN).build();
  }
}
