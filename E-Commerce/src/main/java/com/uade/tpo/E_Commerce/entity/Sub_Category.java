package com.uade.tpo.E_Commerce.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Sub_Category {

    public Sub_Category(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_sub_category;
    
    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id_category") // El que tiene FK se le pone el joinColumn
    private Category category;

    @Column(name = "name_sub_category", length = 60)
    private String name_sub_category;

    @ManyToMany
    @JoinColumn(name = "id_sub_category", referencedColumnName = "id_sub_category", nullable = false)
    private ArrayList<Sub_categoryProduct> sub_categoryProduct;

}
