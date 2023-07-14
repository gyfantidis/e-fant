package com.efant.efant.repositories;

import com.efant.efant.model.entities.RestaurantCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantCategoriesRepository extends JpaRepository<RestaurantCategories, Long> {
//
//    @Query(nativeQuery = true,
//            value = "SELECT * FROM efant.restaurant_categories\n" +
//                    "WHERE category_name IN (:?) ")
//    public List<RestaurantCategories> getCategoriesName(List<String> categoryNames);

//    @Query(nativeQuery = true,
//            value = "select * from bootcamp2306.users where email = :? " )
//    public User findByEmail(String email);


    @Query(nativeQuery = true,
            value = "SELECT * FROM efant.restaurant_categories\n" +
                    "WHERE category_name IN (:categoryNames)")
    public List<RestaurantCategories> getCategoriesName(@Param("categoryNames") List<String> categoryNames);


}
