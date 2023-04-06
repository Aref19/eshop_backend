package com.example.eshop_backend.request;

import com.example.eshop_backend.model.Image;
import com.example.eshop_backend.model.Item;
import com.example.eshop_backend.model.Provider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class ItemRequest {
    private String price;
    private String title;
    private String desc;
    private int amount;
    private Set<ImageRequest> image;


    public static Set<Item> ItemRequestToItem(List<ItemRequest> itemRequestList) {
        return itemRequestList.stream().map(itemRequest ->
                new Item(
                        UUID.randomUUID(),
                        itemRequest.price,
                        itemRequest.title,
                        itemRequest.desc,
                        itemRequest.amount,
                        ImageRequest.ImageRequestToImage(itemRequest.image)
                )
        ).collect(Collectors.toSet());
    }

}
