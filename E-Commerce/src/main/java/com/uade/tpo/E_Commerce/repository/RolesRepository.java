package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

    @Query(value = "SELECT * FROM Roles", nativeQuery = true)
    Optional<List<Roles>> findAllRoles();

    @Query(value = "SELECT * FROM Roles WHERE id_role = ?1", nativeQuery = true)
    Optional<Roles> findRoleById(long idRole);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Roles(role_name) VALUES(?1)", nativeQuery = true)
    int insertRole(String roleName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Roles SET role_name = ?1 WHERE id_role = ?2", nativeQuery = true)
    int updateRole(String roleName, long idRole);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Roles WHERE id_role = ?1", nativeQuery = true)
    int deleteRole(long idRole);

    @Query(value = "SELECT * FROM Roles ORDER BY id_role DESC LIMIT 1", nativeQuery = true)
    Optional<Roles> findLatestRole();
}