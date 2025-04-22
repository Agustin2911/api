package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.controllers.config.UserPrincipal;
import com.uade.tpo.E_Commerce.entity.User_Roles;
import com.uade.tpo.E_Commerce.repository.UserRolesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uade.tpo.E_Commerce.controllers.auth.AuthenticationRequest;
import com.uade.tpo.E_Commerce.controllers.auth.AuthenticationResponse;
import com.uade.tpo.E_Commerce.controllers.auth.RegisterRequest;
import com.uade.tpo.E_Commerce.controllers.config.JwtService;
import com.uade.tpo.E_Commerce.entity.Basic_User;
import com.uade.tpo.E_Commerce.repository.Basic_UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
public class AuthenticationService {
    @PersistenceContext
    private EntityManager entityManager;

    private final Basic_UserRepository repository;
    private final UserRolesRepository repository2;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(Basic_UserRepository repository, UserRolesRepository repository2, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.repository2 = repository2;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        repository.insertUser(request.getFirstname(),request.getEmail(),hashedPassword);
        entityManager.flush();
        entityManager.clear();
        Optional<Basic_User> user=repository.findLatest();
        System.out.println(user.get().getId_user());
        repository2.assignRoleToUser(user.get().getId_user(),request.getRole());
        entityManager.flush();
        entityManager.clear();
        Optional<User_Roles> role=repository2.findByUserId(user.get().getId_user());

        Optional<Basic_User> updatedUser=repository.findWithRolesById(user.get().getId_user());

        updatedUser.get().setUser_roles(role.get());
        var jwtToken = jwtService.generateToken(new UserPrincipal(updatedUser.get()));
        return new AuthenticationResponse(jwtToken);
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        Optional<Basic_User> user = repository.findByMail(request.getEmail());
        var jwtToken = jwtService.generateToken(new UserPrincipal(user.get()));
        return new AuthenticationResponse(jwtToken);
    }
}
