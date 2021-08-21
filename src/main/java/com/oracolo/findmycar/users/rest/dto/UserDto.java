package com.oracolo.findmycar.users.rest.dto;

import javax.validation.constraints.NotBlank;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class UserDto {
	public String id;
	@NotBlank
	public String email;
	@NotBlank
	public String name;
	public String pictureUrl;
	public String locale;
	public String familyName;
	public Long chatId;
	public String uniqueKey;
	public String userName;
	public String password;

	@Override
	public String toString() {
		return "UserDto{" + "id='" + id + '\'' + ", email='" + email + '\'' + ", name='" + name + '\'' + ", pictureUrl='" + pictureUrl
				+ '\'' + ", locale='" + locale + '\'' + ", familyName='" + familyName + '\'' + ", chatId=" + chatId + ", uniqueKey='"
				+ uniqueKey + '\'' + ", userName='" + userName + '\'' + '}';
	}
}
