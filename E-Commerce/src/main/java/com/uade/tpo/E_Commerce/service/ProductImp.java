package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Product;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

public interface ProductImp {


    public Optional<ArrayList<Product>> allProduct();

    public Optional<Product> productById(long id_product);

    public Optional<ArrayList<Product>> productsByCategoryId(long id_category);

    public Optional<ArrayList<Product>> productsBySub_categoryId(long id_sub_category);

    public Optional<Product> createProducts(String name , String photo_url , int price, String description, String discount_state,int discount,long id_sub_category);

    public Optional<Product> modifyProduct(long id_product, String name, String photo_url, int price, String description,String discount_state,int discount);

    public boolean deleteProduct(long id_product);
}
