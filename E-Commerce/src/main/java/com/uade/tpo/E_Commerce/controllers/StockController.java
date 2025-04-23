package com.uade.tpo.E_Commerce.controllers;

import com.uade.tpo.E_Commerce.entity.Product_Stock;
import com.uade.tpo.E_Commerce.entity.Shop_Stock;
import com.uade.tpo.E_Commerce.entity.dto.CreateProductStockDTO;
import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.entity.dto.ModifyStockResponse;
import com.uade.tpo.E_Commerce.entity.dto.StockWarningModification;

import com.uade.tpo.E_Commerce.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService service;
    
    @GetMapping("/{id}")
    public ResponseEntity <Object> getStockOfAProduct(@PathVariable long id){

        Optional<Product_Stock> product=service.getStockOfAProductById(id);

        if(product.isPresent()){

            return ResponseEntity.ok(product.get());
        }
        else{

            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("there are no products with this id or you didn't registe the stock of it"));
        }

    }


    @GetMapping("/getLocation/{id}")
    public ResponseEntity<Object> getLocationOfStock(@PathVariable long id){

        Optional<ArrayList<Shop_Stock>> shops=service.getTheLocationOfTheProduct(id);

        if (shops.isPresent()){
            return  ResponseEntity.ok(shops.get());
        }

        else{
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("there are no products with this id or you didn't registe the stock of it"));
        }


    }

    @PostMapping
    public ResponseEntity<Object> postProductStock(@RequestBody CreateProductStockDTO product){



        Optional<CreateProductStockDTO> product_stock=service.createProduct_Stock(product.getId(), product.getStock_entry(),product.getShop(),product.getStock_warning());

        if (product_stock.isPresent()){
            return  ResponseEntity.ok(product_stock.get());
        }

        else{
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("the system couldn't create the stock for the product"));
        }


    }

//    @PostMapping("/shop_stock")
//    public ResponseEntity<Object> postStock_shop(@RequestBody Shop_Stock product){
//
//
//
//        Optional<Shop_Stock> shop_stock=service.createShopStock(product.getId().getId_product(),product.getStock(),product.getId().getId_shop());
//
//        if (shop_stock.isPresent()){
//            return  ResponseEntity.ok(shop_stock.get());
//        }
//
//        else{
//            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("the system couldn't create the stock for the product"));
//        }
//
//
//    }

    @PutMapping
    public  ResponseEntity<Object> modifyStock(@RequestBody Shop_Stock data){
        Optional<Product_Stock> stock = service.modifyStock(data.getId().getId_product(),data.getId().getId_shop(),
                data.getStock());
        if (stock.isPresent()){
            return  ResponseEntity.ok(stock.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("there are no products with this id or you didn't register the stock of it"));
        }

    }

    @PutMapping("/stockWarning")
    public ResponseEntity<Object> modifyStockWarning(@RequestBody StockWarningModification stock){
        Optional<Product_Stock> stock_warning=service.modifyStock_warning(stock.getId(), stock.getStock_warning());

        if (stock_warning.isPresent()){
            return  ResponseEntity.ok(stock_warning.get());
        }

        else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("there are no products with this id or you didn't register the stock of it"));
        }
    }

}   
