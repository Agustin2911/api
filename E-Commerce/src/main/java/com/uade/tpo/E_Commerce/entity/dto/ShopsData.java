
package com.uade.tpo.E_Commerce.entity.dto;
import lombok.Data;

// este dto sirve para el metodo post de el controller de shops
@Data
public class ShopsData {

    private String city;
    private String street;
    private long id_user;

    public ShopsData(String city, String street, long id_user) {
        this.city = city;
        this.street = street;
        this.id_user = id_user;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public long getId_user() {
        return id_user;
    }
}