package com.uade.tpo.E_Commerce.controllers;


import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.entity.dto.ProductData;
import com.uade.tpo.E_Commerce.entity.Product;
import com.uade.tpo.E_Commerce.entity.dto.SuccesResponse;
import com.uade.tpo.E_Commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController{

    @Autowired
    private ProductService service;

    @Value("${upload-dir}")
    private String UPLOAD_DIR;


    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        Optional<ArrayList<Product>> respond = service.allProduct();
        if (!respond.get().isEmpty()) {
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
        if (!respond.get().isEmpty()) {
            return ResponseEntity.ok(respond.get());

        } else {

            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("there are not products with this category"));
        }
    }


    @GetMapping("/bySubCategoryid/{id}")
    public ResponseEntity<Object> getProductBySubCategoryId(@PathVariable long id) {
        Optional<ArrayList<Product>> respond = service.productsBySub_categoryId(id);
        if (!respond.get().isEmpty()) {
            return ResponseEntity.ok(respond.get());

        } else {

            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("there are not products of this sub category"));
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> postProduct( @ModelAttribute ProductData productData) throws IOException {
        String filePath = UPLOAD_DIR + System.currentTimeMillis() + "_" + productData.getFile().getOriginalFilename();
        Optional<Product> product_created = service.createProducts(productData.getProduct_name(), filePath, productData.getPrice(), productData.getDescription(), productData.getDiscount_state(), productData.getDiscount(), productData.getId_sub_category());

        if (product_created.isPresent()) {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File destination = new File(filePath);
            productData.getFile().transferTo(destination);



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
