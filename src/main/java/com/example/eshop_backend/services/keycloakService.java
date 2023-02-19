package com.example.eshop_backend.services;


import com.example.eshop_backend.config.KeycloakConfig;
import com.example.eshop_backend.config.rotrift.client.KeycloakClient;
import com.example.eshop_backend.model.User;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
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
        Response response=userResource.create(userRepresentation);
    }

    private CredentialRepresentation encodePassword(String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(password);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setTemporary(false);
        return credentialRepresentation;
    }


}
