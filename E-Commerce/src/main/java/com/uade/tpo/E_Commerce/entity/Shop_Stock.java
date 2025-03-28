package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Shop_Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id_product", nullable = false)
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_shop", referencedColumnName = "id_shop", nullable = false)
    private Shops shop;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "stock_warning")
    private Integer stockWarning;

    public Shop_Stock() {
    }

    public Shop_Stock(Product product, Shops shop, int stock, Integer stockWarning) {
        this.product = product;
        this.shop = shop;
        this.stock = stock;
        this.stockWarning = stockWarning;
    }
}
