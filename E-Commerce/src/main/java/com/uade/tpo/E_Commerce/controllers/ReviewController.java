package com.uade.tpo.E_Commerce.controllers;
import com.uade.tpo.E_Commerce.entity.Review;
import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.entity.dto.ReviewData;
import com.uade.tpo.E_Commerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @GetMapping("/{id_product}")
    public ResponseEntity<Object> getReviews(@PathVariable long id_product) {
        Optional<ArrayList<Review>> respond = service.getReviewsById(id_product);
        if (!respond.get().isEmpty()) {
            ArrayList<Review> reviews = respond.get();
            return ResponseEntity.ok(reviews);
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("the system haven't found any reviews of the product"));
        }
    }

    @GetMapping("/can-post-review/{id_product}/{id_user}")
    public ResponseEntity<Boolean> canPostReview(@PathVariable long id_product, @PathVariable long id_user) {

        boolean canReview = service.hasUserPurchased(id_product, id_user);
        return ResponseEntity.ok(canReview);
    }

    @GetMapping("/already-reviewed/{id_product}/{id_user}")
    public ResponseEntity<Boolean> alreadyReviewed(@PathVariable long id_user, @PathVariable long id_product) {

        boolean alreadyReviewed = service.alreadyReviewed(id_user, id_product);
        return ResponseEntity.ok(alreadyReviewed);
    }

    @PostMapping
    public ResponseEntity<Object> postReview(@RequestBody ReviewData review) {

        Optional<ArrayList<Review>> respond = service.CreateReview(review.getText(), review.getStars(), review.getId_product(),review.getId_user());
        if (respond.isPresent()) {
            if(! respond.get().isEmpty()) {
                return ResponseEntity.ok(respond.get());
            }
            else{
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("the System couldn't create the review"));
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(new FailedResponse("the System couldn't create the review"));
        }
    }

}
