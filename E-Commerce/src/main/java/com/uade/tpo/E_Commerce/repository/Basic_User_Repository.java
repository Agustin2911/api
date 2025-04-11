package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Basic_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Basic_User_Repository extends JpaRepository<Basic_User, Long> { // Assuming Long is the type of id_user
    Optional<Basic_User> findByUsername(String username);
}