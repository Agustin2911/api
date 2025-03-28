package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Shop_Stock {

    @Id
    @OneToMany
    @JoinColumn(name = "id_product", referencedColumnName = "id_product", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_shop", referencedColumnName = "id_shop", nullable = false)
    private Shops shop;

    @Column(name = "stock", nullable = false)
    private int stock;

}
