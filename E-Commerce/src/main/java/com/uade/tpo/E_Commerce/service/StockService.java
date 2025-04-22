
package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Product_Stock;
import com.uade.tpo.E_Commerce.entity.Shop_Stock;
import com.uade.tpo.E_Commerce.entity.dto.CreateProductStockDTO;
import com.uade.tpo.E_Commerce.entity.dto.ModifyStockResponse;
import com.uade.tpo.E_Commerce.repository.Product_StockRepository;
import com.uade.tpo.E_Commerce.repository.Shop_StockRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StockService implements  StockImp{

    @Autowired
    private Product_StockRepository repository1;

    @Autowired
    private Shop_StockRepository repository2;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<Product_Stock> getStockOfAProductById(long id) {
        Optional<Product_Stock> product=repository1.searchStock(id);

        if (product.isPresent()){
            return product;
        }

        return Optional.empty();
    }

    @Override
    public Optional<CreateProductStockDTO> createProduct_Stock(long id, int stock_entry, long shop , int stock_warning) {

        Optional<Product_Stock> product=repository1.searchStock(id);
        Optional<Shop_Stock> shop_exist = repository2.searchByProductIdAndShopId(id,shop);

        if(product.isPresent() && shop_exist.isPresent()){
            return  Optional.of(new CreateProductStockDTO(product.get().getId(),product.get().getStock(),shop_exist.get().getId().getId_shop(),product.get().getStock_warning()));
        }


        repository1.CreateStock(id, stock_entry, stock_warning);
        repository2.createShop_stock(id, shop, stock_entry);

        product=repository1.searchStock(id);
        shop_exist=repository2.searchByProductIdAndShopId(id,shop);

        if(product.isPresent() && shop_exist.isPresent()) {
            return  Optional.of(new CreateProductStockDTO(product.get().getId(),product.get().getStock(),shop_exist.get().getId().getId_shop(),product.get().getStock_warning()));
        }

        else {
            return Optional.empty();
        }
    }


    public Optional<ArrayList<Shop_Stock>> getTheLocationOfTheProduct(long id) {

        Optional<Product_Stock> product= repository1.searchStock((id));
        if(!product.isPresent()){
            return Optional.empty();
        }

        else {
            return repository2.productLocation(id);

        }

    }

    @Override
    public Optional<Shop_Stock> createShopStock(long id_product, int stock, long id_shop) {

        Optional<Shop_Stock> shop=repository2.searchByProductIdAndShopId(id_product,id_shop);

        if(shop.isPresent()){
            return shop;
        }

        else{
            repository2.createShop_stock(id_product,id_shop,stock);

            Optional<Product_Stock> product=repository1.searchStock(id_product);
            int new_amount=product.get().getStock()+stock;
            repository1.modifyStock(id_product,new_amount);

            return repository2.searchByProductIdAndShopId(id_product,id_shop);
        }


    }


    //se introduce +5 en new stock si hay 5 nuevas unidades o -5 si salieron 5 unidades
    @Transactional
    @Override
    public Optional<Product_Stock> modifyStock(long id, long id_shop, int new_stock) {

        Optional<Product_Stock> stock=repository1.searchStock(id);
        Optional<Shop_Stock> shop=repository2.searchByProductIdAndShopId(id,id_shop);

        if(!stock.isPresent() || !shop.isPresent()){
            return Optional.empty();
        }

        else{


            repository1.modifyStock(id,stock.get().getStock()+new_stock);
            entityManager.flush();

            repository2.updateShop_stock(id,id_shop,shop.get().getStock()+new_stock);
            entityManager.flush();
            entityManager.clear();

            Optional<Product_Stock> product_stock =repository1.searchStock(id);

            return product_stock;
        }

    }

    @Override
    public Optional<Product_Stock> modifyStock_warning(long id, int Stock_warning) {

        Optional<Product_Stock> product=repository1.searchStock(id);
        if(product.isPresent()){
            repository1.modifyStockWarning(id,Stock_warning);
            product.get().setStock_warning(Stock_warning);
            return product;
        }
        else {
            return Optional.empty();
        }

    }


}

