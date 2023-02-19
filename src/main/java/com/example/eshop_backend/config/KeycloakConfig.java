package com.example.eshop_backend.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    static Keycloak keycloak;
    @Value("${keycloak.auth-server-url}")
    final static String serverUrl = "http://localhost:8181/auth";
    @Value("${keycloak.realm}")
    public final static String realm = "eshop_backend";
    @Value("${keycloak.client-id}")
    final static String clientId = "eshop_backend";
    @Value("${keycloak.client-secret}")
    final static String clientSecret = "Z9TWYDnhz9RQVGxDKorlCWuCchv7c1rP";

    public static Keycloak getInstance() {
        if (keycloak == null) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .build();
        }
        return keycloak;

    }

    public RealmResource realmResource() {
        return getInstance().realm(realm);
    }

    public UsersResource userResource() {
        return realmResource().users();
    }


}
