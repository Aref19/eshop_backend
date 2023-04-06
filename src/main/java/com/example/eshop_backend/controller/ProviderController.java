package com.example.eshop_backend.controller;


import com.example.eshop_backend.model.Item;
import com.example.eshop_backend.request.ItemRequest;
import com.example.eshop_backend.request.PageReq;
import com.example.eshop_backend.services.ProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public void addItems(@RequestBody List<ItemRequest> itemRequestList) {
        providerService.addItems(itemRequestList);
    }

//    @GetMapping("/items")
//    public Page<Item> getItems(@Valid PageReq pageReq) {
//        return providerService.getItems(pageReq);
//    }

}
