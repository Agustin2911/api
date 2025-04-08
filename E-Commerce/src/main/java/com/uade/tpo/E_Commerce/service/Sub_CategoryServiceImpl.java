package com.uade.tpo.E_Commerce.service;

import java.util.List;
import java.util.Optional;

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


    //public Sub_Category createSubCategory(String name_sub_category) { //throws DuplicateException
        //List <Sub_Category> sub_categories = sub_CategoryRepository.findByName(name_sub_category);
       // if (sub_categories.isEmpty()){
       //     return sub_CategoryRepository.save(new Sub_Category(name_sub_category));
       // } else {
          //  return;
             //throw new CategoryDuplicateException();
  //      }
  //  }

    public void deleteSubCategory(String name_sub_category) {
        List <Sub_Category> sub_categories = sub_CategoryRepository.findByName(name_sub_category);
        if (sub_categories.isEmpty()){
            return;
            //throw new CategoryNotFoundException();
        }
        return;
    }
}
