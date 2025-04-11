package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Buyer_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Buyer_User_Repository extends JpaRepository<Buyer_User, Long> {

    Optional<Buyer_User> findByDni(int dni);

    List<Buyer_User> findByName(String name);

    List<Buyer_User> findByLastName(String lastName);

    List<Buyer_User> findByNameContainingIgnoreCase(String name);

    List<Buyer_User> findByLastNameContainingIgnoreCase(String lastName);

    // agregar metodos
}