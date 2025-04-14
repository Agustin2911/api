
package com.uade.tpo.E_Commerce.entity.dto;

import lombok.Data;

@Data
public class CompoundKeySub_categoryProduct {
    private Long id_sub_category;
    private Long id_product;

    public Long getId_sub_category() {
        return id_sub_category;
    }

    public Long getId_product() {
        return id_product;
    }
}
