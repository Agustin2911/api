
package com.uade.tpo.E_Commerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Review {

    public Review(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_comment;

    @Column(name="text" , length = 2000)
    private String text;

    @Column(name="stars")
    private int stars;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_product",nullable = false)
    private Product product;

    public long getId_comment() {
        return id_comment;
    }

    public String getText() {
        return text;
    }

    public int getStars() {
        return stars;
    }

    public Product getProduct() {
        return product;
    }
}
