package com.uade.tpo.E_Commerce.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
public class Items {

    public Items(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_items;


    @ManyToMany
    @JoinColumn(name = "id_product")
    private List<Product> id_product;

    @ManyToOne
    @JoinColumn(name = "id_sale")
    private int id_sale;

    @Column(name = "amount")
    private int amount;

}
