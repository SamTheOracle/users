package com.oracolo.findmycar.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oracolo.findmycar.users.entity.User;
import com.oracolo.findmycar.users.mqtt.converter.SyncConverter;
import com.oracolo.findmycar.users.mqtt.messages.KeyChatValuesMessage;
import com.oracolo.findmycar.users.mqtt.messages.RetrySyncMessage;
import com.oracolo.findmycar.users.mqtt.messages.TelegramUserMessage;

import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class SyncService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Inject
	SyncConverter syncConverter;

	@Inject
	MqttClientService mqttClientService;

	void onTelegramUserMessage(@Observes TelegramUserMessage telegramUserMessage) {
		logger.debug("Received event {}", telegramUserMessage);
		Optional<User> userOptional = Optional.empty();
		if(userOptional.isEmpty()){
			logger.debug("Received a message for a non existing user");
			return;
		}
		User user = userOptional.get();
		user.chatId = telegramUserMessage.chatId;



		//TODO
	}

	void onKeyChatValuesMessage(@Observes KeyChatValuesMessage keyChatValuesMessage) {
		logger.debug("Received event {}", keyChatValuesMessage);
		//TODO
	}

	@Scheduled(every = "5s",concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
	void checkOutOfSyncUsers(){
		logger.trace("Checking out of sync users");
		List<User> outOfSyncUsers = new ArrayList<>();
		RetrySyncMessage retrySyncMessage = syncConverter.to(outOfSyncUsers);
		mqttClientService.sendRetrySyncMessage(retrySyncMessage);
	}


}
