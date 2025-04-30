
package com.uade.tpo.E_Commerce.entity.dto;

public class ReviewData {

    private String text;
    private int stars;
    private long id_product;
    private long id_user;

    public ReviewData(String text, int stars, long id_product,long id_user) {
        this.text = text;
        this.stars = stars;
        this.id_product = id_product;
        this.id_user=id_user;
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

    public long getId_user() {
        return id_user;
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
