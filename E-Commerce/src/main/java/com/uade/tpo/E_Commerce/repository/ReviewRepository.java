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

    @Query(value = "select * from review where id_product=?3  and text=?1 and stars=?2 ",nativeQuery = true)
    public Optional<ArrayList<Review>> ReviewById(String text,int stars,long id_product);

    @Modifying
    @Transactional
    @Query(value="insert into Review (text,stars,id_product) values(?1 , ?2  , ?3)",nativeQuery = true)
    public int createReview(String text, int Stars , Long id_product);

}
