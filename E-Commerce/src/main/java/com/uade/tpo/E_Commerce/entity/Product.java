package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "product")
public class Product {

    public Product(){}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_product;

    @Column(name = "product_name",length = 100)
    private String product_name;

    @Column(name = "photo_url",length = 500)
    private String photo_url;

    @Column(name = "price",nullable = false)
    private int price;


    @Column(name = "description",length = 500)
    private String description;

    @Column(name = "discount_state",length = 5)
    private String discount_state;

    @Column(name = "discount")
    private int discount;

    @OneToOne(mappedBy = "product")
    private Product_Stock product_stock;

    @OneToMany(mappedBy = "product")
    private ArrayList<Review> review_list;

    @ManyToMany(mappedBy = "product")
    private ArrayList<Sub_categoryProduct> sub_categoryProductList;

    @OneToMany(mappedBy = "product")
    private ArrayList<Items> items_list;
}