package com.oracolo.findmycar.users.mqtt.converter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.oracolo.findmycar.users.entity.User;
import com.oracolo.findmycar.users.mqtt.messages.RetrySyncMessage;
import com.oracolo.findmycar.users.mqtt.messages.TelegramUserMessage;

@ApplicationScoped
public class SyncConverter {

	public RetrySyncMessage to(List<User> users){
		RetrySyncMessage retrySyncMessage = new RetrySyncMessage();
		retrySyncMessage.messageId = UUID.randomUUID().toString();
		retrySyncMessage.uniqueKeys = users.stream().map(user -> user.uniqueKey).collect(Collectors.toUnmodifiableList());
		return retrySyncMessage;
	}


}
