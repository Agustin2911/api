package com.uade.tpo.E_Commerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.E_Commerce.entity.Sub_Category;

import jakarta.transaction.Transactional;

@Repository
public interface Sub_CategoryRepository extends JpaRepository<Sub_Category, Long>{

    @Query(value = "select s from Sub_Category s where s.name_sub_category = ?1")
    List<Sub_Category> findSCByName(String name_sub_category);

    @Query (value = "SELECT s from Sub_Category s WHERE s.id_sub_category = ?1")
    Optional<Sub_Category> findSCById(Long id_sub_category);

    @Modifying
    @Transactional
    @Query(value = "delete from Sub_Category s where s.id_sub_category = ?1")
    int deleteSCById(Long id_sub_category);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Sub_Category (id_category, name_sub_category) VALUES" +
    "(?1, ?2)", nativeQuery = true)
    int createNewSub_Category(Long id_category, String name_sub_category);

}
