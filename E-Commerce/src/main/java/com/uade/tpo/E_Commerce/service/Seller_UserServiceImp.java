package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Seller_User;
import com.uade.tpo.E_Commerce.entity.dto.newSeller_User;

import java.util.List;
import java.util.Optional;

public interface Seller_UserServiceImp {

    Optional<List<Seller_User>> getAll();
    Optional<Seller_User> getById(long id);
    Optional<Seller_User> createUser(newSeller_User user);
    Optional<Seller_User> updateUser(Seller_User user);
    boolean deleteUser(long id);
}