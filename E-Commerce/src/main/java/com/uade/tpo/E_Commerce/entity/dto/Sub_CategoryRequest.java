package com.uade.tpo.E_Commerce.entity.dto;
import java.util.List;

import com.uade.tpo.E_Commerce.entity.Category;
import com.uade.tpo.E_Commerce.entity.Sub_categoryProduct;

import lombok.Data;

@Data
public class Sub_CategoryRequest {
    private Long id_sub_category;
    private Long id_category;
    private String name_sub_category;
    private List<Sub_categoryProduct> sub_categoryProduct;

    public Long getId_sub_category() {
        return id_sub_category;
    }

    public Long getId_category() {
        return id_category;
    }

    public String getName_sub_category() {
        return name_sub_category;
    }

    public List<Sub_categoryProduct> getSub_categoryProduct() {
        return sub_categoryProduct;
    }
}
