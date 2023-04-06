package com.example.eshop_backend.controller;


import com.example.eshop_backend.model.Item;
import com.example.eshop_backend.request.PageReq;
import com.example.eshop_backend.request.UserRequest;
import com.example.eshop_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@PreAuthorize("hasRole('ROLE_USER')")
@RestController
@RequestMapping("/endUser")
public class EndUserController {

    @Autowired
    UserService userService;


    @GetMapping("/test")
    public String test(){
        return "fromUser";
    }

    @GetMapping("/getAllItem")
    public Page<Item> getAllItems(@RequestParam PageReq pageReq){
        return userService.getAllItems(pageReq);
    }

    @GetMapping("/searchItem")
    public Page<Item> searchItem(@RequestParam PageReq pageReq, @RequestParam String name){
        return userService.searchItem(pageReq,name);
    }

    @PostMapping("/createUserAccount")
    public void createAccount(@RequestBody UserRequest user) {
        userService.createUser(user);
    }
}
