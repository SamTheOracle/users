package com.oracolo.findmycar.users.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import com.oracolo.findmycar.users.rest.dto.UserDto;

@ApplicationScoped
public class KeycloakConverter {

	private final static String PHOTO_URL = "photo_url";
	public final static String CHAT_ID = "chat_id";
	private final static String LOCALE = "locale";
	private static final String PASSWORD = "password";

	public UserRepresentation from(UserDto user) {
		UserRepresentation userRepresentation = new UserRepresentation();
		userRepresentation.setUsername(user.email);
		userRepresentation.setEmailVerified(true);
		CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
		credentialRepresentation.setType(PASSWORD);
		credentialRepresentation.setTemporary(false);
		credentialRepresentation.setValue(user.password);
		userRepresentation.setCredentials(List.of(credentialRepresentation));
		userRepresentation.setEnabled(true);
		userRepresentation.setEmail(user.email);
		userRepresentation.setFirstName(user.name);
		userRepresentation.setLastName(user.familyName);
		userRepresentation.setRealmRoles(List.of(Role.USER));
		Map<String, List<String>> attributes = new HashMap<>();
		attributes.put(PHOTO_URL, List.of(user.pictureUrl==null?"":user.pictureUrl));
		Long chatId = user.chatId;
		if(chatId!=null){
			attributes.put(CHAT_ID, List.of(chatId.toString()));
		}
		String locale = user.locale;
		attributes.put(LOCALE, List.of(locale == null ? "" : locale));
		userRepresentation.setAttributes(attributes);
		userRepresentation.setUsername(user.email);
		return userRepresentation;
	}

	public UserDto to(UserRepresentation userByEmail) {
		UserDto userDto = new UserDto();
		List<String> chatIdAttribute = userByEmail.getAttributes().get(CHAT_ID);
		if (chatIdAttribute != null)
			userDto.chatId = chatIdAttribute.stream().findFirst().map(Long::parseLong).orElse(null);
		userDto.uniqueKey = userByEmail.getId();
		List<String> pictureUrlAttribute = userByEmail.getAttributes().get(PHOTO_URL);
		if (pictureUrlAttribute != null)
			userDto.pictureUrl = pictureUrlAttribute.stream().findFirst().orElse(null);
		List<String> localeAttribute = userByEmail.getAttributes().get(LOCALE);
		if (localeAttribute != null)
			userDto.locale = localeAttribute.stream().findFirst().orElse(null);
		userDto.email = userByEmail.getEmail();
		userDto.name = userByEmail.getFirstName();
		userDto.familyName = userByEmail.getLastName();
		userDto.id = userByEmail.getId();
		userDto.userName = userByEmail.getUsername();
		return userDto;
	}

	public UserRepresentation addChatId(UserRepresentation user, Long chatId) {
		Map<String, List<String>> attributes = user.getAttributes();
		if (attributes == null) {
			attributes = new HashMap<>();
			user.setAttributes(attributes);
		}
		attributes.put(CHAT_ID, List.of(chatId.toString()));
		return user;
	}
}
