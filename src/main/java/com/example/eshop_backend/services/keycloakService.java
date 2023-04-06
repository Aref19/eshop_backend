package com.example.eshop_backend.services;


import com.example.eshop_backend.Until;
import com.example.eshop_backend.config.KeycloakConfig;
import com.example.eshop_backend.config.rotrift.client.KeycloakClient;
import com.example.eshop_backend.model.User;
import com.example.eshop_backend.request.ProviderRequest;
import com.example.eshop_backend.request.UserRequest;
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
    @Autowired
    UsersResource usersResource;



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

    public void createAccount(UserRequest user) {
        user.setRole("user");
        saveUserInKeycloak(createUserRepresentation(user));
        assignRole(UserRequest.UserRequestToUser(user));
    }

    public void createAccountProvider(ProviderRequest providerRequest) {
        User user = providerRequest.getUser();
        user.setRole("provider");
        saveUserInKeycloak(createUserRepresentation(user));
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

    private <T> UserRepresentation createUserRepresentation(T t) {
        UserRepresentation userRepresentation = new UserRepresentation();
        if(t instanceof User user){
            CredentialRepresentation password = encodePassword(user.getPassword());
            userRepresentation.setUsername(user.getEmailId());
            userRepresentation.setEmail(user.getEmailId());
            userRepresentation.setFirstName(user.getEmailId());
            userRepresentation.setLastName(user.getEmailId());
            userRepresentation.setCredentials(Collections.singletonList(password));
            userRepresentation.setEnabled(true);
        }
        return  userRepresentation;
    }

    private void saveUserInKeycloak(UserRepresentation userRepresentation){
        UsersResource userResource = keycloakConfig.userResource();
        userResource.create(userRepresentation);

    }

    public void removeUser(String email){
        usersResource.get(email).remove();
    }

}
