package com.uade.tpo.E_Commerce.service;


import com.uade.tpo.E_Commerce.entity.Product;
import com.uade.tpo.E_Commerce.entity.Shop_Stock;
import com.uade.tpo.E_Commerce.repository.ProductRepository;
import com.uade.tpo.E_Commerce.repository.Product_StockRepository;
import com.uade.tpo.E_Commerce.repository.Shop_StockRepository;
import com.uade.tpo.E_Commerce.repository.Sub_CategoryProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService implements  ProductImp{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private Sub_CategoryProductRepository repository2;

    @Autowired
    private Product_StockRepository repository3;

    @Autowired
    private Shop_StockRepository repository4;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<ArrayList<Product>> allProduct() {
        return repository.allTheProducts();
    }

    @Override
    public Optional<Product> productById(long id_product) {
        return repository.productByID(id_product);
    }

    @Override
    public Optional<ArrayList<Product>> productsByCategoryId(long id_category) {
        return repository.productsByCategory(id_category);
    }

    @Override
    public Optional<ArrayList<Product>> productsBySub_categoryId(long id_sub_category) {
        return repository.productsBySubcategory(id_sub_category);
    }


    public Optional<Object> categorySubCategoryById(long id_product) {
        return repository.categoryAndSubcategoryById(id_product);
    }

    @Transactional
    @Override
    public Optional<Product> createProducts(String name, String photo_url, int price, String description, String discount_state, int discount,long id_sub_category) {




        Optional<Product> product_exist=repository.productsByAtribiutes(name, photo_url, price, description, discount_state, discount);


        if (product_exist.isPresent()){
            return Optional.empty();
        }

        repository.CreateProduct(name, photo_url, price, description, discount_state, discount);
        Optional<Product> product= repository.productsByAtribiutes(name, photo_url, price, description, discount_state, discount);

        if (product.isPresent()){

            long id=product.get().getId_product();
            repository2.addProduct(id_sub_category,id);
        }

        return product;
    }

    @Transactional
    @Override
    public Optional<Product> modifyProduct(long id_product, String name, String photo_url, int price, String description, String discount_state, int discount) {
        Optional<Product>product=repository.productByID(id_product);
        if(!product.isPresent()){
            return Optional.empty();
        }
        repository.modifyProduct(id_product, name, photo_url, price, description, discount_state, discount);

        entityManager.flush();
        entityManager.clear();

        product=repository.productByID(id_product);

        return product;
    }

    @Transactional
    @Override
    public boolean deleteProduct(long id_product) {

        Optional<Product> product=repository.productByID(id_product);
        if(!product.isPresent()){
            return  true;
        }
        repository3.deleteProductStock(id_product);
        repository4.deleteShop_stock(id_product);
        repository2.deleteProduct(id_product);
        repository.deleteProduct(id_product);
        Optional<Product> respond=repository.productByID(id_product);

        if(respond.isPresent()){
            return false;
        }

        else{
            return true;
        }
    }


    public Long getIdByName(String nameProduct) {
        return repository.findIdByName(nameProduct);
    }
}
