package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Buyer_User;
import java.util.List;
import java.util.Optional;

public interface Buyer_User_Service {

    Buyer_User createBuyerUser(Buyer_User buyerUser);

    Optional<Buyer_User> findBuyerUserById(Long id);

    Optional<Buyer_User> findBuyerUserByDni(int dni);

    List<Buyer_User> findAllBuyerUsers();

    Buyer_User updateBuyerUser(Long id, Buyer_User buyerUser);

    void deleteBuyerUser(Long id);

    List<Buyer_User> findBuyerUsersByName(String name);

    List<Buyer_User> findBuyerUsersByLastName(String lastName);

    List<Buyer_User> findBuyerUsersByNameContainingIgnoreCase(String name);

    List<Buyer_User> findBuyerUsersByLastNameContainingIgnoreCase(String lastName);
}