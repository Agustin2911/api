package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Basic_User;
import com.uade.tpo.E_Commerce.repository.Basic_User_Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class User_Service {

    private final Basic_User_Repository basicUserRepository;

    public User_Service(Basic_User_Repository basicUserRepository) {
        this.basicUserRepository = basicUserRepository;
    }

    public Basic_User findByUsername(String username) {
        Optional<Basic_User> userOptional = basicUserRepository.findByUsername(username);
        return userOptional.orElse(null);
    }

    // Hay una opcion de poder hacerlo mas seguro agregando una dependencia al pom pero seguro que lo vemos en la clase de tokens
    public boolean checkPassword(String rawPassword, String storedPassword) {
        // **WARNING: This is INSECURE. Never do this in a real application.**
        return rawPassword.equals(storedPassword);
    }

    public String generateAuthToken(Basic_User user) {
        // aca iria un token seguro
        return "fake_token_for_" + user.getUsername();
    }
}