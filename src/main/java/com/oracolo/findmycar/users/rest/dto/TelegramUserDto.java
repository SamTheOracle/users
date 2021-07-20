package com.oracolo.findmycar.users.rest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class TelegramUserDto {

	@NotBlank
	public String uniqueKeyValue;
	@NotNull
	public Long chatId;

	@Override
	public String toString() {
		return "TelegramUserDto{" + "uniqueKeyValue='" + uniqueKeyValue + '\'' + ", chatId=" + chatId + '}';
	}
}
