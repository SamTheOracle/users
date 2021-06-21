package com.oracolo.findmycar.users.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oracolo.findmycar.users.mqtt.messages.KeyChatValuesMessage;
import com.oracolo.findmycar.users.mqtt.messages.TelegramUserMessage;

import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class SyncService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	void onTelegramUserMessage(@Observes TelegramUserMessage telegramUserMessage) {
		logger.debug("Received event {}", telegramUserMessage);
		//TODO
	}

	void onKeyChatValuesMessage(@Observes KeyChatValuesMessage keyChatValuesMessage) {
		logger.debug("Received event {}", keyChatValuesMessage);
		//TODO
	}

	@Scheduled(every = "5s",concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
	void checkOutOfSyncUsers(){
		logger.trace("Checking out of sync users");
		//TODO
	}


}
