package com.uade.tpo.E_Commerce.entity;



import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Category {

    public Category(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_category;

    @Column(name = "name_category", length = 60)
    private String name_category;

    @OneToMany(mappedBy = "category") //El que tiene PK se le pone mappedBy
    private List<Sub_Category> sub_category;
}
