package com.oracolo.findmycar.users.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.oracolo.findmycar.users.auth.KeycloakConverter;
import com.oracolo.findmycar.users.auth.KeycloakService;
import com.oracolo.findmycar.users.rest.converter.UniqueKeyConverter;
import com.oracolo.findmycar.users.rest.dto.UniqueKeyDto;
import com.oracolo.findmycar.users.rest.dto.UserDto;
import com.oracolo.findmycar.users.rest.validators.GoogleUserValidator;

@RequestScoped
@Path("users")
public class UserRest {

	@Inject
	GoogleUserValidator googleUserValidator;
	@Inject
	UniqueKeyConverter uniqueKeyConverter;
	@Inject
	KeycloakService keycloakService;
	@Inject
	KeycloakConverter keycloakConverter;


	@POST
	@Path("google")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewGoogleUser(UserDto dto){
		googleUserValidator.validate(dto);
		String uniqueKey = keycloakService.createKeycloakUser(keycloakConverter.from(dto));
		UniqueKeyDto uniqueKeyDto = uniqueKeyConverter.to(uniqueKey);
		return Response.status(201).entity(uniqueKeyDto).build();
	}

	@GET
	@Path("google")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDto getUserByEmail(@NotNull @QueryParam("email") String email){
		return keycloakConverter.to(keycloakService.getUserByEmail(email).orElseThrow(()->new NotFoundException("User not found")));
	}

	@GET
	@Path("google/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDto getUserById(@NotBlank @PathParam("id") String id){
		return keycloakConverter.to(keycloakService.getUserById(id).orElseThrow(()->new NotFoundException("User not found")));
	}

}
