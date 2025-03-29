package com.uade.tpo.E_Commerce.entity;

import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "Shops")
public class Shops {


    public Shops(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_shop;

    @Column(name = "city" ,length = 40 , nullable = false)
    private String city;

    @Column(name = "street",length = 40 ,nullable = false)
    private String street;

    @OneToMany(mappedBy = "shops")
    private List<Shop_Stock> shop_stock;
    
}
