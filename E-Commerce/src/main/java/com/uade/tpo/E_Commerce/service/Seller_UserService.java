package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Seller_User;
import com.uade.tpo.E_Commerce.entity.dto.newSeller_User;
import com.uade.tpo.E_Commerce.repository.Seller_UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class Seller_UserService implements Seller_UserServiceImp {

    @Autowired
    private Seller_UserRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<Seller_User>> getAll() {
        return repository.findAllUsers();
    }

    @Override
    public Optional<Seller_User> getById(long id) {
        return repository.findByIdUser(id);
    }

    @Override
    @Transactional
    public Optional<Seller_User> createUser(newSeller_User user) {
        repository.insertUser(user.getId_user(), user.getCuit(), user.getCompanyName(), user.getDescription(), user.getState());
        // Se asume que el seller_user creado se identifica mediante el id_user que se pasa.
        return repository.findByIdUser(user.getId_user());
    }

    @Override
    @Transactional
    public Optional<Seller_User> updateUser(Seller_User user) {
        Optional<Seller_User> exist = repository.findByIdUser(user.getId());
        if (!exist.isPresent()) {
            return Optional.empty();
        }
        repository.updateUser(user.getId(), user.getCuit(), user.getCompany_name(), user.getDescription(), user.getState());
        entityManager.flush();
        return repository.findByIdUser(user.getId());
    }

    @Override
    @Transactional
    public boolean deleteUser(long id) {
        Optional<Seller_User> exist = repository.findByIdUser(id);
        if (!exist.isPresent()) {
            return false;
        }
        try {
            repository.deleteUser(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
