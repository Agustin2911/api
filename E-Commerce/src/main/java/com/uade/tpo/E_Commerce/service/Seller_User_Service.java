package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Seller_User;
import java.util.List;
import java.util.Optional;

public interface Seller_User_Service {

    Seller_User createSellerUser(Seller_User sellerUser);

    Optional<Seller_User> findSellerUserById(int id);

    Optional<Seller_User> findSellerUserByCuit(int cuit);

    List<Seller_User> findAllSellerUsers();

    Seller_User updateSellerUser(int id, Seller_User sellerUser);

    void deleteSellerUser(int id);

    List<Seller_User> findSellerUsersByCompanyName(String companyName);

    List<Seller_User> findSellerUsersByCompanyNameContainingIgnoreCase(String companyName);

    List<Seller_User> findSellerUsersByState(String state);
}