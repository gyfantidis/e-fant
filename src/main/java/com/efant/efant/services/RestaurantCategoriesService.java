package com.efant.efant.services;
import com.efant.efant.model.entities.RestaurantCategories;
import com.efant.efant.repositories.RestaurantCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantCategoriesService {

    private RestaurantCategoriesRepository restaurantCategoriesRepository;

    @Autowired
    public RestaurantCategoriesService(RestaurantCategoriesRepository restaurantCategoriesRepository){
        this.restaurantCategoriesRepository = restaurantCategoriesRepository;
    }

    public List<RestaurantCategories> getAllRestaurantCategories(){
        return restaurantCategoriesRepository.findAll();
    }

    public RestaurantCategories getRestaurantCategoriesById(Long id) throws Exception{
        return restaurantCategoriesRepository.findById(id)
                .orElseThrow(() -> new Exception("Restaurants Category not exists with id: " + id));

    }


    public RestaurantCategories createRestaurantCategories(RestaurantCategories restaurantCategories){
        restaurantCategories = restaurantCategoriesRepository.save(restaurantCategories);
        return restaurantCategories;
    }

    public void deleteRestaurantCategories(Long id) throws Exception{
        RestaurantCategories restaurantCategories = restaurantCategoriesRepository.findById(id).orElse(null);

        if(restaurantCategories != null){
            restaurantCategoriesRepository.deleteById(id);
        }
        else{
            throw new Exception("Restaurants Category not exists with id" +id);
        }



    }

}
