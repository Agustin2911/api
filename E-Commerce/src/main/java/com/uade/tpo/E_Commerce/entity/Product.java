
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

    public void setId_product(long id_product) {
        this.id_product = id_product;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDiscount_state(String discount_state) {
        this.discount_state = discount_state;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setProduct_stock(Product_Stock product_stock) {
        this.product_stock = product_stock;
    }

    public void setReview_list(List<Review> review_list) {
        this.review_list = review_list;
    }

    public void setSub_categoryProductList(List<Sub_categoryProduct> sub_categoryProductList) {
        this.sub_categoryProductList = sub_categoryProductList;
    }

    public void setItems_list(List<Items> items_list) {
        this.items_list = items_list;
    }
}