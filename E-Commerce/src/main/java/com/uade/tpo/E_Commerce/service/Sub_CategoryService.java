package com.uade.tpo.E_Commerce.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.E_Commerce.entity.Sub_Category;

public interface Sub_CategoryService {

    public Page<Sub_Category> getSubCategories (PageRequest pageRequest); // Get de todas las sub categorias existentes

    public Optional<Sub_Category> getSubCategoryById (Long id_sub_category); //Get de una sub categoria por id

    public Sub_Category createSubCategory (String name_sub_category, Long id_category); // Post de una sub categoria

    public boolean deleteSubCategoryById (Long id_sub_category); // Delete de una sub categoria

}
