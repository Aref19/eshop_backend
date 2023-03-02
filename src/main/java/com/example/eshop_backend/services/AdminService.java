package com.example.eshop_backend.services;

import com.example.eshop_backend.exception.AdminException;
import com.example.eshop_backend.exception.GlobalExceptionHandler;
import com.example.eshop_backend.model.Address;
import com.example.eshop_backend.model.Provider;
import com.example.eshop_backend.repo.AddressRepo;
import com.example.eshop_backend.repo.ImageRepo;
import com.example.eshop_backend.repo.ItemRepo;
import com.example.eshop_backend.repo.ProviderRepo;
import com.example.eshop_backend.request.AddressReq;
import com.example.eshop_backend.request.ProviderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class AdminService {

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

    public AdminException saveProvider(ProviderRequest providerRequest) {

        Provider provider = ProviderRequest.providerRequestToProvider(providerRequest);
        List<Address> addresses = new ArrayList<>(AddressReq.addressRequestToAddress(providerRequest));
        addressRepo.saveAll(addresses);
        provider.setAddresses(new HashSet<>(addresses));
        providerRepo.save(provider);
        if (provider != null) {
            return new AdminException(HttpStatus.OK.toString(), "Provider is add", Instant.now().toString());
        }
        return new AdminException(HttpStatus.NOT_FOUND.toString(), "can not add Provider", Instant.now().toString());
    }

    public AdminException deleteProvider(UUID providerId) {
        Optional<Provider> provider = providerRepo.findById(providerId);
        if (provider.isPresent()) {
            providerRepo.delete(provider.get());
            return new AdminException(HttpStatus.NOT_FOUND.toString(), "Provider id deleted", Instant.now().toString());
        } else
            return new AdminException(HttpStatus.NOT_FOUND.toString(), "Provider not Found", Instant.now().toString());
    }


    public List<Provider> getAllItems() {
        return providerRepo.findAll();
    }



}
