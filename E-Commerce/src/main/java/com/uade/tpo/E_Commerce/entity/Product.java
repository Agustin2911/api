package com.uade.tpo.E_Commerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "product")
public class Product {

    public Product(){}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_product;

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

    @JsonIgnore
    @OneToOne(mappedBy = "product")
    private Product_Stock product_stock;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Review> review_list;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Sub_categoryProduct> sub_categoryProductList;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Items> items_list;


    public long getId_product() {
        return id_product;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getDiscount_state() {
        return discount_state;
    }

    public int getDiscount() {
        return discount;
    }

    public Product_Stock getProduct_stock() {
        return product_stock;
    }

    public List<Review> getReview_list() {
        return review_list;
    }

    public List<Sub_categoryProduct> getSub_categoryProductList() {
        return sub_categoryProductList;
    }

    public List<Items> getItems_list() {
        return items_list;
    }
}