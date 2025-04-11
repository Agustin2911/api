package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Seller_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Seller_User_Repository extends JpaRepository<Seller_User, Integer> {

    Optional<Seller_User> findByCuit(int cuit);

    List<Seller_User> findByCompanyName(String companyName);

    List<Seller_User> findByCompanyNameContainingIgnoreCase(String companyName);

    List<Seller_User> findByState(String state);

    // nose que otros metodos agregar
}