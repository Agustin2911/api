package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Sale;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long>{

    @Query(value = "SELECT * FROM Sale WHERE id_user = ?1 AND sale_date = ?2 ", nativeQuery = true)
    Sale findSaleByUserDate(Long id_user, LocalDateTime sale_date);

    @Query(value = "SELECT * FROM Sale WHERE id_sale = ?1", nativeQuery = true)
    Optional<Sale> findSaleById(Long id_sale);

    @Query(value = "SELECT * FROM sale WHERE id_user = ?1", nativeQuery = true)
    ArrayList<Sale> findAllSalesByUser(Long id_user);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Sale (total_price,id_user,sale_date) VALUES" +
            "(?1, ?2, ?3)", nativeQuery = true)
    int createNewSale(int total_price, Long id_user, LocalDateTime sale_date);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Sale SET total_price = ?2, id_user = ?3, sale_date = " +
            "?4 WHERE id_sale = ?1", nativeQuery = true)
    int updateSale(Long id_sale, int total_price, Long id_user, LocalDateTime sale_date);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Sale WHERE id_sale = ?1", nativeQuery = true)
    int deleteSale(Long id_sale);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Sale WHERE id_user = ?1", nativeQuery = true)
    int deleteSaleByUserId(Long id_user);


}
