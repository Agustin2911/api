package com.uade.tpo.E_Commerce.service;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.E_Commerce.entity.Sub_Category;
import com.uade.tpo.E_Commerce.repository.Sub_CategoryRepository;

@Service
public class Sub_CategoryServiceImpl implements Sub_CategoryService{

    @Autowired
    private Sub_CategoryRepository sub_CategoryRepository;


    public Page<Sub_Category> getSubCategories(PageRequest pageable){
        return sub_CategoryRepository.findAll(pageable);
    }

    public Optional<Sub_Category> getSubCategoryById(Long id_sub_category){
        return sub_CategoryRepository.findById(id_sub_category);
    }

    @Transactional
    public Sub_Category createSubCategory(String name_sub_category, Long id_category) { //throws DuplicateException
        List <Sub_Category> sub_categories = sub_CategoryRepository.findSCByName(name_sub_category);
        if (sub_categories.isEmpty()){
            int check = sub_CategoryRepository.createNewSub_Category(id_category, name_sub_category);
            if (check > 0){
                List <Sub_Category> sub_categories_check = sub_CategoryRepository.findSCByName(name_sub_category);
                if (!sub_categories_check.isEmpty()){
                    Sub_Category sub_category = sub_categories_check.get(0);
                    return sub_category;
                } else {
                    return null;
                }
            } else{
                return null;
            }
        } else {
            return null;
             //throw new CategoryDuplicateException();
        }
    }

    @Transactional
    public boolean deleteSubCategoryById(Long id_sub_category) {
        int check = sub_CategoryRepository.deleteSCById(id_sub_category);
        if (check > 0) {
            Optional<Sub_Category> check_sub_category = sub_CategoryRepository.findSCById(id_sub_category);
            return check_sub_category.isEmpty();
        } else{
            return false;
        }
    }

}
