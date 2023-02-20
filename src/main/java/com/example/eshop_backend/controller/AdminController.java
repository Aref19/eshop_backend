package com.example.eshop_backend.controller;


import com.example.eshop_backend.exception.AdminException;
import com.example.eshop_backend.model.Provider;
import com.example.eshop_backend.request.ItemRequest;
import com.example.eshop_backend.request.ProviderRequest;
import com.example.eshop_backend.services.AdminService;
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


    @GetMapping("/test")
    public String test() {
        return "fromAdmin";
    }

    @PostMapping("/addProvider")
    public AdminException addProvider(@RequestBody  ProviderRequest providerRequest){
      return adminService.saveProvider(providerRequest);
    }

    @DeleteMapping("/deleteProvider")
    public AdminException addProvider(@RequestParam UUID uuid){

        return adminService.deleteProvider(uuid);
    }

    @PostMapping("/addItems")
    public AdminException addItems(@RequestParam UUID uuid, @RequestBody List<ItemRequest> itemRequestList){
        return adminService.addItems(uuid,itemRequestList);
    }

    @GetMapping("/getProvider")
    public List<Provider> getProvider(){
        return adminService.getAllItems();
    }

}
