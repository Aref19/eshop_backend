package com.example.eshop_backend.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.domain.Sort;



@Getter
@Setter
public class PageReq {
    @Min(0)
    private int page;
    @Min(0)
    private int size;
    @JsonIgnore
    private Sort sort = Sort.by("name");

    /**
     * @param sortColum will change sort type from name to
     * @return
     */
    public PageReq changeSort(String sortColum) {
        this.sort = Sort.by(sortColum);
        return this;
    }


}
