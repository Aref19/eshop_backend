package com.example.eshop_backend.controller;


import com.example.eshop_backend.model.Item;
import com.example.eshop_backend.services.UserService;
import com.example.eshop_backend.services.keycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//@PreAuthorize("hasRole('ROLE_USER')")
@RestController
@RequestMapping("/endUser")
public class EndUserController {

    @Autowired
    UserService userService;
    @Autowired
    keycloakService keycloakService;

    @GetMapping("/test")
    public String test(){
        return "fromUser";
    }

    @GetMapping("/getAllItem")
    public List<Item> getAllItems(){

        return userService.getAllItems();
    }
    @GetMapping("/searchItem")
    public List<Item> searchItem(@RequestParam String name){
       return userService.searchItem(name);
    }

    @GetMapping("/sortItem")
    public List<Item> sortItem(@RequestParam String name){
        return userService.sortItem();
    }

}
