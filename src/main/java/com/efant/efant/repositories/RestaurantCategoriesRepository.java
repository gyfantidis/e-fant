package com.efant.efant.repositories;

import com.efant.efant.model.entities.RestaurantCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantCategoriesRepository extends JpaRepository<RestaurantCategories, Long> {
}
