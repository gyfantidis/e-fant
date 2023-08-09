package com.efant.efant.repositories;

import com.efant.efant.model.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    //    List<Restaurant> findByMenuItemId(Long itemId);
    List<Restaurant> findByRestaurantCategoriesCategoryId(Long categoryId);
}
