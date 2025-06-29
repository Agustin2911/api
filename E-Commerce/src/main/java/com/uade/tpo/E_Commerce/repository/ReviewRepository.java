package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Review;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query(value="select * from Review where id_product=?1 ",nativeQuery = true)
    public Optional<ArrayList<Review>> ReviewsById(Long id);

    @Query(value="select count(i.id_items) from sale s  inner join items i on s.id_sale=i.id_sale inner join product p on p.id_product=i.id_product where i.id_product=?1 and s.id_user=?2 ;",nativeQuery = true)
    public int VerifyTheSale(long id_product, long id_user);

    @Query(value = "select * from review where id_product=?3  and text=?1 and stars=?2 ",nativeQuery = true)
    public Optional<ArrayList<Review>> ReviewById(String text,int stars,long id_product);

    @Query(value = "SELECT COUNT(r.id_product) FROM review r WHERE r.id_user = ?1 AND r.id_product = ?2", nativeQuery = true)
    public int checkReview(long id_user, long id_product);

    @Modifying
    @Transactional
    @Query(value="insert into Review (text,stars,id_product,id_user) values(?1 , ?2  , ?3, ?4)",nativeQuery = true)
    public int createReview(String text, int Stars , Long id_product, long id_user);

}
