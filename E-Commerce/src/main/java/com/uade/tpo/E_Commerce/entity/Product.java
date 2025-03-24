package com.uade.tpo.E_Commerce.entity;

public class Product {

    private int id_prodcut;
    private String product_name;
    private String photo_url;
    private int price;
    private int id_sub_category;
    private String description;
    private String discount_state;
    private int discount;

    public Product(int id_prodcut, String product_name, String photo_url, int price, int id_sub_category, String description, String discount_state, int discount) {
        this.id_prodcut = id_prodcut;
        this.product_name = product_name;
        this.photo_url = photo_url;
        this.price = price;
        this.id_sub_category = id_sub_category;
        this.description = description;
        this.discount_state = discount_state;
        this.discount = discount;
    }
}
