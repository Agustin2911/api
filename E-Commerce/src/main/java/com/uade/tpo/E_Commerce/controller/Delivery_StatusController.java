package com.uade.tpo.E_Commerce.controller;


import com.uade.tpo.E_Commerce.entity.Delivery_Status;
import com.uade.tpo.E_Commerce.entity.Shops;
import com.uade.tpo.E_Commerce.entity.dto.Delivery_StatusRequest;
import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.entity.dto.ShopsData;
import com.uade.tpo.E_Commerce.entity.dto.SuccesResponse;
import com.uade.tpo.E_Commerce.service.Delivery_StatusService;
import com.uade.tpo.E_Commerce.service.ShopsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/delivery-status")
public class Delivery_StatusController {

    @Autowired
    private Delivery_StatusService delivery_statusService;

    @GetMapping("/{id_delivery}")
    public ResponseEntity<Delivery_Status> getCategoryById(@PathVariable Long id_delivery){
        Optional<Delivery_Status> delivery_status = delivery_statusService.getDeliveryStatusById(id_delivery);
        //noinspection OptionalIsPresent
        if (delivery_status.isPresent()){
            return ResponseEntity.ok(delivery_status.get());
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createDeliveryStatus(@RequestBody Delivery_StatusRequest request)
            /*throws DuplicateException*/{
        Delivery_Status new_delivery = delivery_statusService.createDeliveryStatus(request.getId_sale(),
                request.getDelivery_type(), request.getAddress(), request.getDelivery_status());

        if (new_delivery == null) {
            return ResponseEntity.badRequest().body("Ya existe un delivery status con esos datos.");
        }else{
            return ResponseEntity.created(URI.create("/delivery-status/" + new_delivery.getId_delivery())).body(new_delivery);

        }

    }

    @PutMapping("/{id_delivery}")
    public ResponseEntity<Delivery_Status> updateDeliveryStatus(@PathVariable Long id_delivery,
                                                                @RequestParam Long id_sale,
                                                                @RequestParam String delivery_type,
                                                                @RequestParam String address,
                                                                @RequestParam String delivery_status){
        Optional<Delivery_Status> updatedDelivery = delivery_statusService.updateDeliveryStatus(id_delivery, id_sale,
                delivery_type, address,delivery_status);
        //noinspection OptionalIsPresent
        if (updatedDelivery.isPresent()){
            return ResponseEntity.ok(updatedDelivery.get());
        }
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id_delivery}")
    public ResponseEntity<Void> deleteDeliveryStatus(@PathVariable Long id_delivery){

        boolean deleted = delivery_statusService.deleteDeliveryStatus(id_delivery);
        if (deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
