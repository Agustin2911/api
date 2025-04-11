package com.uade.tpo.E_Commerce.controller;

import com.uade.tpo.E_Commerce.entity.Seller_User;
import com.uade.tpo.E_Commerce.service.Seller_User_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Seller_User_Controller {

    private final Seller_User_Service sellerUserService;

    public Seller_User_Controller(Seller_User_Service sellerUserService) {
        this.sellerUserService = sellerUserService;
    }

    @PostMapping("/seller_user")
    public ResponseEntity<?> createSellerUser(@RequestBody SellerUserRequest sellerUserRequest) {
        Seller_User newSeller = new Seller_User();
        newSeller.setCompany_name(sellerUserRequest.companyName());
        newSeller.setDescription(sellerUserRequest.descriptorMerchant());
        newSeller.setCuit(sellerUserRequest.cuit());
        newSeller.setState("activo");// Puedes establecer un estado por defecto

        // validaciones adicionales por aca masomenos

        Seller_User savedSeller = sellerUserService.createSellerUser(newSeller);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SellerUserResponse(
                savedSeller.getId_user(),
                savedSeller.getCuit(),
                savedSeller.getCompany_name(),
                savedSeller.getDescription(),
                savedSeller.getState()
        ));
    }
}

record SellerUserRequest(int cuit, String companyName, String descriptorMerchant) {}
record SellerUserResponse(int idUser, int cuit, String companyName, String description, String state) {}