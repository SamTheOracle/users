package com.oracolo.findmycar.users.service;

import static com.oracolo.findmycar.users.auth.KeycloakConverter.CHAT_ID;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oracolo.findmycar.users.auth.KeycloakService;
import com.oracolo.findmycar.users.mqtt.converter.SyncConverter;
import com.oracolo.findmycar.users.mqtt.messages.KeyChatPair;
import com.oracolo.findmycar.users.mqtt.messages.KeyChatValuesMessage;
import com.oracolo.findmycar.users.mqtt.messages.RetrySyncMessage;
import com.oracolo.findmycar.users.mqtt.messages.TelegramUserMessage;

import io.quarkus.runtime.Startup;
import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
@Startup
public class SyncService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Inject
	SyncConverter syncConverter;

	@Inject
	MqttClientService mqttClientService;

	@Inject
	KeycloakService keycloakService;

	void onTelegramUserMessage(@Observes TelegramUserMessage telegramUserMessage) {
		logger.debug("Received event {}", telegramUserMessage);
		keycloakService.updateUser(telegramUserMessage.uniqueKeyValue, telegramUserMessage.chatId);
	}

	void onKeyChatValuesMessage(@Observes KeyChatValuesMessage keyChatValuesMessage) {
		logger.debug("Received event {}", keyChatValuesMessage);
		for (KeyChatPair keyChatPair : keyChatValuesMessage.uniqueKeyValues) {
			keycloakService.updateUser(keyChatPair.uniqueKeyValue, keyChatPair.chatId);
		}
	}

	@Scheduled(every = "5s", delay = 10, concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
	void checkOutOfSyncUsers() {
		logger.trace("Checking out of sync users");
		Predicate<UserRepresentation> userRepresentationPredicate = userRepresentation -> {
			if (userRepresentation.getAttributes() == null) {
				return false;
			}
			return userRepresentation.getAttributes().get(CHAT_ID) == null;
		};
		List<UserRepresentation> keycloakUsersOutOfSync = keycloakService.getUsers().stream().filter(userRepresentationPredicate).collect(
				Collectors.toUnmodifiableList());
		if (!keycloakUsersOutOfSync.isEmpty()) {
			logger.debug("Asking for chatId for users {}", keycloakUsersOutOfSync);
			RetrySyncMessage retrySyncMessage = syncConverter.to(keycloakUsersOutOfSync);
			mqttClientService.sendRetrySyncMessage(retrySyncMessage);
		}
	}

}
