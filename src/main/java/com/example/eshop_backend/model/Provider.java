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
    @JsonIgnore
    private UUID id;
    @NonNull
    private String name;
    @NonNull
    private String webLink;
    @NonNull
    private String email;


    @OneToMany(mappedBy = "provider",cascade = {CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
    private Set<Address> addresses;
    @JsonIgnore
    @OneToMany(mappedBy = "provider",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private Set<Item> itemSet;

}
