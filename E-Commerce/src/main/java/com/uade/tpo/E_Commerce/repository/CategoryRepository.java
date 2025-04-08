package com.uade.tpo.E_Commerce.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.E_Commerce.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select c from Category c where c.name_category = ?1") // ?1 hace referencia al primer parametro pasado
    List<Category> findByName(String name_category);

    @Modifying
    @Query(value = "DELETE FROM Category c WHERE c.name_category = ?1")
    void deleteByName(String name_category);

}
