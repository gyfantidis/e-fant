package com.efant.efant.controllers;

import com.efant.efant.model.entities.Restaurant;
import com.efant.efant.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restServ) {
        restaurantService = restServ;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) throws Exception{
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping("/restaurants")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant){
        restaurant = restaurantService.createRestaurant(restaurant);
        return restaurant;
    }

    @PutMapping("/restaurants/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) throws Exception{

        // validate
        if (!id.equals(restaurant.getRestaurantId())){
            throw new Exception("ID in path and ID in body are not the same");
        }

        restaurant = restaurantService.updateRestaurant(restaurant);
        return restaurant;
    }

    @DeleteMapping("/restaurants/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable Long id) throws Exception{
        restaurantService.deleteRestaurant(id);
    }


}
