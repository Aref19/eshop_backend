package com.example.eshop_backend.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    static Keycloak keycloak;
    @Value("${keycloak.auth-server-url}")
    final static String serverUrl = "http://localhost:8080/auth";
    @Value("${keycloak.realm}")
    public final static String realm = "master";
    @Value("${keycloak.client-id}")
    final static String clientId = "eshop_backend";
    @Value("${keycloak.client-secret}")
    final static String clientSecret = "T2hxnRgPTXhZsjz2v3Q7Oq1PziciyyOU";

    public static Keycloak getInstance() {
        if (keycloak == null) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .clientId(clientId)
                    .username("admin")
                    .password("password")
                    .clientSecret(clientSecret)
                    .build();
        }
        return keycloak;

    }

    @Bean
    public RealmResource realmResource() {
        return getInstance().realm(realm);
    }

    @Bean
    public UsersResource userResource() {
        return realmResource().users();
    }

    @Bean
    public ClientRepresentation getClientRepresentation() {
        return realmResource()
                .clients()
                .findByClientId("eshop_backend").get(0);
    }


}
