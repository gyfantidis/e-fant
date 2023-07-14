package com.efant.efant.model.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurant_categories", schema = "efant", catalog = "postgres")
public class RestaurantCategories {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id")
    private long categoryId;
    @Basic
    @Column(name = "category_name")
    private String categoryName;

    @ManyToMany(mappedBy = "restaurantCategories")
    List<Restaurant> restaurants;

//    @OneToMany(mappedBy = "restaurantCategories")
//    List<RestaurantCategoryMapping> restaurantCategoryMappingList;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantCategories that = (RestaurantCategories) o;

        if (categoryId != that.categoryId) return false;
        if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (categoryId ^ (categoryId >>> 32));
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }
}
