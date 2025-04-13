package com.uade.tpo.E_Commerce.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "Shops")
public class Shops {


    public Shops(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_shop;

    @Column(name = "city" ,length = 40 , nullable = false)
    private String city;

    @Column(name = "street",length = 40 ,nullable = false)
    private String street;

    @JsonIgnore
    @OneToMany(mappedBy = "shops")
    private List<Shop_Stock> shop_stock;

    @JsonIgnore
    @OneToMany(mappedBy = "shops")
    private List<Company_Shops> company_shops;

    public long getId_shop() {
        return id_shop;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public List<Shop_Stock> getShop_stock() {
        return shop_stock;
    }

    public List<Company_Shops> getCompany_shops() {
        return company_shops;
    }
}
