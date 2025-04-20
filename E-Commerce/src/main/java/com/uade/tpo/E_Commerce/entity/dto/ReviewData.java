package com.uade.tpo.E_Commerce.entity.dto;

public class ReviewData {

    private String text;
    private int stars;
    private long id_product;

    public ReviewData(String text, int stars, long id_product) {
        this.text = text;
        this.stars = stars;
        this.id_product = id_product;
    }

    public String getText() {
        return text;
    }

    public int getStars() {
        return stars;
    }

    public long getId_product() {
        return id_product;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setId_product(long id_product) {
        this.id_product = id_product;
    }
}
