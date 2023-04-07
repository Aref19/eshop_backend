package com.example.eshop_backend.request;

import com.example.eshop_backend.model.Image;
import com.example.eshop_backend.model.Item;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UpdateItem {


    private UUID id;
    @Min(0)
    private String price;

    private String title;

    private String des;
    @Min(0)
    private int amount;

    private String specialPrice;

    private Set<MultipartFile> imageSet;


    public static Item updateItemToItem(UpdateItem updateItem) {
        Item item = new Item(
                updateItem.getId(),
                updateItem.getPrice(),
                updateItem.getTitle(),
                updateItem.getDes(),
                updateItem.getAmount(),
               ImageRequest.ImageRequestToImage( updateItem.getImageSet())
        );
        item.setSpecialPrice(updateItem.getSpecialPrice());
        return item;
    }
}
