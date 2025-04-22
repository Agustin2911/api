package com.uade.tpo.E_Commerce.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import com.uade.tpo.E_Commerce.entity.Basic_User;
import com.uade.tpo.E_Commerce.entity.dto.newBasic_user;
import com.uade.tpo.E_Commerce.repository.Basic_UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class Basic_UserService implements Basic_UserImp{

    @Autowired
    private Basic_UserRepository repository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<Basic_User>> getAll() {
        return repository.findAllUsers();
    }

    @Override
    public Optional<Basic_User> getById(long id) {
        return repository.findByIdUser(id);
    }

    @Override
    @Transactional
    public Optional<Basic_User> createUser(newBasic_user user) {



        repository.insertUser(user.getUsername(), user.getMail(), user.getPassword());
        return repository.findLatest();
    }

    @Override
    @Transactional
    public Optional<Basic_User> updateUser(Basic_User user) {

        Optional<Basic_User>user_exist=repository.findByIdUser(user.getId_user());

        if(!user_exist.isPresent()){
            return Optional.empty();
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        repository.updateUser(user.getUsername(), user.getMail(), hashedPassword, user.getId_user());

        entityManager.flush();

        return repository.findByIdUser(user.getId_user());
    }

    @Override
    @Transactional
    public boolean deleteUser(long id) {

        Optional<Basic_User>user_exist=repository.findByIdUser(id);

        if(!user_exist.isPresent()){
            return false
                    ;
        }

        try {
            repository.deleteUser(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }




}
