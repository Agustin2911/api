package com.uade.tpo.E_Commerce.entity;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Product_Stock {

    public  Product_Stock(){}

    @Id
    @Column(name = "id_product", nullable = false)
    private Long id;


    @OneToOne
    @MapsId("id_product")
    @JoinColumn(name = "id_product",nullable = false)
    private Product id_product;

    @Column(name="stock",nullable = false)
    private int stock;

    @Column(name = "stock_warning",nullable = false)
    private int stock_warning;

}
