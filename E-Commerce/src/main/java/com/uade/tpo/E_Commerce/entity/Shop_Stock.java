package com.uade.tpo.E_Commerce.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Data
@Entity
public class Shop_Stock {


    @Id
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product id_product;


    @OneToOne
    @JoinColumn(name = "id_shop")
    private Shops id_shop;

    @Column(name = "stock")
    private int stock;


}
