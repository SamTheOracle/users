package com.oracolo.findmycar.users.rest.validators;

import java.util.regex.Pattern;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.BadRequestException;

import com.oracolo.findmycar.users.rest.dto.UserDto;

@ApplicationScoped
public class GoogleUserValidator extends BaseValidator<UserDto> {
	private final Pattern emailPattern = Pattern.compile(
			"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
	private final Pattern urlPattern = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
	@Override
	public void validate(UserDto object) {
		super.validate(object);
		if(!emailPattern.matcher(object.email).matches()){
			throw new BadRequestException("Invalid user email");
		}
		if(!urlPattern.matcher(object.pictureUrl).matches()){
			throw new BadRequestException("Invalid picture url");
		}

	}
}
