package com.efant.efant.controllers;

import com.efant.efant.model.entities.Review;
import com.efant.efant.services.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }


    @GetMapping("/reviews")
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }


    @GetMapping("/reviews/{id}")
    public Review getReviewById(@PathVariable Long id) throws Exception{
        return reviewService.getReviewById(id);
    }

    @PostMapping("/reviews")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Review createReview(@RequestBody Review review) throws Exception{
        review = reviewService.createReview(review);
        return review;
    }


    @PutMapping("/reviews/{id}")
    public Review updateReview(@PathVariable Long id, @RequestBody Review review)throws Exception{
        if (!id.equals(review.getReviewId())){
            throw new Exception("ID in path and ID in body are not the same");
        }

        review = reviewService.updateReview(review);
        return review;
    }


    @DeleteMapping("/reviews/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable Long id) throws Exception{
        reviewService.deleteReview(id);
    }

































}
