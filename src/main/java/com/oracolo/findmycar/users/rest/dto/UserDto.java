package com.oracolo.findmycar.users.rest.dto;

import javax.validation.constraints.NotBlank;

public class UserDto {
	public String id;
	@NotBlank
	public String email;
	@NotBlank
	public String name;
	@NotBlank
	public String pictureUrl;
	public String locale;
	public String familyName;
	public String givenName;
	public Long chatId;
	public String uniqueKey;

	@Override
	public String toString() {
		return "UserDto{" + "id=" + id + ", email='" + email + '\'' + ", name='" + name + '\'' + ", pictureUrl='" + pictureUrl + '\''
				+ ", locale='" + locale + '\'' + ", familyName='" + familyName + '\'' + ", givenName='" + givenName + '\'' + ", chatId='"
				+ chatId + '\'' + ", uniqueKey='" + uniqueKey + '\'' + '}';
	}
}
