package com.uade.tpo.E_Commerce.entity;


import java.util.ArrayList;
import java.util.List;

import com.uade.tpo.E_Commerce.entity.dto.CompoundKeyShop_Stock;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Shop_Stock {


    public Shop_Stock (){}

    @EmbeddedId
    private CompoundKeyShop_Stock id;


    @ManyToOne
    @MapsId("id_product")
    @JoinColumn(name = "id_product", referencedColumnName = "id_product", nullable = false)
    private Product_Stock product_stock;

    @ManyToOne
    @MapsId("id_shop")
    @JoinColumn(name = "id_shop", referencedColumnName = "id_shop", nullable = false)
    private Shops shops;

    @Column(name = "stock", nullable = false)
    private int stock;

}
