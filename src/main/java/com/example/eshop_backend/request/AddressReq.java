package com.example.eshop_backend.request;


import com.example.eshop_backend.model.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
public class AddressReq {
    private String plz;
    private String str;
    private String number;


    public static Set<Address> addressRequestToAddress(ProviderRequest pr) {
        Set<Address> addresses= pr.getAddress().stream().map((addressRequest ->
             new Address(
                    UUID.randomUUID(),
                    addressRequest.getPlz(),
                    addressRequest.getStr(),
                    addressRequest.getNumber()

            )
        )).collect(Collectors.toSet());
        System.out.println(addresses);
        return addresses;
    }

    public String getPlz() {
        return plz;
    }

    public String getStr() {
        return str;
    }

    public String getNumber() {
        return number;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
