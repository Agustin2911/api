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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController{

    @Autowired
    private ProductService service;

    @Value("${upload-dir-products}")
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

    @GetMapping("/category-subCategory/{id}")
    public ResponseEntity<Object> getCategoryAndSubCategoryById(@PathVariable long id) {
        Optional<Object> respond = service.categorySubCategoryById(id);
        if (respond.isPresent()) {
            return ResponseEntity.ok(respond.get());

        } else {

            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("there is no product by this id"));
        }
    }

    @GetMapping("/name/{name_product}")
    public Long getIdByName(@PathVariable String name_product) {
        Long result = service.getIdByName(name_product);
        if (result != null){
            return result;
        }
        return 0L;
    }


    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> postProduct(
            @RequestParam("product_name")   String name,
            @RequestParam("price")          int price,
            @RequestParam("description")    String description,
            @RequestParam("discount_state") String discountState,
            @RequestParam("discount")       int discount,
            @RequestParam("id_sub_category") long subCategoryId,
            @RequestPart("photo_url")           MultipartFile photo_url
    ) throws IOException {
        // 1) Guarda la imagen en disco
        String filename = System.currentTimeMillis() + "_" + photo_url.getOriginalFilename();
        Path target = Paths.get(UPLOAD_DIR).resolve(filename);
        Files.createDirectories(target.getParent());
        photo_url.transferTo(target.toFile());

        // 2) Construye la URL pública desde donde se servirá la imagen
        //    Puedes usar ServletUriComponentsBuilder o concatenar manualmente:
        String photoUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/images/products/")
                .path(filename)
                .toUriString();

        // 3) Llama al service con la URL de la foto (string) y resto de campos
        Optional<Product> created = service.createProducts(
                name,
                photoUrl,
                price,
                description,
                discountState,
                discount,
                subCategoryId
        );

        // 4) Responde según el resultado
        if (created.isPresent()) {
            return ResponseEntity.ok(created.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("the system couldn't create the new product"));
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
