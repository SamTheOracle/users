package com.oracolo.findmycar.users.exceptions;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServerErrorExceptionMapper extends BaseExceptionMapper implements ExceptionMapper<ServerErrorException> {
	@Override
	public Response toResponse(ServerErrorException exception) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
				format(NotFoundException.class.getSimpleName(), exception.getMessage())).build();
	}
}
