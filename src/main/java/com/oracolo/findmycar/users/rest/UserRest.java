package com.oracolo.findmycar.users.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.oracolo.findmycar.users.rest.converter.UniqueKeyConverter;
import com.oracolo.findmycar.users.rest.converter.UserConverter;
import com.oracolo.findmycar.users.rest.dto.UniqueKeyDto;
import com.oracolo.findmycar.users.rest.dto.UserDto;
import com.oracolo.findmycar.users.rest.validators.GoogleUserValidator;
import com.oracolo.findmycar.users.service.UserService;

@Path("users")
public class UserRest {

	@Inject
	UserService userService;
	@Inject
	UserConverter userConverter;
	@Inject
	GoogleUserValidator googleUserValidator;
	@Inject
	UniqueKeyConverter uniqueKeyConverter;

	@POST
	@Path("google")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewGoogleUser(UserDto dto){
		googleUserValidator.validate(dto);
		UniqueKeyDto uniqueKeyDto = uniqueKeyConverter.to(userService.createUserAndGetUniqueKey(userConverter.from(dto)));
		return Response.status(201).entity(uniqueKeyDto).build();
	}

	@GET
	@Path("google")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserDto> getUserByQuery(@QueryParam("uniqueKey") String uniqueKey){
		return userConverter.to(userService.getUsersByQuery(uniqueKey));
	}

}
