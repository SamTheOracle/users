package com.oracolo.findmycar.users.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class IdTokenDto {
	@NotEmpty
	public String idToken;

	@Override
	public String toString() {
		return "CreateGoogleUserDto{" + "idToken='" + idToken + '\'' + '}';
	}
}
