package com.efant.efant.services;

import com.efant.efant.model.entities.Restaurant;
import com.efant.efant.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantService(RestaurantRepository restRepo) {
        restaurantRepository = restRepo;
    }

    public List<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) throws Exception{
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new Exception("Restaurant not exists with id: " + id));
    }

    public Restaurant createRestaurant(Restaurant restaurant){
        restaurant = restaurantRepository.save(restaurant);
        return restaurant;
    }


    public Restaurant updateRestaurant(Restaurant restaurant) throws Exception{
        Long restaurantId = restaurant.getRestaurantId();
        Restaurant existingRestaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new Exception("Restaurant not exists with id: " + restaurantId));

        // Update the properties of the existingUser with the values from the updated user
        existingRestaurant.setName(restaurant.getName());
        existingRestaurant.setDescription(restaurant.getDescription());
        existingRestaurant.setAddress(restaurant.getAddress());
        existingRestaurant.setPhone(restaurant.getPhone());
        existingRestaurant.setImageUrl(restaurant.getImageUrl());

        existingRestaurant = restaurantRepository.save(existingRestaurant);
        return existingRestaurant;



    }

    public void deleteRestaurant(Long id) throws Exception{
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        if(restaurant != null){
            restaurantRepository.deleteById(id);
        }
        else{
            throw new Exception("Restaurant not exists with id" +id);
        }
    }


}
