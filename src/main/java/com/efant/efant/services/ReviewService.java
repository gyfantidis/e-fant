package com.efant.efant.services;

import com.efant.efant.exeptions.EfantException;
import com.efant.efant.model.entities.Review;
import com.efant.efant.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
                .orElseThrow(() -> new EfantException("REVIEW_NOT_FOUND", "Review not exists with id: " + id, HttpStatus.NOT_FOUND));

    }

    public Review createReview(Review review) throws Exception{
        if (review.getReviewId() != null) {
            throw new EfantException("NEW_REVIEWS_ID_IS_NOT_NULL", "Reviews id must be null", HttpStatus.BAD_REQUEST);
        }
        review = reviewRepository.save(review);
        return review;
    }



    public Review updateReview(Review review) throws Exception{
        Long reviewId = review.getReviewId();
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EfantException("REVIEW_NOT_FOUND", "Review not exists with id: " + reviewId, HttpStatus.NOT_FOUND));

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
            throw new EfantException("REVIEW_NOT_FOUND", "Review not exists with id: " + id, HttpStatus.NOT_FOUND);

        }
    }




























}
