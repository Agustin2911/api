package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.User_Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRolesRepository extends JpaRepository<User_Roles, Long> {

    @Query(value = "SELECT * FROM User_Roles WHERE id_user = ?1", nativeQuery = true)
    Optional<User_Roles> findByUserId(long idUser);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO User_Roles (id_user, id_role) VALUES (?1, ?2)", nativeQuery = true)
    int assignRoleToUser(long idUser, long idRole);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM User_Roles WHERE id_user = ?1", nativeQuery = true)
    int removeRoleFromUser(long idUser);
}