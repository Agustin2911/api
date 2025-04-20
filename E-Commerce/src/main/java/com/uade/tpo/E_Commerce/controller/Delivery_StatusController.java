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

    @Data
    @RestController
    @RequestMapping("/shops")
    public static class ShopsController {

        @Autowired
        private ShopsService service;

        @GetMapping("/all/{id}")
        public ResponseEntity<Object> getAllShopsById(@PathVariable long id){
            Optional<ArrayList<Shops>> shops = service.getShopsById(id);

            if (shops.isPresent() && shops.get().size()>0){
                return ResponseEntity.ok(shops.get());
            }
            else{
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("there are not shops of this seller"));
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<Object> getShopById(@PathVariable long id){
            Optional<Shops> shop=service.SearchShop(id);

            if (shop.isPresent()){
                return ResponseEntity.ok(shop.get());
            }
            else {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("there is not shop with that id"));
            }
        }

        @PostMapping
        public ResponseEntity<Object> postSHop(@RequestBody ShopsData shop){

            Optional<Shops> respond=service.CreateShop(shop.getCity(),shop.getStreet(),shop.getId_user());
            if(respond.isPresent()){
                return  ResponseEntity.ok(respond.get());
            }
            else {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("the shop could't be created"));
            }
        }

        @PutMapping
        public ResponseEntity<Object> putShop(@RequestBody Shops shop){

            Optional<Shops> respond=service.ModifyShop(shop.getId_shop(),shop.getCity(),shop.getStreet());
            if (respond.isPresent()){
                return ResponseEntity.ok(respond.get());
            }
            else {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("the shop couldn't be modified"));
            }

        }


        @DeleteMapping("/{id}")
        public  ResponseEntity<Object> deleteShop(@PathVariable long id){
            boolean respond =service.DeleteShop(id);

            if(respond){

                return  ResponseEntity.ok(new SuccesResponse("shop deleted"));
            }
            else {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("the shop couldn't be deleted"));
            }
        }
    }
}
