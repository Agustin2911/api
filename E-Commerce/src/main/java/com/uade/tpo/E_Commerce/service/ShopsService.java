package com.uade.tpo.E_Commerce.service;
import com.uade.tpo.E_Commerce.entity.Shop_Stock;
import com.uade.tpo.E_Commerce.entity.Shops;
import com.uade.tpo.E_Commerce.repository.Company_ShopsRepository;
import com.uade.tpo.E_Commerce.repository.Shop_StockRepository;
import com.uade.tpo.E_Commerce.repository.ShopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ShopsService implements ShopsImp {

    @Autowired
    private ShopsRepository repository;

    @Autowired
    private Company_ShopsRepository repository2;


    @Autowired
    private Shop_StockRepository repository3;

    @Override
    public Optional<Shops> SearchShop(Long id) {

        return repository.getShpById(id);


    }

    @Override
    public Optional<ArrayList<Shops>> getShopsById(long id_company) {
        return repository.getShopsByCompanyId(id_company);
    }

    @Override
    public Optional<Shops> CreateShop(String city, String street,long id_user) {

        Optional<Shops> shop=repository.getShpByCityAndStreet(city, street);
        if (!shop.isPresent()){

            repository.insertSHop(city, street);

            shop=repository.getShpByCityAndStreet(city, street);

            repository2.addShop(id_user,shop.get().getId_shop());

            return shop;
        }
        return  shop;
    }

    @Override
    public Optional<Shops> ModifyShop(long id_shop,String city, String street) {

        Optional<Shops> shop=repository.getShpById(id_shop);

        if(!shop.isPresent()){
            return Optional.empty();
        }

        repository.ModifySHop(id_shop,city,street);

        shop.get().setCity(city);
        shop.get().setStreet(street);

        return shop;
    }

    @Override
    public Boolean DeleteShop(long id_shop) {

        Optional<Shops> shop=repository.getShpById(id_shop);

        if(!shop.isPresent()){
            return true;
        }

        repository3.deleteShop_stock(id_shop);
        repository2.deleteShop(id_shop);
        repository.DeleteShop(id_shop);
        if (repository.getShpById(id_shop).isPresent()){
            return false;
        }
        else {
            return true;
        }

    }
}
