package com.oracolo.findmycar.users.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.BadRequestException;

import org.keycloak.representations.idm.UserRepresentation;

import com.oracolo.findmycar.users.rest.dto.UserDto;

@ApplicationScoped
public class KeycloakConverter {

	private final static String UNIQUE_KEY = "unique_key";
	private final static String PHOTO_URL = "photo_url";
	private final static String CHAT_ID = "chat_id";
	private final static String LOCALE = "locale";

	public UserRepresentation from(UserDto user) {
		UserRepresentation userRepresentation = new UserRepresentation();
		userRepresentation.setEmailVerified(true);
		userRepresentation.setEnabled(true);
		userRepresentation.setEmail(user.email);
		userRepresentation.setFirstName(user.name);
		userRepresentation.setLastName(user.familyName);
		userRepresentation.setRealmRoles(List.of(Role.USER));
		Map<String,List<String>> attributes = new HashMap<>();
		attributes.put(PHOTO_URL,List.of(user.pictureUrl));
		Long chatId = user.chatId;
		attributes.put(CHAT_ID,List.of(chatId==null?"0":chatId.toString()));
		String locale = user.locale;
		attributes.put(LOCALE,List.of(locale==null?"":locale));
		userRepresentation.setAttributes(attributes);
		return userRepresentation;
	}

	public UserDto to(UserRepresentation userByEmail) {
		UserDto userDto = new UserDto();
		List<String> chatIdAttribute = userByEmail.getAttributes().get(CHAT_ID);
		userDto.chatId = chatIdAttribute.stream().findFirst().map(Long::parseLong).orElse(null);
		List<String> uniqueKeyAttribute=userByEmail.getAttributes().get(UNIQUE_KEY);
		userDto.uniqueKey = uniqueKeyAttribute.stream().findFirst().orElseThrow(()->new BadRequestException("User does not have unique key"));
		List<String> pictureUrlAttribute = userByEmail.getAttributes().get(PHOTO_URL);
		userDto.pictureUrl = pictureUrlAttribute.stream().findFirst().orElse(null);
		List<String> localeAttribute = userByEmail.getAttributes().get(LOCALE);
		userDto.locale = localeAttribute.stream().findFirst().orElse(null);
		userDto.email = userByEmail.getEmail();
		userDto.name  =userByEmail.getFirstName();
		userDto.familyName = userByEmail.getLastName();
		userDto.id = userByEmail.getId();
		return userDto;
	}

	public UserRepresentation addUniqueKey(UserRepresentation user, String uniqueKey) {
		Map<String,List<String>> attributes =user.getAttributes();
		attributes.put(UNIQUE_KEY,List.of(uniqueKey));
		return user;
	}
}
