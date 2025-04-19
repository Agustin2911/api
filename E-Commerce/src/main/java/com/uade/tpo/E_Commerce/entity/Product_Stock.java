
package com.uade.tpo.E_Commerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Product_Stock {

    public Product_Stock() {
    }

    @Id
    @Column(name = "id_product", nullable = false)
    private Long id;


    @JsonIgnore
    @OneToOne
    @MapsId("id_product")
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "stock_warning", nullable = false)
    private int stock_warning;

    @JsonIgnore
    @OneToMany(mappedBy = "product_stock")
    private List<Shop_Stock> shop_stock;

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getStock() {
        return stock;
    }

    public int getStock_warning() {
        return stock_warning;
    }

    public List<Shop_Stock> getShop_stock() {
        return shop_stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setStock_warning(int stock_warning) {
        this.stock_warning = stock_warning;
    }

    public void setShop_stock(List<Shop_Stock> shop_stock) {
        this.shop_stock = shop_stock;
    }

}

