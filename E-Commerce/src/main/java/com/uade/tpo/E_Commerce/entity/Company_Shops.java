package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "company_shops")
public class Company_Shops {

    public Company_Shops(){}


    @Id
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Seller_User seller_user;

    @ManyToOne
    @JoinColumn(name = "id_shop", nullable = false)
    private Shops shop;

}
