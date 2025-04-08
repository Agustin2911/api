package com.uade.tpo.E_Commerce.entity.dto;
import java.util.List;

import com.uade.tpo.E_Commerce.entity.Category;
import com.uade.tpo.E_Commerce.entity.Sub_categoryProduct;

import lombok.Data;

@Data
public class Sub_CategoryRequest {
    private Long id_sub_category;
    private Category category;
    private String name_sub_category;
    private List<Sub_categoryProduct> sub_categoryProduct;
}
