package com.oracolo.findmycar.users.exceptions;

import java.util.Objects;

public abstract class BaseExceptionMapper {

	protected ErrorDto format(String exception, String message){
		ErrorDto errorDto = new ErrorDto();
		errorDto.error = Objects.requireNonNullElse(exception,"Exception");
		errorDto.message = Objects.requireNonNull(message,"Error occured");
		return errorDto;
	}
}
