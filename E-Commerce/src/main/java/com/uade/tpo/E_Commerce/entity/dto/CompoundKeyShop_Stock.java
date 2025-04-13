package com.uade.tpo.E_Commerce.entity.dto;

import lombok.Data;

@Data
public class CompoundKeyShop_Stock {
    private Long id_shop;
    private Long id_product;

    public Long getId_shop() {
        return id_shop;
    }

    public Long getId_product() {
        return id_product;
    }
}
