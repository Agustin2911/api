package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Buyer_User;
import com.uade.tpo.E_Commerce.repository.Buyer_User_Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerUserServiceImpl implements Buyer_User_Service {

    private final Buyer_User_Repository buyerUserRepository;

    public BuyerUserServiceImpl(Buyer_User_Repository buyerUserRepository) {
        this.buyerUserRepository = buyerUserRepository;
    }

    @Override
    public Buyer_User createBuyerUser(Buyer_User buyerUser) {
        // Aquí podrías agregar lógica de negocio adicional antes de guardar.
        return buyerUserRepository.save(buyerUser);
    }

    @Override
    public Optional<Buyer_User> findBuyerUserById(Long id) {
        return buyerUserRepository.findById(id);
    }

    @Override
    public Optional<Buyer_User> findBuyerUserByDni(int dni) {
        return buyerUserRepository.findByDni(dni);
    }

    @Override
    public List<Buyer_User> findAllBuyerUsers() {
        return buyerUserRepository.findAll();
    }

    @Override
    public Buyer_User updateBuyerUser(Long id, Buyer_User buyerUser) {
        Optional<Buyer_User> existingBuyerOptional = buyerUserRepository.findById(id);
        if (existingBuyerOptional.isPresent()) {
            buyerUser.setId_user(id); // Asegurar que el ID sea el correcto para la actualización
            return buyerUserRepository.save(buyerUser);
        }
        return null; // el wachin que compra no existe
    }

    @Override
    public void deleteBuyerUser(Long id) {
        buyerUserRepository.deleteById(id);
    }

    @Override
    public List<Buyer_User> findBuyerUsersByName(String name) {
        return buyerUserRepository.findByName(name);
    }

    @Override
    public List<Buyer_User> findBuyerUsersByLastName(String lastName) {
        return buyerUserRepository.findByLastName(lastName);
    }

    @Override
    public List<Buyer_User> findBuyerUsersByNameContainingIgnoreCase(String name) {
        return buyerUserRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Buyer_User> findBuyerUsersByLastNameContainingIgnoreCase(String lastName) {
        return buyerUserRepository.findByLastNameContainingIgnoreCase(lastName);
    }
}