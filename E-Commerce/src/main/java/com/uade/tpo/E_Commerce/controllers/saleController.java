package com.uade.tpo.E_Commerce.controllers;



import com.uade.tpo.E_Commerce.entity.Sale;
import com.uade.tpo.E_Commerce.entity.dto.ItemsRequest;
import com.uade.tpo.E_Commerce.entity.dto.SaleRequest;
import com.uade.tpo.E_Commerce.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/sale")
public class saleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/{id_sale}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id_sale){
        Optional<Sale> selected_sale = saleService.getSaleById(id_sale);

        if(selected_sale.isPresent()){
            return ResponseEntity.ok(selected_sale.get());
        }
        return ResponseEntity.noContent().build();

    }

    @PostMapping
    public ResponseEntity<Sale> createNewSale(@RequestBody SaleRequest request){

        Sale new_sale = saleService.createSale(request.getTotal_price(), request.getId_user(), request.getSale_date()
                ,request.getItems());
        return ResponseEntity.created(URI.create("/sale/" + new_sale.getId_sale())).body(new_sale);

    }

    @PutMapping("/{id_sale}")
    public ResponseEntity<Sale> updateSale(@PathVariable Long id_sale,
                                           @RequestParam int total_price,
                                           @RequestParam Long id_user,
                                           @RequestParam LocalDateTime sale_date){
        Optional<Sale> updatedSale = saleService.updateSale(id_sale, total_price,
                id_user, sale_date);
        //noinspection OptionalIsPresent
        if (updatedSale.isPresent()){
            return ResponseEntity.ok(updatedSale.get());
        }
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id_sale}")
    public ResponseEntity<Void> deleteSaleStatus(@PathVariable Long id_sale){

        boolean deleted = saleService.deleteSaleById(id_sale);
        if (deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
