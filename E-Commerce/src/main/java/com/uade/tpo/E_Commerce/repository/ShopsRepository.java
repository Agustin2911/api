package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Shops;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

public interface ShopsRepository extends JpaRepository<Shops,Long> {

    @Query(value="select * from Shops where id_Shop= ?1 ",nativeQuery = true)
    public Optional<Shops> getShpById(long shop);

    @Query(value="select * from Shops where city= ?1  and street= ?2 ",nativeQuery = true)
    public Optional<Shops> getShpByCityAndStreet(String city , String street);

    @Query(value = "select s.* from shops s inner join company_shops c on s.id_shop=c.id_shop where c.id_user=?1 ",nativeQuery = true)
    public Optional<ArrayList<Shops>> getShopsByCompanyId(long id_company);

    @Modifying
    @Transactional
    @Query(value = "insert into Shops (city,street) values(?1 , ?2 )",nativeQuery = true)
    public int insertSHop(String city,String street);


    @Modifying
    @Transactional
    @Query(value="update Shops set city= ?2 , street=?3  where id_Shop= ?1 ",nativeQuery = true)
    public int ModifySHop(long id_shop,String new_city,String new_street );


    @Modifying
    @Transactional
    @Query(value="delete from Shops where id_shop=?1 ",nativeQuery = true)
    public int DeleteShop(long id_shop);

}
