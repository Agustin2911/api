package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Sale;
import com.uade.tpo.E_Commerce.entity.dto.ItemsRequest;
import com.uade.tpo.E_Commerce.repository.ItemsRepository;
import com.uade.tpo.E_Commerce.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    public Optional<Sale> getSaleById(Long id_sale) {
       return saleRepository.findSaleById(id_sale);
    }

    public Sale createSale(int total_price, Long id_user, LocalDateTime sale_date, ArrayList<ItemsRequest> items) {

        Sale search_sale = saleRepository.findSaleByUserDate(id_user,sale_date);
        if(search_sale == null){
            int check = saleRepository.createNewSale(total_price, id_user, sale_date);
            if(check > 0){

                Sale new_sale = saleRepository.findSaleByUserDate(id_user,sale_date);
                Long new_sale_id =
                        saleRepository.findSaleByUserDate(new_sale.getBuyer_user().getId_user(),new_sale.getSale_date()).getId_sale();

                for (ItemsRequest item : items) {
                    int checkItem = itemsRepository.createNewItem(item.getId_product(), new_sale_id, item.getAmount());
                }
                return new_sale;
            }
            else {
                return null;
            }

        }else{
            return null;
        }

    }

    public Optional<Sale> updateSale(Long id_sale, int total_price, Long id_user, LocalDateTime sale_date) {
        Optional<Sale> search_sale = saleRepository.findSaleById(id_user);
        if(search_sale.isPresent()){
            int check = saleRepository.updateSale(id_sale,total_price,id_user,sale_date);
            if(check > 0){
                return saleRepository.findSaleById(id_sale);

            }else{
                return Optional.empty();
            }
        }else{
            return Optional.empty();
        }
//      new NotFoundException();
    }

    public boolean deleteSaleById(Long id_sale) {
        int check = saleRepository.deleteSale(id_sale);
        if(check > 0){
            Optional<Sale> check_sale = saleRepository.findSaleById(id_sale);
            return check_sale.isEmpty();
        }else{
            return false;
        }
    }
}
