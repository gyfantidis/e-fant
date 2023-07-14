package com.efant.efant.repositories;

import com.efant.efant.model.entities.RestaurantCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantCategoriesRepository extends JpaRepository<RestaurantCategories, Long> {



    @Query(nativeQuery = true,
            value = "SELECT * FROM efant.restaurant_categories\n" +
                    "WHERE category_name IN (:categoryNames)")
    public List<RestaurantCategories> getCategoriesName(@Param("categoryNames") List<String> categoryNames);

    @Query(nativeQuery = true,
            value = "SELECT * FROM efant.restaurant_categories\n" +
                    "WHERE category_id IN (:categoryId)")
    public List<RestaurantCategories> getCategoriesById(@Param("categoryId") List<Long> categoryId);


}
