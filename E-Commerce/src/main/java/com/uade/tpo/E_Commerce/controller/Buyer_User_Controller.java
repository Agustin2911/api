package com.uade.tpo.E_Commerce.controller;

import com.uade.tpo.E_Commerce.entity.Buyer_User;
import com.uade.tpo.E_Commerce.service.Buyer_User_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Buyer_User_Controller {

    private final Buyer_User_Service buyerUserService;

    public Buyer_User_Controller(Buyer_User_Service buyerUserService) {
        this.buyerUserService = buyerUserService;
    }

    @PostMapping("/buyer_user")
    public ResponseEntity<?> createBuyerUser(@RequestBody BuyerUserRequest buyerUserRequest) {
        Buyer_User newBuyer = new Buyer_User();
        newBuyer.setName(buyerUserRequest.name());
        newBuyer.setLast_name(buyerUserRequest.lastName());
        newBuyer.setDni(buyerUserRequest.dni());

        // Aquí podrías realizar validaciones adicionales de los datos recibidos

        Buyer_User savedBuyer = buyerUserService.createBuyerUser(newBuyer);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BuyerUserResponse(
                savedBuyer.getId_user(),
                savedBuyer.getName(),
                savedBuyer.getLast_name(),
                savedBuyer.getDni()
        ));
    }
}

record BuyerUserRequest(String name, String lastName, int dni) {}
record BuyerUserResponse(Long idUser, String name, String lastName, int dni) {}