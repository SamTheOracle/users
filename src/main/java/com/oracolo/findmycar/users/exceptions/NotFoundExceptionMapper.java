package com.oracolo.findmycar.users.exceptions;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper extends BaseExceptionMapper implements ExceptionMapper<NotFoundException> {
	@Override
	public Response toResponse(NotFoundException exception) {
		return Response.status(Response.Status.NOT_FOUND).entity(
				format(NotFoundException.class.getSimpleName(), exception.getMessage())).build();
	}
}
