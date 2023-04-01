package com.example.eshop_backend.controller;


import com.example.eshop_backend.exception.AdminException;
import com.example.eshop_backend.model.Provider;
import com.example.eshop_backend.model.User;
import com.example.eshop_backend.request.AddressReq;
import com.example.eshop_backend.request.ProviderRequest;
import com.example.eshop_backend.services.AdminService;
import com.example.eshop_backend.services.keycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public void addProvider(@RequestBody ProviderRequest providerRequest) {
         keycloakService.createAccountProvider(providerRequest);
         adminService.saveProvider(providerRequest);
    }

    @DeleteMapping("/deleteProvider")
    public void deleteProvider(@RequestParam UUID uuid) {
        adminService.deleteProvider(uuid);
    }

    @GetMapping("/getProvider")
    public List<Provider> getProvider() {
        return adminService.getAllItems();
    }

}
