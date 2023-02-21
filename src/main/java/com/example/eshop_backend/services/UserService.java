package com.example.eshop_backend.services;

import com.example.eshop_backend.model.Item;
import com.example.eshop_backend.repo.AddressRepo;
import com.example.eshop_backend.repo.ImageRepo;
import com.example.eshop_backend.repo.ItemRepo;
import com.example.eshop_backend.repo.ProviderRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    ProviderRepo providerRepo;
    @Autowired
    AddressRepo addressRepo;
    @Autowired
    ImageRepo imageRepo;


    public List<Item> getAllItems() {
        List<Item> items=itemRepo.findAll();
        return items;
    }

    public List<Item> searchItem(String name) {
        List<Item> items = itemRepo.findByTitle(name);
        if (items.size() > 0) {
            return items;
        }
        return new ArrayList<>();
    }

    public List<Item> sortItem() {
        List<Item> items = itemRepo.findAll();
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        return items;
    }

}
