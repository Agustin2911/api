package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

public class Sub_categoryProduct {


    public Sub_categoryProduct(){}

    @Id
    @ManyToMany
    @JoinColumn(name="id_sub_category")
    private Sub_Category id_sub_category;


    @ManyToMany
    @JoinColumn(name="id_product")
    private  Product id_product;

}
