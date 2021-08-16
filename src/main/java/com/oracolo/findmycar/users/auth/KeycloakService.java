package com.oracolo.findmycar.users.auth;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private Keycloak keycloak;

	@PostConstruct
	void init() {
		keycloak = Keycloak.getInstance(keycloakUrl, "master", keycloakAdmin, keycloakAdminPass, clientId);
	}

	public String createKeycloakUser(UserRepresentation user) {
		String uniqueKey = UUID.randomUUID().toString();
		keycloak.realm(realm).users().create(keycloakConverter.addUniqueKey(user, uniqueKey));
		return uniqueKey;
	}

	public Optional<UserRepresentation> getUserByEmail(String email) {
		return keycloak.realm(realm).users().search(email, true).stream().findFirst();
	}
}
