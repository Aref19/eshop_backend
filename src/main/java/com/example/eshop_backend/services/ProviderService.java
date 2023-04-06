package com.example.eshop_backend.services;



import com.example.eshop_backend.Until;
import com.example.eshop_backend.exception.NotFoundException;
import com.example.eshop_backend.model.Item;
import com.example.eshop_backend.model.Provider;
import com.example.eshop_backend.repo.ImageRepo;
import com.example.eshop_backend.repo.ItemRepo;
import com.example.eshop_backend.repo.ProviderRepo;
import com.example.eshop_backend.request.ItemRequest;
import com.example.eshop_backend.request.PageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
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
    ImageRepo imageRepo;

    public void addItems( List<ItemRequest> itemRequestList) {
       String email= Until.emailProvider();
        Optional<Provider> provider = providerRepo.findByEmail(email);
        if (provider.isEmpty()) {
            throw new NotFoundException("Porvider Not Founded ");
        }
        Set<Item> items = ItemRequest.ItemRequestToItem(itemRequestList);
        provider.get().setItemSet(items);
        providerRepo.save(provider.get());
    }
//
//    public Page<Item> getItems(PageReq pageReq) {
//        String email = Until.emailProvider();
//        Optional<Provider> provider = providerRepo.findByEmail(email);
////        if(provider.isPresent()){
////         //   return providerRepo.(provider.get().getId(),Until.createPageable(pageReq));
////        }else
////
////    }
// throw new NotFoundException("Porvider Not Founded ");
}
