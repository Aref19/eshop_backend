package com.example.eshop_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
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
    private UUID id;
    @NonNull
    private String name;
    @NonNull
    private String webLink;
    @NonNull
    private String email;
     @NonNull
    private String logoUrl;

    @Getter(AccessLevel.NONE)
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Set<Address> addresses;

    @Getter(AccessLevel.NONE)
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REMOVE,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Set<Item> itemSet;


    public Set<Item> getItemSet() {
        return itemSet;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }
}
