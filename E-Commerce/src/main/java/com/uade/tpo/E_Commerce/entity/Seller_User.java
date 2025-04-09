
package com.uade.tpo.E_Commerce.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Seller_User {


    public Seller_User(){}

    @Id
    @Column(name = "id_user", nullable = false)
    private Long id;


    @OneToOne
    @MapsId("id_user")
    @JoinColumn(name = "id_user", nullable = false)
    private Basic_User basic_user;

    @Column(name = "cuit", nullable = false)
    private int cuit;

    @Column(name = "company_name", length = 50)
    private String company_name;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "state", length = 30)
    private String state;

    @OneToMany(mappedBy = "Seller_User")
    private List<Company_Shops> company_shops;

}

