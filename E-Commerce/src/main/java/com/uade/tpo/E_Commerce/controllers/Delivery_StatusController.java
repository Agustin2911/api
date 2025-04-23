package com.uade.tpo.E_Commerce.controllers;


import com.uade.tpo.E_Commerce.entity.Delivery_Status;
import com.uade.tpo.E_Commerce.entity.dto.Delivery_StatusRequest;
import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.entity.dto.SuccesResponse;
import com.uade.tpo.E_Commerce.service.Delivery_StatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/delivery-status")
public class Delivery_StatusController {

    @Autowired
    private Delivery_StatusServiceImpl delivery_statusService;

    @GetMapping("/{id_delivery}")
    public ResponseEntity<Object> getCategoryById(@PathVariable Long id_delivery){
        Optional<Delivery_Status> delivery_status = delivery_statusService.getDeliveryStatusById(id_delivery);
        //noinspection OptionalIsPresent
        if (delivery_status.isPresent()){
            return ResponseEntity.ok(delivery_status.get());
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("There is no " +
                "Delivery Status with this id"));
    }

//    @PostMapping
//    public ResponseEntity<Object> createDeliveryStatus(@RequestBody Delivery_StatusRequest request) {
//
//        if (request.getId_sale() == null || request.getDelivery_type() == null || request.getAddress() == null || request.getDelivery_status() == null) {
//            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("The data you are " +
//                    "trying to insert is invalid"));
//        }
//
//        Delivery_Status new_delivery = delivery_statusService.createDeliveryStatus(request.getId_sale(),
//                request.getDelivery_type(), request.getAddress(), request.getDelivery_status());
//
//        if (new_delivery == null) {
//            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("There is already a " +
//                    "Delivery Status with this data"));
//        }else{
//            return ResponseEntity.created(URI.create("/delivery-status/" + new_delivery.getId_delivery())).body(new_delivery);
//
//        }
//
//    }

    @PutMapping("/{id_delivery}")
    public ResponseEntity<Object> updateDeliveryStatus(@PathVariable Long id_delivery,
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
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("There is no " +
                "Delivery Status with this id or the update went wrong"));

    }

    @DeleteMapping("/{id_delivery}")
    public ResponseEntity<Object> deleteDeliveryStatus(@PathVariable Long id_delivery){

        boolean deleted = delivery_statusService.deleteDeliveryStatus(id_delivery);
        if (deleted) {
            return ResponseEntity.ok(new SuccesResponse("Delivery status successfully deleted"));
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("There is no " +
                "Delivery Status with this id"));
    }

}
