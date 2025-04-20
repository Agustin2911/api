package com.uade.tpo.E_Commerce.controllers;

import com.uade.tpo.E_Commerce.entity.Sale;
import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.entity.dto.ItemsRequest;
import com.uade.tpo.E_Commerce.entity.dto.SaleRequest;
import com.uade.tpo.E_Commerce.entity.dto.SuccesResponse;
import com.uade.tpo.E_Commerce.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/sale")
public class saleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/{id_sale}")
    public ResponseEntity<Object> getSaleById(@PathVariable Long id_sale){
        Optional<Sale> selected_sale = saleService.getSaleById(id_sale);

        if(selected_sale.isPresent()){
            return ResponseEntity.ok(selected_sale.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("There is no " +
                    "Sale with this id"));
        }

    }

    @PostMapping
    public ResponseEntity<Object> createNewSale(@RequestBody SaleRequest request){

        if (request.getTotal_price() <= 0 || request.getId_user() == null || request.getSale_date() == null || request.getItems() == null || request.getId_shop() == null) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("The data you are " +
                    "trying to insert is invalid"));
        }

        Sale new_sale = saleService.createSale(request.getTotal_price(), request.getId_user(), request.getSale_date()
                ,request.getItems(), request.getId_shop());
        if(new_sale == null){
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("There is " +
                        "already a Sale with this data"));
        }else{
            return ResponseEntity.created(URI.create("/sale/" + new_sale.getId_sale())).body(new_sale);
        }
    }

    @PutMapping("/{id_sale}")
    public ResponseEntity<Object> updateSale(@PathVariable Long id_sale,
                                           @RequestParam int total_price,
                                           @RequestParam Long id_user,
                                           @RequestParam LocalDateTime sale_date){
        Optional<Sale> updatedSale = saleService.updateSale(id_sale, total_price,
                id_user, sale_date);
        //noinspection OptionalIsPresent
        if (updatedSale.isPresent()){
            return ResponseEntity.ok(updatedSale.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("There is no " +
                    "Sale with this id or the update went wrong"));
        }

    }

    @DeleteMapping("/{id_sale}")
    public ResponseEntity<Object> deleteSaleStatus(@PathVariable Long id_sale){

        boolean deleted = saleService.deleteSaleById(id_sale);
        if (deleted){
            return ResponseEntity.ok(new SuccesResponse("Sale deleted successfully"));
        }
        else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("There is no " +
                    "Sale with this id"));
        }
    }

}

