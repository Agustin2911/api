package com.uade.tpo.E_Commerce.entity;

import java.util.ArrayList;

import com.uade.tpo.E_Commerce.entity.dto.CompoundKeySub_categoryProduct;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Sub_categoryProduct {

    public Sub_categoryProduct(){}

//    @EmbeddedId
//    private UserRolesKey id;
//
//    @ManyToOne
//    @MapsId("id_role")
//    @JoinColumn(name = "id_role")
//    private Roles role;
//
//    @OneToOne
//    @MapsId("id_user")
//    @JoinColumn(name = "id_user")
//    private Basic_User user;

    @EmbeddedId
    private CompoundKeySub_categoryProduct id;

    @ManyToOne
    @MapsId("id_sub_category")
    @JoinColumn(name="id_sub_category")
    private ArrayList<Sub_Category> id_sub_category;

    @ManyToOne
    @MapsId("id_product")
    @JoinColumn(name="id_product")
    private ArrayList<Product> id_product;

}
