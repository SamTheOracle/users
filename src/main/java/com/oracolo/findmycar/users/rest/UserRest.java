package com.oracolo.findmycar.users.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.oracolo.findmycar.users.rest.dto.IdTokenDto;

@Path("users")
public class UserRest {

	@POST
	@Path("google")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createNewGoogleUser(IdTokenDto dto){

	}

}
