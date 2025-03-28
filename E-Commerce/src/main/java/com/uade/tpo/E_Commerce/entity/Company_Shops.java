package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "company_shops")
public class Company_Shops {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company_shop")
    private int idCompanyShop;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Basic_User user;

    @ManyToOne
    @JoinColumn(name = "id_shop", nullable = false)
    private Shops shop;

    public Company_Shops() {
    }

    public Company_Shops(Basic_User user, Shops shop) {
        this.user = user;
        this.shop = shop;
    }
}
