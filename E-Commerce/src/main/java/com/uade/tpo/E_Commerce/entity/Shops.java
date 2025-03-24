package com.uade.tpo.E_Commerce.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Shops {
    private int id_shop;
    private String city;
    private String street;

    public Shops(int id_shop, String city, String street) {
        this.id_shop = id_shop;
        this.city = city;
        this.street = street;
    }


}
