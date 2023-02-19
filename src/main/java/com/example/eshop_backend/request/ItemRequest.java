package com.example.eshop_backend.request;

import com.example.eshop_backend.model.Image;
import com.example.eshop_backend.model.Provider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class ItemRequest {
    private String price;
    private String title;
    private String desc;


    private Set<Image> image;
    private Provider provider;

}
