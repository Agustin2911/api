package com.uade.tpo.E_Commerce.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.E_Commerce.entity.Category;

public interface CategoryService {

    public Page<Category> getCategories (PageRequest pageRequest); //Get de todas las categorias

    public Optional<Category> getCategoryById (Long id_category); //Get de una sola categoria por id

    public Category createCategory (String name_category); // Post de una categoria

    public boolean deleteCategoryById (Long id_category); // Delete de una categoria
}
