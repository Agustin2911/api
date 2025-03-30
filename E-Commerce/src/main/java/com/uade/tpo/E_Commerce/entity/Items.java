package com.uade.tpo.E_Commerce.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Items {

    public Items(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_items;


    @OneToOne
    @JoinColumn(name = "id_product")
    private Product id_product;

    @ManyToOne
    @JoinColumn(name = "id_sale")
    private Sale sale;

    @Column(name = "amount", nullable = false)
    private int amount;

}
