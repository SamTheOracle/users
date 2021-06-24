package com.oracolo.findmycar.users.rest.converter;

import javax.enterprise.context.ApplicationScoped;

import com.oracolo.findmycar.users.entity.User;
import com.oracolo.findmycar.users.rest.dto.UserDto;

import liquibase.pro.packaged.U;

@ApplicationScoped
public class UserConverter {

	public User from(UserDto userDto){
		User user = new User();
		user.setEmail(userDto.email);
		user.setName(userDto.name);
		user.setPictureUrl(userDto.pictureUrl);
		return user;
	}
}
