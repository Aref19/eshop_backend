package com.example.eshop_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.jboss.resteasy.annotations.providers.jaxb.IgnoreMediaTypes;

import java.util.Set;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Provider {
    @Id
    private UUID id ;
    private String name;
    private String webLink;
    @OneToMany(mappedBy = "provider")
    private Set<Address> Address;



    public Provider(UUID id, String name, String webLink, Set<com.example.eshop_backend.model.Address> address) {
        this.id = id;
        this.name = name;
        this.webLink = webLink;
        Address = address;
    }
}
