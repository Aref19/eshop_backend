package com.example.eshop_backend.controller;


import com.example.eshop_backend.exception.AdminException;
import com.example.eshop_backend.request.ItemRequest;
import com.example.eshop_backend.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@PreAuthorize("hasRole('ROLE_provider')")
@RestController
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    ProviderService providerService;


    @GetMapping("/test")
    public String test() {

        return "fromProvider";
    }

    @PostMapping("/addItems")
    public AdminException addItems(@RequestBody List<ItemRequest> itemRequestList) {
        return providerService.addItems( itemRequestList);
    }

    @GetMapping("/items")
    public ResponseEntity<List> getItems() {
        return providerService.getItems();
    }

}
