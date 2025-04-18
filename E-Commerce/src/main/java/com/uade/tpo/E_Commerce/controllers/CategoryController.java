package com.uade.tpo.E_Commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.E_Commerce.entity.Category;
import com.uade.tpo.E_Commerce.entity.dto.CategoryRequest;
import com.uade.tpo.E_Commerce.service.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> getCategories(
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size) {
        
        if (page == null || size == null){
            return ResponseEntity.ok(categoryService.getCategories(PageRequest.of(0, Integer.MAX_VALUE)));
        }
        return ResponseEntity.ok(categoryService.getCategories(PageRequest.of(page, size)));
    }

    @GetMapping("/{id_category}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id_category) {
        Optional<Category> result = categoryService.getCategoryById(id_category);
        if (result.isPresent()){
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest categoryRequest) { 
        Category result = categoryService.createCategory(categoryRequest.getName_category());
        return ResponseEntity.created(URI.create("/categories/" + result.getId_category())).body(result);
        }

    @DeleteMapping("/{id_category}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id_category) {
        boolean deleted = categoryService.deleteCategoryById(id_category);
        if (deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
        } 
}
