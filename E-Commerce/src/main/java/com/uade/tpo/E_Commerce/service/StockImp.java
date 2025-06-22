
package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Product_Stock;
import com.uade.tpo.E_Commerce.entity.Shop_Stock;
import com.uade.tpo.E_Commerce.entity.dto.CreateProductStockDTO;
import com.uade.tpo.E_Commerce.entity.dto.ModifyStockResponse;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

public interface StockImp {

    public Optional<Product_Stock> getStockOfAProductById(long id);

    public Optional<CreateProductStockDTO> createProduct_Stock(long id, int stock_entry, long shop, int stock_warning);

    public Optional<ArrayList<Shop_Stock>> getTheLocationOfTheProduct(long id);

    public Optional<Shop_Stock> createShopStock(long id_product,int stock,long id_shop);

    public Optional<Product_Stock> modifyStock(long id, int new_stock);

    public Optional<Product_Stock> modifyStock_warning(long id,int Stock_warning);


    
}



