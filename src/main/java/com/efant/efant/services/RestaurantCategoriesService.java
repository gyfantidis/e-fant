package com.efant.efant.services;
import com.efant.efant.exeptions.EfantException;
import com.efant.efant.model.entities.RestaurantCategories;
import com.efant.efant.repositories.RestaurantCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
                .orElseThrow(() -> new EfantException("CATEGORY_NOT_FOUND", "Category not exists with id: " + id, HttpStatus.NOT_FOUND));

    }


    public RestaurantCategories createRestaurantCategories(RestaurantCategories restaurantCategories) throws Exception{
        if (restaurantCategories.getCategoryId() != null) {
            throw new EfantException("NEW_CATEGORY_ID_IS_NOT_NULL", "Categories id must be null", HttpStatus.BAD_REQUEST);
        }
        restaurantCategories = restaurantCategoriesRepository.save(restaurantCategories);
        return restaurantCategories;
    }

    public void deleteRestaurantCategories(Long id) throws Exception{
        RestaurantCategories restaurantCategories = restaurantCategoriesRepository.findById(id).orElse(null);

        if(restaurantCategories != null){
            restaurantCategoriesRepository.deleteById(id);
        }
        else{
                throw new EfantException("CATEGORY_NOT_FOUND", "Category not exists with id: " + id, HttpStatus.NOT_FOUND);
        }



    }

}
