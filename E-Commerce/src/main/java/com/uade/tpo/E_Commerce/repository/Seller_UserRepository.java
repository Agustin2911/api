package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Seller_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface Seller_UserRepository extends JpaRepository<Seller_User, Long> {

    @Query(value = "SELECT * FROM Seller_User", nativeQuery = true)
    Optional<List<Seller_User>> findAllUsers();

    @Query(value = "SELECT * FROM Seller_User WHERE id_user = ?1", nativeQuery = true)
    Optional<Seller_User> findByIdUser(long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Seller_User(id_user, cuit, company_name, description, state,photo_url) VALUES (?1, ?2, ?3, ?4, ?5,?6)", nativeQuery = true)
    int insertUser(long id_user, int cuit, String companyName, String description, String state,String photo_url);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Seller_User SET cuit = ?2, company_name = ?3, description = ?4, state = ?5 WHERE id_user = ?1", nativeQuery = true)
    int updateUser(long id, int cuit, String companyName, String description, String state);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Seller_User WHERE id_user = ?1", nativeQuery = true)
    int deleteUser(long id);

    @Query(value = "SELECT * FROM Seller_User ORDER BY id_user DESC LIMIT 1", nativeQuery = true)
    Optional<Seller_User> findLatest();
}
