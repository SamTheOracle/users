package com.oracolo.findmycar.users.mqtt.messages;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class TelegramUserMessage {

	public String uniqueKeyValue;
	public Long chatId;

	@Override
	public String toString() {
		return "TelegramUserDto{" + "uniqueKeyValue='" + uniqueKeyValue + '\'' + ", chatId=" + chatId + '}';
	}
}
