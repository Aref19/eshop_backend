package com.example.eshop_backend.controller;


import com.example.eshop_backend.model.Item;
import com.example.eshop_backend.request.ItemRequest;
import com.example.eshop_backend.request.PageReq;
import com.example.eshop_backend.request.ProviderRequest;
import com.example.eshop_backend.request.UpdateItem;
import com.example.eshop_backend.services.ProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public void addItems( ItemRequest itemRequestList) {
        providerService.addItems(itemRequestList);
    }

    @GetMapping("/items")
    public Page<Item> getItems(@Valid PageReq pageReq) {
        return providerService.getItems(pageReq);
    }

    @PutMapping("/updateItem")
    public void updateItem(@Valid @RequestBody UpdateItem updateItem) {
        providerService.updateItem(updateItem);
    }

    @DeleteMapping("/deleteItem")
    public void deleteItem(@RequestParam UUID uuid) {
        providerService.deleteItem(uuid);
    }

    @PutMapping("/updateProvider")
    public void updateProvider(@RequestParam UUID id, @RequestBody ProviderRequest providerRequest){
        providerService.updateProvider(id,providerRequest);
    }

    @DeleteMapping("/deleteAddress")
    public void deleteAddress(@RequestParam UUID uuid) {
        providerService.deleteAddress(uuid);
    }

}
