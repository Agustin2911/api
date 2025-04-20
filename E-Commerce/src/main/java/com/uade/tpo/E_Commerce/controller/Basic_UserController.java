package com.uade.tpo.E_Commerce.controllers;

import com.uade.tpo.E_Commerce.entity.Basic_User;
import com.uade.tpo.E_Commerce.entity.Product;
import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.entity.dto.ProductData;
import com.uade.tpo.E_Commerce.entity.dto.SuccesResponse;
import com.uade.tpo.E_Commerce.entity.dto.newBasic_user;

import com.uade.tpo.E_Commerce.service.Basic_UserService;
import com.uade.tpo.E_Commerce.service.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/basic_user")
public class Basic_UserController {

    @Autowired
    private Basic_UserService service;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        Optional<List<Basic_User>> users = service.getAll();
        if (users.isPresent()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("No users found"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable long id) {
        Optional<Basic_User> user = service.getById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("User not found"));
        }
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody newBasic_user user) {
        Optional<Basic_User> created = service.createUser(user);
        if (created.isPresent()) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("User could not be created"));
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody Basic_User user) {
        Optional<Basic_User> updated = service.updateUser( user);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("User could not be updated"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        boolean deleted = service.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok(new SuccesResponse("User deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("User could not be deleted"));
        }
    }

    @RestController
    @RequestMapping("/product")
    public static class ProductController{

        @Autowired
        private ProductService service;


        @GetMapping
        public ResponseEntity<Object> getAllProducts() {
            Optional<ArrayList<Product>> respond = service.allProduct();
            if (respond.isPresent()) {
                return ResponseEntity.ok(respond.get());

            } else {

                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("there are not products"));
            }
        }

        @GetMapping("/productById/{id}")
        public ResponseEntity<Object> getProductById(@PathVariable long id) {
            Optional<Product> respond = service.productById(id);
            if (respond.isPresent()) {
                return ResponseEntity.ok(respond.get());

            } else {

                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("there is not product by this id"));
            }
        }


        @GetMapping("/byCategoryid/{id}")
        public ResponseEntity<Object> getProductByCategoryId(@PathVariable long id) {
            Optional<ArrayList<Product>> respond = service.productsByCategoryId(id);
            if (respond.isPresent()) {
                return ResponseEntity.ok(respond.get());

            } else {

                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("there are not products with this category"));
            }
        }


        @GetMapping("/bySubCategoryid/{id}")
        public ResponseEntity<Object> getProductBySubCategoryId(@PathVariable long id) {
            Optional<ArrayList<Product>> respond = service.productsBySub_categoryId(id);
            if (respond.isPresent()) {
                return ResponseEntity.ok(respond.get());

            } else {

                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("there are not products of this sub category"));
            }
        }

        @PostMapping
        public ResponseEntity<Object> postProduct(@RequestBody ProductData product) {

            Optional<Product> product_created = service.createProducts(product.getProduct_name(), product.getPhoto_url(), product.getPrice(), product.getDescription(), product.getDiscount_state(), product.getDiscount(), product.getId_sub_category());

            if (product_created.isPresent()) {
                return ResponseEntity.ok(product_created.get());
            } else {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("the system couldn't create the new product"));
            }


        }

        @PutMapping
        public ResponseEntity<Object> putProduct(@RequestBody Product product) {

            Optional<Product> product_created = service.modifyProduct(product.getId_product(), product.getProduct_name(), product.getPhoto_url(), product.getPrice(), product.getDescription(), product.getDiscount_state(), product.getDiscount());

            if (product_created.isPresent()) {
                return ResponseEntity.ok(product_created.get());
            } else {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("the System couldn't modify the product"));
            }

        }


        @DeleteMapping("/{id}")
        public ResponseEntity<Object> deleteProduct(@PathVariable long id) {

            boolean respond = service.deleteProduct(id);

            if (respond) {

                return ResponseEntity.ok(new SuccesResponse("products deleted"));
            } else {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("the System couldn't delete the product"));
            }
        }

    }
}