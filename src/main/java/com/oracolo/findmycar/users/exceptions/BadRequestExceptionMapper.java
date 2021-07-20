package com.oracolo.findmycar.users.exceptions;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper extends BaseExceptionMapper implements ExceptionMapper<BadRequestException> {
	@Override
	public Response toResponse(BadRequestException exception) {
		return Response.status(Response.Status.BAD_REQUEST).entity(
				format(NotFoundException.class.getSimpleName(), exception.getMessage())).build();
	}
}
