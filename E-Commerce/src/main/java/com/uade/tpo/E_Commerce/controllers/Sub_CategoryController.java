package com.uade.tpo.E_Commerce.controllers;

import java.net.URI;
import java.util.Optional;

import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.service.Sub_CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.E_Commerce.entity.Sub_Category;
import com.uade.tpo.E_Commerce.entity.dto.Sub_CategoryRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.micrometer.core.ipc.http.HttpSender;

@RestController
@RequestMapping("sub_categories")
public class Sub_CategoryController {

    @Autowired
    private Sub_CategoryServiceImpl sub_CategoryService;

    @GetMapping
    public ResponseEntity<Object> getSubCategories() {

        if(sub_CategoryService.getSubCategories(PageRequest.of(0, Integer.MAX_VALUE)).getContent().isEmpty()){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("There are no " +
                    "existing subcategories"));
        }

        return ResponseEntity.ok(sub_CategoryService.getSubCategories(PageRequest.of(0, Integer.MAX_VALUE)).getContent());
    }
    
    @GetMapping("/{id_sub_category}")
    public ResponseEntity<Object> getSubCategoryById(@PathVariable Long id_sub_category) {
        Optional<Sub_Category> result = sub_CategoryService.getSubCategoryById(id_sub_category);
        if (result.isPresent()){
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body("The Sub Category ID was not found.");
    }

    @GetMapping("/name/{name_sub_category}")
    public Long getIdByName(@PathVariable String name_sub_category) {
        Long result = sub_CategoryService.getIdByName(name_sub_category);
        if (result != null){
            return result;
        }
        return 0L;
    }

    @PostMapping
    public ResponseEntity<Object> createSubCategory(@RequestBody Sub_CategoryRequest sub_CategoryRequest){
        Sub_Category result = sub_CategoryService.createSubCategory(sub_CategoryRequest.getName_sub_category(), sub_CategoryRequest.getId_category());
        if (result == null){
            return ResponseEntity.badRequest().body("This Sub Category already exists.");
        } else {
            return ResponseEntity.created(URI.create("/sub_categories/" + result.getId_sub_category())).body(result);
        }
    }
    
    @DeleteMapping("/{id_sub_category}")
    public ResponseEntity<Object> deleteSubCategoryById(@PathVariable Long id_sub_category){ //   throws NotFoundException 
        boolean deleted = sub_CategoryService.deleteSubCategoryById(id_sub_category);
        if (deleted){
            return ResponseEntity.ok().body("The Sub Category was succesfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body("The Sub Category ID was not found, delete unsuccesful.");
        }
    } 

}
