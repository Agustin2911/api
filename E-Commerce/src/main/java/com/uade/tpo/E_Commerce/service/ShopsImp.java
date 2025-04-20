package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Shops;

import java.util.ArrayList;
import java.util.Optional;

public interface ShopsImp {

    public Optional<Shops> SearchShop(Long id);

    public Optional<ArrayList<Shops>> getShopsById(long id_company);

    public Optional<Shops> CreateShop(String city ,String street,long id_user);

    public Optional<Shops> ModifyShop(long id_shop, String city , String street);

    public Boolean DeleteShop (long id_shop);
}
