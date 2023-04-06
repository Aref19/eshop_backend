package com.example.eshop_backend.controller;



import com.example.eshop_backend.model.Provider;
import com.example.eshop_backend.request.PageReq;
import com.example.eshop_backend.request.ProviderRequest;
import com.example.eshop_backend.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_admin')")
public class AdminController {

    @Autowired
    AdminService adminService;



    @GetMapping("/test")
    public String test() {
        return "fromAdmin";
    }

    @PostMapping("/addProvider")
    public void addProvider(@RequestBody ProviderRequest providerRequest) {
         adminService.saveProvider(providerRequest);
    }

    @DeleteMapping("/deleteProvider")
    public void deleteProvider(@RequestParam UUID uuid) {
        adminService.deleteProvider(uuid);
    }

    @GetMapping("/getProvider")
    public Page<Provider> getProvider(@Valid PageReq pageReq) {
        return adminService.getAllItems(pageReq);
    }

}
