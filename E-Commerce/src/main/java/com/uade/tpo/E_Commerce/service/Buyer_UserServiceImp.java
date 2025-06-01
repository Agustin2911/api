package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Buyer_User;
import com.uade.tpo.E_Commerce.entity.dto.newBuyer_User;

import java.util.List;
import java.util.Optional;

public interface Buyer_UserServiceImp {

    Optional<List<Buyer_User>> getAll();
    Optional<Buyer_User> getById(long id);
    Optional<Buyer_User> createUser(newBuyer_User user,String photo_url);
    Optional<Buyer_User> updateUser(Buyer_User user);
    boolean deleteUser(long id);
}