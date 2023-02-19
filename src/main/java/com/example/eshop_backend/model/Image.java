package com.example.eshop_backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Image {

    @Id
    private UUID id;
    private String url;

    @ManyToOne
    @JoinColumn(name = "item_Id")
    private Item item;
}
