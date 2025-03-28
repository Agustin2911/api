package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_product;

    @Column(name = "product_name",length = 100)
    private String product_name;

    @Column(name = "photo_url",length = 500)
    private String photo_url;

    @Column(name = "price",nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "id_sub_category",nullable = false)
    private Sub_Category id_sub_category;

    @Column(name = "description",length = 500)
    private String description;

    @Column(name = "discount_state",length = 5)
    private String discount_state;

    @Column(name = "discount")
    private int discount;

}
