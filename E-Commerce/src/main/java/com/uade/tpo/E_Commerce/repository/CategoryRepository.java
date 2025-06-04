package com.uade.tpo.E_Commerce.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.E_Commerce.entity.Category;

import jakarta.transaction.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select c from Category c where c.name_category = ?1") // ?1 hace referencia al primer parametro pasado
    List<Category> findCByName(String name_category);

    @Query(value = "select c from Category c where c.id_category = ?1")
    Optional<Category> findCById(Long id_category);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Category c WHERE c.id_category = ?1")
    int deleteCById(Long id_category);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Category (name_category) VALUES" + 
    "(?1)", nativeQuery = true)
    int createNewCategory(String name_category);

    @Query(value = "select c.id_category from category c where c.name_category = ?1", nativeQuery = true)
    Long findIdByName(String nameCategory);
}
