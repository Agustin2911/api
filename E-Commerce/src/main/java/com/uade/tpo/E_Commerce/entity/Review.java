package com.uade.tpo.E_Commerce.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Review {

    public Review(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_comment;

    @Column(name="text" , length = 2000)
    private String text;

    @Column(name="stars")
    private int stars;

    @ManyToOne
    @JoinColumn(name = "id_product",nullable = false)
    private Product product;

}
