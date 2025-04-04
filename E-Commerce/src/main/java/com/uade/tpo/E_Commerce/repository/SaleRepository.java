package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Sale;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long>{

    @Query(value = "SELECT * FROM Sale s WHERE s.id_user = ?1, s.sale_date = ?2 ")
    Sale findSaleByUserDate(Long id_user, LocalDateTime sale_date);

    @Query(value = "SELECT * FROM Sale s WHERE s.id_sale = ?1")
    Optional<Sale> findSaleById(Long id_sale);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Sale (total_price,id_user,sale_date) VALUES" +
            "(?1, ?2, ?3, ?4)")
    void createNewSale(int total_price, Long id_user, LocalDateTime sale_date);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Sale SET total_price = ?2, id_user = ?3, sale_date = " +
            "?4 WHERE id_sale = ?1")
    void updateSale(Long id_sale, int total_price, Long id_user, LocalDateTime sale_date);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Sale WHERE id_sale = ?1")
    void deleteSale(Long id_sale);


}
