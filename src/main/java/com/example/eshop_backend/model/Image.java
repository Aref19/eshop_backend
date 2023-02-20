package com.example.eshop_backend.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image {

    @Id
    private UUID id;
    private String url;

    @ManyToOne()
    @JoinColumn(name = "item_Id")
    private Item item;
}
