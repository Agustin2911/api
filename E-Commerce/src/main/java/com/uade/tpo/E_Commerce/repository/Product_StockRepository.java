package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Product;
import com.uade.tpo.E_Commerce.entity.Product_Stock;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Product_StockRepository extends JpaRepository<Product_Stock, Product> {

    @Query(value="select * from product_stock where id_product=?1 ",nativeQuery = true)
    public Optional<Product_Stock> searchStock(long id);

    @Query(value = "select * from product_stock", nativeQuery = true)
    Optional<List<Product_Stock>> findAllStocks();

    @Modifying
    @Transactional
    @Query(value = "insert into product_stock (id_product,stock,stock_warning) values(?1 , ?2 , ?3 )",nativeQuery = true)
    public int CreateStock(long id , int stock_entry, int stock_warning);


    @Modifying
    @Transactional
    @Query(value="update product_stock set stock = ?2  where id_product =?1 ",nativeQuery = true)
    public int modifyStock(long id, int new_stock);

    @Modifying
    @Transactional
    @Query(value="update product_stock set stock_warning = ?2  where id_product= ?1 ",nativeQuery = true)
    public int  modifyStockWarning(long id , int new_stock);

    @Modifying
    @Transactional
    @Query(value="delete from product_stock where id_product= ?1 ",nativeQuery = true)
    public  int deleteProductStock(long id);


}
