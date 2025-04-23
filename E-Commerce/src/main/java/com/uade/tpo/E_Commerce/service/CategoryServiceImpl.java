package com.uade.tpo.E_Commerce.service;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.E_Commerce.entity.Category;
import com.uade.tpo.E_Commerce.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

   
    public Page<Category> getCategories(PageRequest pageable){
        return categoryRepository.findAll(pageable);
    }

    
    public Optional<Category> getCategoryById(Long id_category){
        return categoryRepository.findById(id_category);
    }

    @Transactional
    public Category createCategory(String name_category) { //throws DuplicateException 
        List<Category> categories = categoryRepository.findCByName(name_category);
        if (categories.isEmpty()){
            int check = categoryRepository.createNewCategory(name_category);
            if (check > 0){
                List<Category> categories_check = categoryRepository.findCByName(name_category);
                if(!categories_check.isEmpty()){
                    Category category = categories_check.get(0);
                    return category;
                } else {
                    return null;
                }
            } else { 
                return null;
            }
        } else {
            return null; 
            //throw new CategoryDuplicateException();
        }
    }

    @Transactional
    public boolean deleteCategoryById (Long id_category) { //throws NotFoundException  
        int check = categoryRepository.deleteCById(id_category);
        if (check > 0){
            Optional<Category> check_category = categoryRepository.findCById(id_category);
            return check_category.isEmpty();
        } else {
            return false;
        }
    }

}