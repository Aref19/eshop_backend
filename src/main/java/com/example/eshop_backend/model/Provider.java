package com.example.eshop_backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Provider {
    @NonNull
    @Id
    private UUID id ;
    @NonNull
    private String name;
    @NonNull
    private String webLink;


    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Address> addresses;
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Item> itemSet;



}
