package com.uade.tpo.E_Commerce.entity.dto;

import java.util.List;

import com.uade.tpo.E_Commerce.entity.Sub_Category;

import lombok.Data;

@Data
public class CategoryRequest {
    private Long id_category;
    private String name_category;

    public CategoryRequest(Long id_category, String name_category) {

        this.id_category = id_category;
        this.name_category = name_category;
    }

    public Long getId_category() {
        return id_category;
    }

    public String getName_category() {
        return name_category;
    }
}
