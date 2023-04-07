package com.example.eshop_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item   {
    @NonNull
    @Id
    private UUID id ;
    @NonNull
    private String price;
    @NonNull
    private String title;
    @NonNull
    private String des;
    @NonNull
    private int amount;


    private String specialPrice;



    @OneToMany(cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @NonNull
    @JoinColumn(name = "item_Id")
    private Set<Image> image;



}
