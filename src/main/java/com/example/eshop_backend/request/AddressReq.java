package com.example.eshop_backend.request;


import com.example.eshop_backend.model.Address;
import com.example.eshop_backend.model.Provider;
import com.example.eshop_backend.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
public class AddressReq {
    private int id;
    private String plz;
    private String str;
    private String number;


    public static Set<Address>   addressRequestToAddress(List<AddressReq> pr) {
            Set<Address> addresses= pr.stream().map((addressRequest ->
                    new Address(
                            UUID.randomUUID(),
                            addressRequest.getPlz(),
                            addressRequest.getStr(),
                            addressRequest.getNumber()
                    )
            )).collect(Collectors.toSet());
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
