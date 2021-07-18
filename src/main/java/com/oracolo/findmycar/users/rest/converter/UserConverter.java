package com.oracolo.findmycar.users.rest.converter;

import java.util.List;
import java.util.stream.Collectors;

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
		user.setFamilyName(userDto.familyName);
		user.setGivenName(userDto.givenName);
		user.setLocale(userDto.locale);
		user.setChatId(userDto.chatId);
		return user;
	}

	public List<UserDto> to(List<User> users){
		return users.stream().map(this::to).collect(Collectors.toList());
	}

	public UserDto to(User user){
		UserDto userDto = new UserDto();
		userDto.name = user.getName();
		userDto.pictureUrl = user.getPictureUrl();
		userDto.email = user.getEmail();
		userDto.familyName = user.getFamilyName();
		userDto.chatId = user.getChatId();
		userDto.id = user.getId();
		userDto.givenName = user.getGivenName();
		userDto.uniqueKey = user.getUniqueKey();
		return userDto;
	}
}
