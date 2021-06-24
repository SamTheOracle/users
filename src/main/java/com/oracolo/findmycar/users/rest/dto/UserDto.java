package com.oracolo.findmycar.users.rest.dto;

import javax.validation.constraints.NotEmpty;

public class UserDto {
	public Long id;
	@NotEmpty
	public String email;
	@NotEmpty
	public String name;
	@NotEmpty
	public String pictureUrl;
	public String locale;
	public String familyName;
	public String givenName;
	public String chatId;
	public String uniqueKey;

	@Override
	public String toString() {
		return "UserDto{" + "id=" + id + ", email='" + email + '\'' + ", name='" + name + '\'' + ", pictureUrl='" + pictureUrl + '\''
				+ ", locale='" + locale + '\'' + ", familyName='" + familyName + '\'' + ", givenName='" + givenName + '\'' + ", chatId='"
				+ chatId + '\'' + ", uniqueKey='" + uniqueKey + '\'' + '}';
	}
}
