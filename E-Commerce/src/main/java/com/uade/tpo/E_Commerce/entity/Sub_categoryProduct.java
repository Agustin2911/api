package com.uade.tpo.E_Commerce.entity;

import java.util.ArrayList;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Sub_categoryProduct {

    public Sub_categoryProduct(){}

    @Id
    @ManyToOne
    @JoinColumn(name="id_sub_category")
    private ArrayList<Sub_Category> id_sub_category;


    @ManyToOne
    @JoinColumn(name="id_product")
    private ArrayList<Product> id_product;

}
