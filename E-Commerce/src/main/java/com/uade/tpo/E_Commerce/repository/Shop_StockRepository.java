package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Product;
import com.uade.tpo.E_Commerce.entity.Shop_Stock;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

public interface Shop_StockRepository extends JpaRepository<Shop_Stock, Product> {

    @Query(value="select * from shop_stock where id_product=?1 ",nativeQuery = true)
    public Optional<ArrayList<Shop_Stock>> productLocation(long id);


    @Query(value="select * from shop_stock where id_product= ?1 and id_shop= ?2",nativeQuery = true)
    public Optional<Shop_Stock> searchByProductIdAndShopId(long id , long id_shop);


    @Modifying
    @Transactional
    @Query(value="insert into shop_stock (id_product, id_shop, stock) values(?1  , ?2  , ?3 )",nativeQuery = true)
    public int createShop_stock(long id,long shop , int stock);

    @Modifying
    @Transactional
    @Query(value="update shop_stock set stock=?3  where id_product=?1 and id_shop=?2",nativeQuery = true)
    public int updateShop_stock(long id,long shop, int new_stock);

    @Modifying
    @Transactional
    @Query(value="delete from shop_stock where id_shop=?1 ",nativeQuery = true)
    public int deleteShop_stock(long id);


}
