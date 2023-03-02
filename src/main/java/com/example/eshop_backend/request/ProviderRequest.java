package com.example.eshop_backend.request;


import com.example.eshop_backend.model.Provider;
import com.example.eshop_backend.model.User;
import lombok.*;


import java.util.List;
import java.util.UUID;



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

        return provider;
    }
}
