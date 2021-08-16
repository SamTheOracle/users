package com.oracolo.findmycar.users.mqtt.converter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.oracolo.findmycar.users.mqtt.messages.RetrySyncMessage;
import com.oracolo.findmycar.users.rest.dto.UserDto;

@ApplicationScoped
public class SyncConverter {

	public RetrySyncMessage to(List<UserDto> users){
		RetrySyncMessage retrySyncMessage = new RetrySyncMessage();
		retrySyncMessage.messageId = UUID.randomUUID().toString();
		retrySyncMessage.uniqueKeys = users.stream().map(userDto -> userDto.uniqueKey).collect(Collectors.toUnmodifiableList());
		return retrySyncMessage;
	}


}
