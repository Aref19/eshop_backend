package com.example.eshop_backend.request;


import com.example.eshop_backend.model.Address;
import com.example.eshop_backend.model.Provider;
import com.example.eshop_backend.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Transient;
import lombok.*;


import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderRequest {
    private String nameReq;
    private String webLinkReq;
    private List<AddressReq> address;
    private User user;

    private String logoUrl;


    public static Provider providerRequestToProvider(ProviderRequest providerRequest) {
        Provider provider = new Provider(
                UUID.randomUUID(),
                providerRequest.getNameReq(),
                providerRequest.getWebLinkReq(),
                providerRequest.user.getEmailId(),
                providerRequest.getLogoUrl()
        );

        Set<Address> address = AddressReq.addressRequestToAddress(providerRequest.getAddress());
        provider.setAddresses(address);
        return provider;
    }

    public static Provider mergeProviderRequestToProvider(ProviderRequest newProvider, Provider provider) {
        provider.setName((newProvider.getNameReq() == null) ? provider.getName() : newProvider.getNameReq());
        if (newProvider.getAddress() != null) {
            provider.getAddresses().addAll(AddressReq.addressRequestToAddress(newProvider.getAddress()));
            provider.setAddresses(provider.getAddresses());
        }
        provider.setWebLink((newProvider.getWebLinkReq() == null) ? provider.getWebLink() : newProvider.getWebLinkReq());
        provider.setLogoUrl((newProvider.getLogoUrl() == null) ? provider.getLogoUrl() : newProvider.getLogoUrl());
        return provider;
    }
}
