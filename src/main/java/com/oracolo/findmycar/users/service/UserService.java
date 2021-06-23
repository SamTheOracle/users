package com.oracolo.findmycar.users.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.oracolo.findmycar.users.dao.UserDao;
import com.oracolo.findmycar.users.entity.User;
import com.oracolo.findmycar.users.mqtt.messages.KeyChatValuesMessage;

@ApplicationScoped
public class UserService {

	@Inject
	UserDao userDao;

	@Transactional
	public void insert(User user) {
		userDao.insert(user);
	}

	@Transactional
	public void update(User user) {
		userDao.update(user);
	}

	public List<User> getAllUserWithChatIdNull() {
		return userDao.getUserWithChatIdNull();
	}

	public Optional<User> getUserByUniqueKey(String uniqueKey) {
		return userDao.getUserByUniqueKey(uniqueKey);
	}

	@Transactional
	public void updateUsers(KeyChatValuesMessage keyChatValuesMessage) {
		List<User> users = userDao.getAllUserByUniqueKeys(
				keyChatValuesMessage.uniqueKeyValues.stream().map(keyChatPair -> keyChatPair.uniqueKeyValue).collect(
						Collectors.toUnmodifiableList()));
		for (User user : users) {
			Optional<Long> chatIdOptional = keyChatValuesMessage.uniqueKeyValues.stream().filter(
					keyChatPair -> keyChatPair.uniqueKeyValue.equals(user.getUniqueKey())).map(
					keyChatPair -> keyChatPair.chatId).findFirst();
			if (chatIdOptional.isPresent()) {
				Long chatId = chatIdOptional.get();
				user.setChatId(chatId);
				update(user);
			}
		}

	}
}
