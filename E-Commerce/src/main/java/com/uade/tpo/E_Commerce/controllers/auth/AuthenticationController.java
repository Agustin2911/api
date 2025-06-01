package com.uade.tpo.E_Commerce.controllers.auth;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.E_Commerce.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/auth")

public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping(value = "/register/seller_user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthenticationResponse> Sellerregister(
            @ModelAttribute SellerRegister request) throws IOException {
        return ResponseEntity.ok(service.Sellerregister(request));
    }

    @PostMapping(value = "/register/buyer_user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthenticationResponse> Buyerregister(
            @ModelAttribute BuyerRegister request) throws IOException {
        return ResponseEntity.ok(service.BuyerRegister(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}