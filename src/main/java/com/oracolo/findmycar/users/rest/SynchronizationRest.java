package com.oracolo.findmycar.users.rest;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.oracolo.findmycar.users.entity.User;
import com.oracolo.findmycar.users.rest.converter.UniqueKeyConverter;
import com.oracolo.findmycar.users.rest.converter.UserConverter;
import com.oracolo.findmycar.users.rest.dto.TelegramUserDto;
import com.oracolo.findmycar.users.rest.dto.UniqueKeyDto;
import com.oracolo.findmycar.users.rest.dto.UserDto;
import com.oracolo.findmycar.users.rest.validators.GoogleUserValidator;
import com.oracolo.findmycar.users.rest.validators.TelegramUserValidator;
import com.oracolo.findmycar.users.service.UserService;

@Path("users/synchronization")
public class SynchronizationRest {

	@Inject
	UserService userService;
	@Inject
	UserConverter userConverter;

	@Inject
	UniqueKeyConverter uniqueKeyConverter;

	@Inject
	TelegramUserValidator telegramUserValidator;

	@POST
	@Path("merge")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void mergeTelegramUser(TelegramUserDto telegramUserDto){
		telegramUserValidator.validate(telegramUserDto);
		User user = userService.getUserByUniqueKey(telegramUserDto.uniqueKeyValue).orElseThrow(()->new NotFoundException("User not found"));
		userService.mergeUser(user,telegramUserDto.chatId);
	}

	@GET
	@Path("google")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserDto> getUserByQuery(@QueryParam("uniqueKey") String uniqueKey){
		return userConverter.to(userService.getUsersByQuery(uniqueKey));
	}

}
