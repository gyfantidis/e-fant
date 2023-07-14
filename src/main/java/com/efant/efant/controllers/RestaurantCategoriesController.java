package com.efant.efant.controllers;

import com.efant.efant.model.entities.RestaurantCategories;
import com.efant.efant.services.RestaurantCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantCategoriesController {

    private RestaurantCategoriesService restaurantCategoriesService;

    @Autowired
    public RestaurantCategoriesController(RestaurantCategoriesService restaurantCategoriesService){
        this.restaurantCategoriesService=restaurantCategoriesService;
    }

    @GetMapping("/restaurants/categories")
    public List<RestaurantCategories> getRestaurantCategories(){
        return restaurantCategoriesService.getAllRestaurantCategories();
    }

    @GetMapping("/restaurants/categories/{id}")
    public RestaurantCategories GetRestaurantCategoriesById(@PathVariable Long id) throws Exception{
        return restaurantCategoriesService.getRestaurantCategoriesById(id);
    }


    @PostMapping ("/restaurants/categories")
    @ResponseStatus(value = HttpStatus.CREATED)
    public RestaurantCategories createRestaurantCategories(@RequestBody RestaurantCategories restaurantCategories){
        restaurantCategories = restaurantCategoriesService.createRestaurantCategories(restaurantCategories);
        return restaurantCategories;
    }

    @DeleteMapping("/restaurants/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurantCategory(@PathVariable Long id)throws Exception{
        restaurantCategoriesService.deleteRestaurantCategories(id);
    }




}
