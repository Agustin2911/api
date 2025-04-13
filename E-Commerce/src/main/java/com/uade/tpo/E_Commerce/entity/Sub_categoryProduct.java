package com.uade.tpo.E_Commerce.entity;


import java.util.List;

import com.uade.tpo.E_Commerce.entity.dto.CompoundKeySub_categoryProduct;
import com.uade.tpo.E_Commerce.entity.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Sub_categoryProduct {

    public Sub_categoryProduct(){}

    @EmbeddedId
    private CompoundKeySub_categoryProduct id;

    @ManyToOne
    @MapsId("id_sub_category")
    @JoinColumn(name="id_sub_category")
    private Sub_Category sub_category;

    @ManyToOne
    @MapsId("id_product")
    @JoinColumn(name="id_product")
    private Product product;

    public CompoundKeySub_categoryProduct getId() {
        return id;
    }

    public Sub_Category getSub_category() {
        return sub_category;
    }

    public Product getProduct() {
        return product;
    }
}
