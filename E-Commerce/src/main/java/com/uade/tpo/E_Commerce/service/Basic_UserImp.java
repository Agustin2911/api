package com.uade.tpo.E_Commerce.service;
import java.util.List;
import java.util.Optional;

import com.uade.tpo.E_Commerce.entity.Basic_User;
import com.uade.tpo.E_Commerce.entity.dto.newBasic_user;

public interface Basic_UserImp {

    public Optional<List<Basic_User>> getAll();
    public Optional<Basic_User> getById(long id);
    public Optional<Basic_User> createUser(newBasic_user user);
    public Optional<Basic_User> updateUser( Basic_User user);
    public boolean deleteUser(long id);

}
