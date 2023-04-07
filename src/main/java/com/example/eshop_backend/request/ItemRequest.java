package com.example.eshop_backend.request;

import com.example.eshop_backend.model.Image;
import com.example.eshop_backend.model.Item;
import com.example.eshop_backend.model.Provider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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
    private Set<MultipartFile> image;


    public static Item ItemRequestToItem(ItemRequest itemRequestList) {
        return new Item(
                UUID.randomUUID(),
                itemRequestList.price,
                itemRequestList.title,
                itemRequestList.desc,
                itemRequestList.amount,
                ImageRequest.ImageRequestToImage(itemRequestList.image)
        );

    }

}
