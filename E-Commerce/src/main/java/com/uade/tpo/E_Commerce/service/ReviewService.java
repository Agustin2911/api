package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Review;
import com.uade.tpo.E_Commerce.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class ReviewService implements ReviewImp{

    @Autowired
    private ReviewRepository repository;


    @Override
    public Optional<ArrayList<Review>> getReviewsById(long id_product) {
        Optional<ArrayList<Review>> reviews=repository.ReviewsById(id_product);
        if(reviews.isPresent()){
            return  reviews;
        }
        else{
            return Optional.empty();
        }

    }


    @Override
    public Optional<ArrayList<Review>> CreateReview(String text, int stars, long id_product) {
        repository.createReview(text,stars,id_product);
        Optional<ArrayList<Review>> review=repository.ReviewById(text, stars, id_product);
        if(review.isPresent()){

            return review;
        }
        else {
            return Optional.empty();
        }
    }
}
