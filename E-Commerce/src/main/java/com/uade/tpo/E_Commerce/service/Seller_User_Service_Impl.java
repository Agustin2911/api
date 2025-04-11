package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Seller_User;
import com.uade.tpo.E_Commerce.repository.Seller_User_Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Seller_User_Service_Impl implements Seller_User_Service {

    private final Seller_User_Repository sellerUserRepository;

    public Seller_User_Service_Impl(Seller_User_Repository sellerUserRepository) {
        this.sellerUserRepository = sellerUserRepository;
    }

    @Override
    public Seller_User createSellerUser(Seller_User sellerUser) {
        return sellerUserRepository.save(sellerUser);
    }

    @Override
    public Optional<Seller_User> findSellerUserById(int id) {
        return sellerUserRepository.findById(id);
    }

    @Override
    public Optional<Seller_User> findSellerUserByCuit(int cuit) {
        return sellerUserRepository.findByCuit(cuit);
    }

    @Override
    public List<Seller_User> findAllSellerUsers() {
        return sellerUserRepository.findAll();
    }

    @Override
    public Seller_User updateSellerUser(int id, Seller_User sellerUser) {
        Optional<Seller_User> existingSellerOptional = sellerUserRepository.findById(id);
        if (existingSellerOptional.isPresent()) {
            sellerUser.setId_user(id); // Asegurar que el ID sea el correcto para la actualización
            return sellerUserRepository.save(sellerUser);
        }
        return null; //el wachin que vende no existe
    }

    @Override
    public void deleteSellerUser(int id) {
        sellerUserRepository.deleteById(id);
    }

    @Override
    public List<Seller_User> findSellerUsersByCompanyName(String companyName) {
        return sellerUserRepository.findByCompanyName(companyName);
    }

    @Override
    public List<Seller_User> findSellerUsersByCompanyNameContainingIgnoreCase(String companyName) {
        return sellerUserRepository.findByCompanyNameContainingIgnoreCase(companyName);
    }

    @Override
    public List<Seller_User> findSellerUsersByState(String state) {
        return sellerUserRepository.findByState(state);
    }
}