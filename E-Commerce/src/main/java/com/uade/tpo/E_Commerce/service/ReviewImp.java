package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Review;

import java.util.ArrayList;
import java.util.Optional;

public interface ReviewImp {

    public Optional<ArrayList<Review>> getReviewsById(long id_product);

    public Optional<ArrayList<Review>> CreateReview(String text, int stars,long id_product,long id_user);

    public boolean hasUserPurchased(long id_product, long id_user);

    public boolean alreadyReviewed(long id_user, long id_product);
}
