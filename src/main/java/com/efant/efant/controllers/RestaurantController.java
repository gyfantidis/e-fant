package com.efant.efant.controllers;

import com.efant.efant.model.entities.Restaurant;
import com.efant.efant.model.entities.Review;
import com.efant.efant.services.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants(/*@PathVariable String categoryId*/) {
//        if (categoryId != null) {
//            return restaurantService.getByCategoryId(categoryId);
//        }

        return restaurantService.getAllRestaurants();
    }


//    @GetMapping("/restaurants/items/{id}")
//    public List<Restaurant> getRestaurantsByItemId(@PathVariable Long id) throws Exception {
//        try {
//            List<Restaurant> restaurants = restaurantService.getRestaurantsByItemId(id);
//            if (restaurants.isEmpty()) {
//                return null;
//            }
//            return restaurants;
//        } catch (Exception e) {
//            return null;
//        }
//    }

    @GetMapping("/restaurants/category/{id}")
    public List<Restaurant> getRestaurantByCategory(@PathVariable Long id) throws Exception{
        try {
            List<Restaurant> restaurants = restaurantService.getRestaurantsByCategoryId(id);
            return restaurants;
        } catch (Exception e) {
            // Handle the exception, return appropriate response or status code
            e.printStackTrace();
            return null; // For simplicity, you might want to improve error handling
        }

    }

        @GetMapping("/restaurants/{id}")
        public Restaurant getRestaurantById (@PathVariable Long id) throws Exception {
            return restaurantService.getRestaurantById(id);
        }

        @GetMapping("/restaurants/reviews/{id}")
        public List<Review> getRestaurantReviews (@PathVariable Long id) throws Exception {
            return restaurantService.getRestaurantReviews(id);
        }

        @PostMapping("/restaurants")
        @ResponseStatus(value = HttpStatus.CREATED)
        public Restaurant createRestaurant (@RequestBody Restaurant restaurant) throws Exception {
            restaurant = restaurantService.createRestaurant(restaurant);
            return restaurant;
        }

        @PutMapping("/restaurants/{id}")
        public Restaurant updateRestaurant (@PathVariable Long id, @RequestBody Restaurant restaurant) throws Exception
        {

            // validate
            if (!id.equals(restaurant.getRestaurantId())) {
                throw new Exception("ID in path and ID in body are not the same");
            }

            restaurant = restaurantService.updateRestaurant(restaurant);
            return restaurant;
        }

        @DeleteMapping("/restaurants/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteRestaurant (@PathVariable Long id) throws Exception {
            restaurantService.deleteRestaurant(id);
        }


    }
