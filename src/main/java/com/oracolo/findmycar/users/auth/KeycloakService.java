package com.oracolo.findmycar.users.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.handler.codec.http.HttpResponseStatus;

@ApplicationScoped
public class KeycloakService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@ConfigProperty(name = "keycloak.url")
	String keycloakUrl;

	@ConfigProperty(name = "keycloak.admin")
	String keycloakAdmin;

	@ConfigProperty(name = "keycloak.admin-pass")
	String keycloakAdminPass;

	@ConfigProperty(name = "keycloak.realm")
	String realm;

	@ConfigProperty(name = "keycloak.client")
	String clientId;

	@Inject
	KeycloakConverter keycloakConverter;

	private List<RoleRepresentation> roleRepresentations = new ArrayList<>();
	private Keycloak keycloak;

	@PostConstruct
	void init() {
		keycloak = Keycloak.getInstance(keycloakUrl, "master", keycloakAdmin, keycloakAdminPass, clientId);
		try{
			roleRepresentations = keycloak.realm(realm).roles().list();
		}catch (Exception e){
			logger.debug("Could not find any roles. Adding roles {}",Role.USER);
			RoleRepresentation roleRepresentation = new RoleRepresentation();
			roleRepresentation.setName("user");
			roleRepresentations.add(roleRepresentation);
		}
	}

	public String createKeycloakUser(UserRepresentation user) {
		Response response = keycloak.realm(realm).users().create(user);
		if (HttpResponseStatus.CREATED.code() != response.getStatus()) {
			throw new BadRequestException("Could not create user");
		}
		String userid = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
		logger.debug("User id {}", userid);
		UserResource userResource = keycloak.realm(realm).users().get(userid);
		List<RoleRepresentation> realmRoles = user.getRealmRoles().stream().map(realmName -> roleRepresentations.stream().filter(
				roleRepresentation -> roleRepresentation.getName().equals(realmName)).findFirst().orElseThrow()).collect(
				Collectors.toList());
		userResource.roles().realmLevel().add(realmRoles);
		return userid;
	}

	public Optional<UserRepresentation> getUserByEmail(String email) {
		return keycloak.realm(realm).users().search(email, true).stream().findFirst();
	}

	public Optional<UserRepresentation> getUserById(String id) {
		return Optional.of(keycloak.realm(realm).users().get(id)).map(UserResource::toRepresentation);
	}

	public void updateUser(String id, Long chatId) {
		UserResource userResource = keycloak.realm(realm).users().get(id);
		UserRepresentation userRepresentation = keycloakConverter.addChatId(userResource.toRepresentation(), chatId);
		userResource.update(userRepresentation);
	}

	public List<UserRepresentation> getUsers() {

		return keycloak.realm(realm).users().list();
	}
}
