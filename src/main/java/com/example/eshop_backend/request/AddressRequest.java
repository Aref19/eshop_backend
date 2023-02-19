package com.example.eshop_backend.request;


import com.example.eshop_backend.model.Address;
import com.example.eshop_backend.model.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressRequest {
    private String plz;
    private String str;
    private String number;
    private Provider provider;

    public static Set<Address> addressRequestToAddress(ProviderRequest pr) {
        return pr.getAddressReq().stream().map((addressRequest ->
             new Address(
                    UUID.randomUUID(),
                    addressRequest.getPlz(),
                    addressRequest.getStr(),
                    addressRequest.getNumber(),
                    addressRequest.getProvider()
            )
        )).collect(Collectors.toSet());
    }
}
