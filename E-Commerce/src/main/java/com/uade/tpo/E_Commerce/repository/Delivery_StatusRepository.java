package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Delivery_Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Delivery_StatusRepository extends JpaRepository<Delivery_Status, Long> {

    @Query(value = "SELECT d FROM Delivery_Status d WHERE d.id_delivery = ?1")
    Delivery_Status findDeliveryStatusById(Long id_delivery);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Delivery_Status (id_sale,delivery_type,address,delivery_status) VALUES" +
                   "(?1, ?2, ?3, ?4)")
    Delivery_Status createNewDeliveryStatus(Long id_sale, String delivery_type, String address, String delivery_status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Delivery_Status SET id_sale = ?2, delivery_type = ?3, address = " +
            "?4, delivery_status = ?5 WHERE id_delivery = ?1")
    Delivery_Status updateDeliveryStatus(Long id_delivery, Long id_sale, String delivery_type, String address,
                                         String delivery_status);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Delivery_Status WHERE id_delivery = ?1")
    boolean deleteDeliveryStatus(Long id_delivery);

}
