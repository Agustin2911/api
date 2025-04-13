package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Company_Shops;
import com.uade.tpo.E_Commerce.entity.Seller_User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface Company_ShopsRepository extends JpaRepository<Company_Shops, Seller_User> {

    @Transactional
    @Modifying
    @Query(value = "insert into Company_shops (id_user,id_shop) values(?1 ,?2 )",nativeQuery = true)
    public int addShop(long id_user, long id_shop);

    @Modifying
    @Transactional
    @Query(value="delete from Company_Shops where id_shop=?1 ",nativeQuery = true)
    public int deleteShop(long id_shop);


}
