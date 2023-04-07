package com.example.eshop_backend.response;

import com.example.eshop_backend.model.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProviderResponse {

    private String name;
    private Set<Item> itemSet;

}
