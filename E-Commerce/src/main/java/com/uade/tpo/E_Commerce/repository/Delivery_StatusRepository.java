package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Delivery_Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Delivery_StatusRepository extends JpaRepository<Delivery_Status, Long> {

    @Query(value = "SELECT * FROM Delivery_Status WHERE id_delivery = ?1", nativeQuery = true)
    Optional<Delivery_Status> findDeliveryStatusById(Long id_delivery);

    @Query(value = "SELECT * FROM Delivery_Status WHERE id_sale = ?1", nativeQuery = true)
    Delivery_Status findDeliveryStatusBySale(Long id_sale);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Delivery_Status (id_sale,delivery_type,address,delivery_status) VALUES" +
                   "(?1, ?2, ?3, ?4)", nativeQuery = true)
    int createNewDeliveryStatus(Long id_sale, String delivery_type, String address, String delivery_status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Delivery_Status SET id_sale = ?2, delivery_type = ?3, address = " +
            "?4, delivery_status = ?5 WHERE id_delivery = ?1", nativeQuery = true)
    int updateDeliveryStatus(Long id_delivery, Long id_sale, String delivery_type, String address,
                                         String delivery_status);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Delivery_Status WHERE id_delivery = ?1", nativeQuery = true)
    int deleteDeliveryStatus(Long id_delivery);

}
