package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Buyer_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface Buyer_UserRepository extends JpaRepository<Buyer_User, Long> {

    @Query(value = "SELECT * FROM Buyer_User", nativeQuery = true)
    Optional<List<Buyer_User>> findAllUsers();

    @Query(value = "SELECT * FROM Buyer_User WHERE id_user = ?1", nativeQuery = true)
    Optional<Buyer_User> findByIdUser(long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Buyer_User(id_user,name, last_name, dni) VALUES (?1, ?2, ?3,?4)", nativeQuery = true)
    int insertUser(long id_user,String name, String lastName, int dni,String photo_url);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Buyer_User SET name = ?1, last_name = ?2, dni = ?3 WHERE id_user = ?4", nativeQuery = true)
    int updateUser(String name, String lastName, int dni, long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Buyer_User WHERE id_user = ?1", nativeQuery = true)
    int deleteUser(long id);

    @Query(value = "SELECT * FROM Buyer_User ORDER BY id_user DESC LIMIT 1", nativeQuery = true)
    Optional<Buyer_User> findLatest();
}