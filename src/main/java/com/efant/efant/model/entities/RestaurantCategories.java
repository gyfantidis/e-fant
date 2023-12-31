package com.efant.efant.model.entities;


import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "restaurant_categories", schema = "efant", catalog = "postgres")
public class RestaurantCategories {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id")
    private Long categoryId;
    @Basic
    @Column(name = "category_name")
    private String categoryName;

    @ManyToMany
    @JoinTable(
            name="restaurant_category_mapping",
            schema = "efant",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
    List<Restaurant> restaurants;

    @Column(name = "icon", length = 100)
    private String icon;


    // Constructors


    public RestaurantCategories() {
    }

    public RestaurantCategories(String categoryName) {
        this.categoryName = categoryName;
    }



    // Getters and Setters


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    // equals and hashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantCategories that = (RestaurantCategories) o;
        return Objects.equals(categoryId, that.categoryId) && Objects.equals(categoryName, that.categoryName) && Objects.equals(restaurants, that.restaurants) && Objects.equals(icon, that.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName, restaurants, icon);
    }
}
