package com.uade.tpo.E_Commerce.controllers;
import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.entity.dto.ShopsData;
import com.uade.tpo.E_Commerce.entity.Shops;
import com.uade.tpo.E_Commerce.entity.dto.SuccesResponse;
import com.uade.tpo.E_Commerce.service.ShopsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
@Data
@RestController
@RequestMapping("/shops")
public class ShopsController {

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

    @GetMapping("/street/{street}")
    public Long getIdByName(@PathVariable String street) {
        Long result = service.getIdByName(street);
        if (result != null){
            return result;
        }
        return 0L;
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
