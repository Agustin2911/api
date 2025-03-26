package com.uade.tpo.E_Commerce.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;


@Data
@Entity
public class Shop_Stock {


    @Id
    private int id_product;
    private int id_shop;
    private int stock;


}
