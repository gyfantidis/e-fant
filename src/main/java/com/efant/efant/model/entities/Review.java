package com.efant.efant.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reviews", schema = "efant", catalog = "postgres")
public class Review {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "review_id")
    private long reviewId;
    @Basic
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "restaurant_id")
    private Long restaurantId;
    @Basic
    @Column(name = "rating")
    private Integer rating;
    @Basic
    @Column(name = "comment")
    private String comment;
    @Basic
    @Column(name = "review_date")
    private Timestamp reviewDate;


    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id" , insertable = false, updatable = false)
    private User user;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "restaurant_id", insertable = false, updatable = false)
    private Restaurant restaurant;

    // Constructors


    public Review() {
    }

    public Review(Long userId, Long restaurantId, Integer rating, String comment, Timestamp reviewDate) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    // Getters and Setters

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // equals and hashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return reviewId == review.reviewId && Objects.equals(userId, review.userId) && Objects.equals(restaurantId, review.restaurantId) && Objects.equals(rating, review.rating) && Objects.equals(comment, review.comment) && Objects.equals(reviewDate, review.reviewDate) && Objects.equals(user, review.user) && Objects.equals(restaurant, review.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, userId, restaurantId, rating, comment, reviewDate, user, restaurant);
    }
}
