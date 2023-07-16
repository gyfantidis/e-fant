package com.efant.efant.services;

import com.efant.efant.model.entities.Review;
import com.efant.efant.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }


    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id)throws Exception{
        return reviewRepository.findById(id)
                .orElseThrow(() -> new Exception("Review not exists with id: " + id));

    }

    public Review createReview(Review review){
        review = reviewRepository.save(review);
        return review;
    }



    public Review updateReview(Review review) throws Exception{
        Long reviewId = review.getReviewId();
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new Exception("Review not exists with id: " + reviewId));

        existingReview.setUserId(review.getUserId());
        existingReview.setRestaurantId(review.getRestaurantId());
        existingReview.setRating(review.getRating());
        existingReview.setComment(review.getComment());
        existingReview.setReviewDate(review.getReviewDate());

        existingReview = reviewRepository.save(existingReview);
        return existingReview;

    }




    public void deleteReview(Long id) throws Exception{
        Review review = reviewRepository.findById(id).orElse(null);

        if(review != null){
            reviewRepository.deleteById(id);
        }else{
            throw new Exception("Review not exists with id:" + id);

        }
    }




























}
