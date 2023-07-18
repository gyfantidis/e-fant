package com.efant.efant.repositories;

import com.efant.efant.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {



    @Query(nativeQuery = true,
            value = "SELECT * FROM efant.reviews\n" +
                    "WHERE restaurant_id IN (:restaurantId)")
    public List<Review> getReviewsById(@Param("restaurantId") List<Long> restaurantId);

}
