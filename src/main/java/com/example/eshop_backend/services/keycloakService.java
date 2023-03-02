package com.example.eshop_backend.services;


import com.example.eshop_backend.config.KeycloakConfig;
import com.example.eshop_backend.config.rotrift.client.KeycloakClient;
import com.example.eshop_backend.model.User;
import com.example.eshop_backend.request.ProviderRequest;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import retrofit2.HttpException;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Service
public class keycloakService {
    @Autowired
    KeycloakConfig keycloakConfig;
    @Autowired
    KeycloakClient keycloakC;
    Logger logger = LoggerFactory.getLogger(keycloakService.class);

    public Object getToken(Map<String, String> userInfo) {
        try {
            var response = keycloakC.token(userInfo).execute();
            if (!response.isSuccessful() || response.body() == null) {
                throw new ResponseStatusException(HttpStatus.valueOf(response.code()), response.errorBody().string());
            }
            return response.body();
        } catch (HttpException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.code()), e.message());
        } catch (IOException exception) {
            logger.error("An ERROR Message" + exception);
        }
        return null;
    }

    public void createAccount(User user) {
        CredentialRepresentation password = encodePassword(user.getPassword());
        UsersResource userResource = keycloakConfig.userResource();
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getEmailId());
        userRepresentation.setEmail(user.getEmailId());
        userRepresentation.setFirstName(user.getEmailId());
        userRepresentation.setLastName(user.getEmailId());
        userRepresentation.setCredentials(Collections.singletonList(password));
        userRepresentation.setEnabled(true);
        Response response = userResource.create(userRepresentation);
        assignRole(user);
    }

    public void createAccountProvider(ProviderRequest providerRequest) {
        User user=providerRequest.getUser();
        user.setRole("provider");
        CredentialRepresentation password = encodePassword(user.getPassword());
        UsersResource userResource = keycloakConfig.userResource();
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getEmailId());
        userRepresentation.setEmail(user.getEmailId());
        userRepresentation.setFirstName(user.getEmailId());
        userRepresentation.setLastName(user.getEmailId());
        userRepresentation.setCredentials(Collections.singletonList(password));
        userRepresentation.setEnabled(true);
        Response response = userResource.create(userRepresentation);
        assignRole(user);
    }

    private void assignRole(User user) {
        ClientRepresentation clientRepresentation = keycloakConfig.getClientRepresentation();
        RoleRepresentation roleRepresentation = keycloakConfig.realmResource()
                .clients()
                .get(clientRepresentation.getId())
                .roles()
                .get(user.getRole())
                .toRepresentation();
         keycloakConfig.userResource().
                get(getUser(user)).
                roles().clientLevel(clientRepresentation.getId()).
                add(
                Collections.singletonList(roleRepresentation)
        );

    }

    private String getUser(User user) {
        return keycloakConfig.
                realmResource().
                users().
                search(user.getEmailId()
                ).
                get(0).
                getId();

    }

    private CredentialRepresentation encodePassword(String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(password);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setTemporary(false);
        return credentialRepresentation;
    }


}
