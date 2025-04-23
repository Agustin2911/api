package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Buyer_User;
import com.uade.tpo.E_Commerce.entity.Sale;
import com.uade.tpo.E_Commerce.entity.dto.newBuyer_User;
import com.uade.tpo.E_Commerce.repository.Basic_UserRepository;
import com.uade.tpo.E_Commerce.repository.Buyer_UserRepository;
import com.uade.tpo.E_Commerce.repository.RolesRepository;
import com.uade.tpo.E_Commerce.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Buyer_UserService implements Buyer_UserServiceImp {

    @Autowired
    private Buyer_UserRepository repository;

    @Autowired
    private Basic_UserRepository basic_userRepository;

    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private RolesService rolesService;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<List<Buyer_User>> getAll() {
        return repository.findAllUsers();
    }

    @Override
    public Optional<Buyer_User> getById(long id) {
        return repository.findByIdUser(id);
    }

    @Override
    @Transactional
    public Optional<Buyer_User> createUser(newBuyer_User user) {
        // Insertar Buyer_User
        repository.insertUser(user.getId_user(), user.getName(), user.getLastName(), user.getDni());
        // Retornar el último insertado
        return repository.findLatest();
    }

    @Override
    @Transactional
    public Optional<Buyer_User> updateUser(Buyer_User user) {
        Optional<Buyer_User> exist = repository.findByIdUser(user.getId_user());
        if (!exist.isPresent()) {
            return Optional.empty();
        }
        repository.updateUser(
                user.getName(),
                user.getLast_name(),
                user.getDni(),
                user.getId_user()
        );

        entityManager.flush();
        entityManager.clear();

        return repository.findByIdUser(user.getId_user());
    }

    @Override
    @Transactional
    public boolean deleteUser(long id) {
        Optional<Buyer_User> exist = repository.findByIdUser(id);
        if (!exist.isPresent()) {
            return false;
        }
        try {

            ArrayList<Sale> sale_list = saleRepository.findAllSalesByUser(id);
            for(Sale sale : sale_list){
                saleService.deleteSaleById(sale.getId_sale());
            }

            rolesService.removeRoleFromUser(id);
            repository.deleteUser(id);
            basic_userRepository.deleteUser(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}