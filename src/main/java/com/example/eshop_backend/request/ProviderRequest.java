package com.example.eshop_backend.request;


import com.example.eshop_backend.model.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;
import java.util.UUID;



@Getter
@Setter
@AllArgsConstructor
public class ProviderRequest {
    private String nameReq;
    private String webLinkReq;
    private Set<AddressRequest> AddressReq;


    public static Provider providerRequestToProvider(ProviderRequest providerRequest) {
        Provider provider= new Provider(
                UUID.randomUUID(),
                providerRequest.getNameReq(),
                providerRequest.getWebLinkReq()
        );

        return provider;
    }
}
