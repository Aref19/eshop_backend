package com.example.eshop_backend.controller;

import com.example.eshop_backend.model.User;
import com.example.eshop_backend.services.keycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    keycloakService keycloakService;


    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object getToken(@RequestParam Map<String, String> userInfo) {

        return keycloakService.getToken(userInfo);
    }

    @PostMapping("/createAccount")
    public void createAccount(@RequestBody User user) {
        user.setRole("user");
        keycloakService.createAccount(user);
    }

}
