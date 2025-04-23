package com.uade.tpo.E_Commerce.service;
import com.uade.tpo.E_Commerce.entity.Shop_Stock;
import com.uade.tpo.E_Commerce.entity.Shops;
import com.uade.tpo.E_Commerce.repository.Company_ShopsRepository;
import com.uade.tpo.E_Commerce.repository.Seller_UserRepository;
import com.uade.tpo.E_Commerce.repository.Shop_StockRepository;
import com.uade.tpo.E_Commerce.repository.ShopsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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

    @Autowired
    private Seller_UserRepository repository4;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Shops> SearchShop(Long id) {

        return repository.getShpById(id);


    }

    @Override
    public Optional<ArrayList<Shops>> getShopsById(long id_company) {
        return repository.getShopsByCompanyId(id_company);
    }

    @Transactional
    @Override
    public Optional<Shops> CreateShop(String city, String street,long id_user) {

        if(!repository4.findByIdUser(id_user).isPresent()){
            return Optional.empty();
        }

        Optional<Shops> shop=repository.getShpByCityAndStreet(city, street);
        if (!shop.isPresent()){

            repository.insertSHop(city, street);

            shop=repository.getShpByCityAndStreet(city, street);

            repository2.addShop(id_user,shop.get().getId_shop());

            return shop;
        }
        return  shop;
    }

    @Transactional
    @Override
    public Optional<Shops> ModifyShop(long id_shop,String city, String street) {

        Optional<Shops> shop=repository.getShpById(id_shop);

        if(!shop.isPresent()){
            return Optional.empty();
        }

        repository.ModifySHop(id_shop,city,street);
        entityManager.flush();
        entityManager.clear();
        shop=repository.getShpById(id_shop);
        return shop;
    }

    @Transactional
    @Override
    public Boolean DeleteShop(long id_shop) {

        Optional<Shops> shop=repository.getShpById(id_shop);

        if(!shop.isPresent()){
            return false;
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
