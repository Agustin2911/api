package com.uade.tpo.E_Commerce.repository;
import com.uade.tpo.E_Commerce.entity.Items;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ItemsRepository extends JpaRepository<Items, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Items (id_product,id_sale,amount) VALUES" +
            "(?1, ?2, ?3)", nativeQuery = true)
    int createNewItem(Long id_product, Long id_sale, int amount);



}
