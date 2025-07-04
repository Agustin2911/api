
package com.uade.tpo.E_Commerce.entity.dto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductData {


    private String product_name;
    private MultipartFile photo_url;
    private int price;
    private String description;
    private String discount_state;
    private int discount;
    private long id_sub_category;


    public ProductData(String product_name, MultipartFile photo_url, int price, String description, String discount_state, int discount, long id_sub_category) {
        this.product_name = product_name;
        this.photo_url = photo_url;
        this.price = price;
        this.description = description;
        this.discount_state = discount_state;
        this.discount = discount;
        this.id_sub_category = id_sub_category;

    }

    public String getProduct_name() {
        return product_name;
    }

    public MultipartFile getPhoto_url() {
        return photo_url;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getDiscount_state() {
        return discount_state;
    }

    public int getDiscount() {
        return discount;
    }

    public long getId_sub_category() {
        return id_sub_category;
    }



}

