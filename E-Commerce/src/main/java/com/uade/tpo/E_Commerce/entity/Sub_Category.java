

package com.uade.tpo.E_Commerce.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Sub_Category {

    public Sub_Category(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_sub_category;
    
    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id_category") // El que tiene FK se le pone el joinColumn
    private Category category;

    @Column(name = "name_sub_category", length = 60)
    private String name_sub_category;

    @OneToMany(mappedBy = "sub_category")
    private List<Sub_categoryProduct> sub_categoryProduct;

    public long getId_sub_category() {
        return id_sub_category;
    }

    public Category getCategory() {
        return category;
    }

    public String getName_sub_category() {
        return name_sub_category;
    }

    public List<Sub_categoryProduct> getSub_categoryProduct() {
        return sub_categoryProduct;
    }
}
