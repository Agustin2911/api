package com.uade.tpo.E_Commerce.controller;

import com.uade.tpo.E_Commerce.entity.Basic_User;
import com.uade.tpo.E_Commerce.service.User_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login_Controller {

    private final User_Service userService;

    public Login_Controller(User_Service userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.username();
        String password = loginRequest.password();

        Basic_User user = userService.findByUsername(username);

        if (user != null && userService.checkPassword(password, user.getPassword())) {
            // Autenticación exitosa
            String token = userService.generateAuthToken(user); // ver cuando tengamos la clase de token
            String role = user.getUser_roles().getRole().getRoleName();
            return ResponseEntity.ok(new AuthResponse(token, "Bearer", user.getId_user(), username, role));
        } else {
            return new ResponseEntity<>(new ErrorResponse("Credenciales inválidas"), HttpStatus.UNAUTHORIZED);
        }
    }
}

record LoginRequest(String username, String password) {}
record AuthResponse(String accessToken, String tokenType, Long userId, String username, String role) {}
record ErrorResponse(String message) {}