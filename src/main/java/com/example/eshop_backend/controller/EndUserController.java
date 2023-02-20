package com.example.eshop_backend.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@PreAuthorize("hasRole('ROLE_USER')")
@RestController
@RequestMapping("/endUser")
public class EndUserController {

    @GetMapping("/test")
    public String test(){
        return "fromUser";
    }

    //TODO say items
    //TODO search item
    //TODO sort items
}
