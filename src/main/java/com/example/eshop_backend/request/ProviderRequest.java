package com.example.eshop_backend.request;


import com.example.eshop_backend.model.Address;
import com.example.eshop_backend.model.Provider;
import com.example.eshop_backend.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Transient;
import lombok.*;


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



    public static Provider providerRequestToProvider(ProviderRequest providerRequest) {
        Provider provider= new Provider(
                UUID.randomUUID(),
                providerRequest.getNameReq(),
                providerRequest.getWebLinkReq(),
                providerRequest.user.getEmailId()
        );
        Set<Address> address= AddressReq.addressRequestToAddress(providerRequest.getAddress());
        provider.setAddresses(address);
        return provider;
    }
}
