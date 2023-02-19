package com.example.eshop_backend.controller;


import com.example.eshop_backend.request.ProviderRequest;
import com.example.eshop_backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('ROLE_admin')")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;


    @GetMapping("/test")
    public String test() {
        return "fromAdmin";
    }

    @PostMapping("/addProvider")
    public Boolean addProvider(ProviderRequest providerRequest){
      return adminService.saveProvider(providerRequest);
    }

}
