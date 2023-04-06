package com.example.eshop_backend.services;


import com.example.eshop_backend.Until;
import com.example.eshop_backend.exception.NotFoundException;
import com.example.eshop_backend.model.Provider;
import com.example.eshop_backend.repo.AddressRepo;
import com.example.eshop_backend.repo.ProviderRepo;
import com.example.eshop_backend.request.PageReq;
import com.example.eshop_backend.request.ProviderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService {
    @Autowired
    ProviderRepo providerRepo;
    @Autowired
    AddressRepo addressRepo;
    @Autowired
    keycloakService keycloakService;

    public void saveProvider(ProviderRequest providerRequest) {
        Provider provider = ProviderRequest.providerRequestToProvider(providerRequest);
        keycloakService.createAccountProvider(providerRequest);
        providerRepo.save(provider);
    }

    public void deleteProvider(UUID providerId) {
        Optional<Provider> provider = providerRepo.findById(providerId);
        if (provider.isPresent()) {
            keycloakService.removeUser(provider.get().getEmail());
            providerRepo.deleteById(providerId);
        } else
            throw new NotFoundException("provider with id :" + providerId+" Not Found");
    }

    public Page<Provider> getAllItems(PageReq pageReq) {
        return providerRepo.findAll(Until.createPageable(pageReq));
    }
}
