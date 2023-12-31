package com.efant.efant.model.entities;


import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "restaurants", schema = "efant")
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "restaurant_id")
    private Long restaurantId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "image_url")
    private String imageUrl;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;




    @ManyToMany
    @JoinTable(
            name="restaurant_category_mapping",
            schema = "efant",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    List<RestaurantCategories> restaurantCategories;




    @OneToMany(mappedBy="restaurant")
    private List<MenuItem> menuItems;

    //For Version 2

//    @ManyToMany
//    @JoinTable(
//            name="favorites",
//            schema = "efant",
//            joinColumns = @JoinColumn(name = "restaurant_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//
//    List<User> users;

    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews;



    // Constructors


    public Restaurant() {
    }

    public Restaurant(String name, String description, String address, String phone, String imageUrl, Timestamp createdAt) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }

    // Getters and Setters


    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<RestaurantCategories> getRestaurantCategories() {
        return restaurantCategories;
    }

    public void setRestaurantCategories(List<RestaurantCategories> restaurantCategories) {
        this.restaurantCategories = restaurantCategories;
    }


    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }


    // For Version 2
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    // equals and hashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(restaurantId, that.restaurantId) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(createdAt, that.createdAt) && Objects.equals(restaurantCategories, that.restaurantCategories) && Objects.equals(menuItems, that.menuItems) && Objects.equals(reviews, that.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, name, description, address, phone, imageUrl, createdAt, restaurantCategories, menuItems, reviews);
    }
}
