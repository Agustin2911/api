package com.uade.tpo.E_Commerce.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {

    private int id_comment;
    private String text;
    private int starts;
    private int id_product;

    public Review(int id_comment, String text, int starts, int id_product) {
        this.id_comment = id_comment;
        this.text = text;
        this.starts = starts;
        this.id_product = id_product;
    }
}
