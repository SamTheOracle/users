package com.oracolo.findmycar.users.service;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import com.oracolo.findmycar.users.mqtt.messages.KeyChatValuesMessage;

@ApplicationScoped
public class UserService {

	@Transactional
	public void updateUsers(KeyChatValuesMessage keyChatValuesMessage) {
//		List<User> users = userDao.getAllUserByUniqueKeys(
//				keyChatValuesMessage.uniqueKeyValues.stream().map(keyChatPair -> keyChatPair.uniqueKeyValue).collect(
//						Collectors.toUnmodifiableList()));
//		for (User user : users) {
//			Optional<Long> chatIdOptional = keyChatValuesMessage.uniqueKeyValues.stream().filter(
//					keyChatPair -> keyChatPair.uniqueKeyValue.equals(user.getUniqueKey())).map(
//					keyChatPair -> keyChatPair.chatId).findFirst();
//			if (chatIdOptional.isPresent()) {
//				Long chatId = chatIdOptional.get();
//				user.setChatId(chatId);
//				update(user);
//			}
//		}
	}

}
