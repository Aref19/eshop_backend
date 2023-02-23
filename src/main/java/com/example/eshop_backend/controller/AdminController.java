package com.example.eshop_backend.controller;


import com.example.eshop_backend.exception.AdminException;
import com.example.eshop_backend.model.Provider;
import com.example.eshop_backend.model.User;
import com.example.eshop_backend.request.ItemRequest;
import com.example.eshop_backend.request.ProviderRequest;
import com.example.eshop_backend.services.AdminService;
import com.example.eshop_backend.services.keycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@PreAuthorize("hasRole('ROLE_admin')")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    keycloakService keycloakService;


    @GetMapping("/test")
    public String test() {
        return "fromAdmin";
    }

    @PostMapping("/addProvider")
    public AdminException addProvider(@RequestBody ProviderRequest providerRequest) {
        return adminService.saveProvider(providerRequest);
    }

    @DeleteMapping("/deleteProvider")
    public AdminException deleteProvider(@RequestParam UUID uuid) {

        return adminService.deleteProvider(uuid);
    }

    @GetMapping("/getProvider")
    public List<Provider> getProvider() {
        return adminService.getAllItems();
    }

    @PostMapping("/createAccount")
    public void createAccount(@RequestBody User user) {
        user.setRole("provider");
        keycloakService.createAccount(user);
    }
}
