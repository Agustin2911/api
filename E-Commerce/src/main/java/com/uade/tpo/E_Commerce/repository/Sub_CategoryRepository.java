package com.uade.tpo.E_Commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.E_Commerce.entity.Sub_Category;

@Repository
public interface Sub_CategoryRepository extends JpaRepository<Sub_Category, Long>{

    @Query(value = "select s from Sub_Category s where s.name_sub_category = ?1")
    List<Sub_Category> findByName(String name_sub_category);

    @Modifying
    @Query(value = "delete from Sub_Category s where s.name_sub_category = ?1")
    Sub_Category deleteByName(String name_sub_category);
    
}
