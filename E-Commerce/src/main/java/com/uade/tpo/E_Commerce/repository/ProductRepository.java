package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query(value = "select * from product",nativeQuery = true)
    public Optional<ArrayList<Product>> allTheProducts();

    @Query(value="select * from product where id_product=?1",nativeQuery = true)
    public Optional<Product> productByID(long id_product);


    @Query(value = "select * from product where product_name=?1  and photo_url=?2  and price=?3 and description=?4 and discount_state= ?5  and discount=?6 ",nativeQuery = true)
    public Optional<Product> productsByAtribiutes(String name, String photo_url, int price, String description, String discount_state, int discount);

    @Query(value = "select p.* from product p  inner join Sub_category_Product s on s.id_product=p.id_product where id_sub_category= ?1 ",nativeQuery = true)
    public Optional<ArrayList<Product>> productsBySubcategory(long id);

    @Query(value="select p.* from product p  inner join Sub_category_Product s on s.id_product=p.id_product inner join sub_category sc on sc.id_sub_category=s.id_sub_category where id_category=?1 ",nativeQuery = true)
    public Optional<ArrayList<Product>> productsByCategory(long id);


    @Modifying
    @Transactional
    @Query(value="insert into product (product_name,photo_url,price,description,discount_state,discount) values(?1 , ?2  ,?3 , ?4 , ?5  , ?6 )",nativeQuery = true)
    public int CreateProduct(String name , String url , int price, String description, String discount_state,int discount);

    @Modifying
    @Transactional
    @Query(value="update product set product_name=?2 , photo_url=?3  , price =?4  , description=?5  , discount_state= ?6 , discount=?7  where id_product= ?1 ",nativeQuery = true)
    public int modifyProduct(long id, String name, String url, int price, String description,String discount_state,int discount);


    @Modifying
    @Transactional
    @Query(value = "delete from product where id_product=?1 ",nativeQuery = true)
    public int deleteProduct(long id);

}
