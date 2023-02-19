package com.example.eshop_backend.services;

import com.example.eshop_backend.model.Address;
import com.example.eshop_backend.model.Provider;
import com.example.eshop_backend.repo.AddressRepo;
import com.example.eshop_backend.repo.ItemRepo;
import com.example.eshop_backend.repo.ProviderRepo;
import com.example.eshop_backend.request.AddressRequest;
import com.example.eshop_backend.request.ProviderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminService {

    @Autowired
    ItemRepo itemRepo;
    @Autowired
    ProviderRepo providerRepo;
    @Autowired
    AddressRepo addressRepo;

    public Boolean saveProvider(ProviderRequest providerRequest) {
        Provider provider = providerRepo.save(ProviderRequest.providerRequestToProvider(providerRequest));
        if (provider != null) {
            return true;
        }
        return false;
    }

}
