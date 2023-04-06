package com.example.eshop_backend.services;

import com.example.eshop_backend.Until;
import com.example.eshop_backend.model.Item;
import com.example.eshop_backend.repo.*;
import com.example.eshop_backend.request.PageReq;
import com.example.eshop_backend.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    @Autowired
    ItemRepo itemRepo;
    @Autowired
    keycloakService keycloakService;
    @Autowired
    private UserRepo userRepo;

    public Page<Item> getAllItems(PageReq pageReq) {
        return itemRepo.findAll(Until.createPageable(pageReq));

    }

    public Page<Item> searchItem(PageReq pageReq, String name) {
        return itemRepo.findByTitle(Until.createPageable(pageReq), name);

    }

    public void createUser(UserRequest user) {
        keycloakService.createAccount(user);
        userRepo.save(UserRequest.UserRequestToUser(user));
    }

}
