
package com.uade.tpo.E_Commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "company_shops")
public class Company_Shops {

    public Company_Shops(){}

    @Id
    @Column(name = "id_shop")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Seller_User Seller_User;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "id_shop", nullable = false)
    private Shops shops;

    public Long getId() {
        return id;
    }

    public com.uade.tpo.E_Commerce.entity.Seller_User getSeller_User() {
        return Seller_User;
    }

    public Shops getShops() {
        return shops;
    }
}

