package com.example.eshop_backend.services;


import com.example.eshop_backend.exception.AdminException;
import com.example.eshop_backend.exception.GlobalExceptionHandler;
import com.example.eshop_backend.model.Image;
import com.example.eshop_backend.model.Item;
import com.example.eshop_backend.model.Provider;
import com.example.eshop_backend.repo.AddressRepo;
import com.example.eshop_backend.repo.ImageRepo;
import com.example.eshop_backend.repo.ItemRepo;
import com.example.eshop_backend.repo.ProviderRepo;
import com.example.eshop_backend.request.ImageRequest;
import com.example.eshop_backend.request.ItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProviderService {

    @Autowired
    ItemRepo itemRepo;
    @Autowired
    ProviderRepo providerRepo;
    @Autowired
    AddressRepo addressRepo;
    @Autowired
    GlobalExceptionHandler globalExceptionHandler;
    @Autowired
    ImageRepo imageRepo;

    public AdminException addItems(UUID providerId, List<ItemRequest> itemRequestList) {
        Optional<Provider> provider = providerRepo.findById(providerId);
        if (!provider.isPresent()) {
            return new AdminException(HttpStatus.NOT_FOUND.toString(), "Provider not Found", Instant.now().toString());
        }
        Set<Item> items = ItemRequest.ItemRequestToItem(itemRequestList);
        provider.get().setItemSet(items);
        List<Item> savedItems = itemRepo.saveAll(items);
        providerRepo.save(provider.get());
        List<Image> images = ImageRequest.ImageRequestToImage(itemRequestList,
                savedItems);
        imageRepo.saveAll(images);

        return new AdminException(HttpStatus.OK.toString(), "items success saved", Instant.now().toString());

    }

    public ResponseEntity<List> getItems(String nameProvider) {
        Optional<Provider> provider = providerRepo.findByName(nameProvider);
        if (provider.isPresent()) {

            return new ResponseEntity<List>(new ArrayList<>(provider.get().getItemSet()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }
}
