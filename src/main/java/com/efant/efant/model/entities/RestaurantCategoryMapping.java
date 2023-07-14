package com.efant.efant.model.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "restaurant_category_mapping", schema = "efant", catalog = "postgres")
public class RestaurantCategoryMapping {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "mapping_id")
    private long mappingId;




    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private RestaurantCategories restaurantCategories;

    public RestaurantCategoryMapping() {
    }

    public RestaurantCategoryMapping(long mappingId, Restaurant restaurant, RestaurantCategories restaurantCategories) {
        this.mappingId = mappingId;
        this.restaurant = restaurant;
        this.restaurantCategories = restaurantCategories;
    }



    public long getMappingId() {
        return mappingId;
    }

    public void setMappingId(long mappingId) {
        this.mappingId = mappingId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantCategories getRestaurantCategories() {
        return restaurantCategories;
    }

    public void setRestaurantCategories(RestaurantCategories restaurantCategories) {
        this.restaurantCategories = restaurantCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantCategoryMapping that = (RestaurantCategoryMapping) o;
        return mappingId == that.mappingId && Objects.equals(restaurant, that.restaurant) && Objects.equals(restaurantCategories, that.restaurantCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mappingId, restaurant, restaurantCategories);
    }
}
