package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Sub_Category;
import com.uade.tpo.E_Commerce.entity.Sub_categoryProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface Sub_CategoryProductRepository extends JpaRepository<Sub_categoryProduct, Sub_Category> {

    @Modifying
    @Transactional
    @Query(value="insert into Sub_category_product (id_sub_category,id_product) values(?1 , ?2 )",nativeQuery = true)
    public int addProduct(long id_sub_category, long id_product);


    @Modifying
    @Transactional
    @Query(value="delete from Sub_category_product where id_product=?1 ",nativeQuery = true)
    public int deleteProduct(long id_product);



    @Modifying
    @Transactional
    @Query(value="delete from Sub_category_product where id_sub_category=?1 ",nativeQuery = true)
    public int deleteSub_category(long id_sub_category);

}
