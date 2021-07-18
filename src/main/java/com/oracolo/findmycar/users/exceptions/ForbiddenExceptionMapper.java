package com.oracolo.findmycar.users.exceptions;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ForbiddenExceptionMapper extends BaseExceptionMapper implements ExceptionMapper<ForbiddenException> {
	@Override
	public Response toResponse(ForbiddenException exception) {
		return Response.status(Response.Status.FORBIDDEN).entity(
				format(NotFoundException.class.getSimpleName(), exception.getMessage())).build();
	}
}
