package com.uade.tpo.E_Commerce.entity.dto;

import java.util.List;

import com.uade.tpo.E_Commerce.entity.Sub_Category;

import lombok.Data;

@Data
public class CategoryRequest {
    private Long id_category;
    private String name_category;
    private List<Sub_Category> sub_category;
}
