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

    public void setId_shop(long id_shop) {
        this.id_shop = id_shop;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setShop_stock(List<Shop_Stock> shop_stock) {
        this.shop_stock = shop_stock;
    }

    public void setCompany_shops(List<Company_Shops> company_shops) {
        this.company_shops = company_shops;
    }
}
