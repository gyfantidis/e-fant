package com.efant.efant.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "addresses", schema = "efant")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addresses_address_id_seq")
    @SequenceGenerator(allocationSize = 1, name = "addresses_address_id_seq", sequenceName = "efant.addresses_address_id_seq")
    private Long addressId;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "address_number", length = 20)
    private String addressNumber;

    @Column(name = "floor", length = 20)
    private String floor;

    @Column(name = "ring_name", length = 50)
    private String ringName;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "zip_code", length = 10)
    private String zipCode;

    @Column(name = "comments", length = 200)
    private String comments;

    @Column(name = "user_id")
    private Long userId;


    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonBackReference
    private User user;


    // Constructors


    public Address() {
    }

    public Address(String address, String addressNumber, String floor, String ringName, String city, String state, String zipCode, String comments, Long userId) {
        this.address = address;
        this.addressNumber = addressNumber;
        this.floor = floor;
        this.ringName = ringName;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.comments = comments;
        this.userId = userId;
    }

    //getters, and setters

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRingName() {
        return ringName;
    }

    public void setRingName(String ringName) {
        this.ringName = ringName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // equals and hashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(addressId, address1.addressId) && Objects.equals(address, address1.address) && Objects.equals(addressNumber, address1.addressNumber) && Objects.equals(floor, address1.floor) && Objects.equals(ringName, address1.ringName) && Objects.equals(city, address1.city) && Objects.equals(state, address1.state) && Objects.equals(zipCode, address1.zipCode) && Objects.equals(comments, address1.comments) && Objects.equals(userId, address1.userId) && Objects.equals(user, address1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, address, addressNumber, floor, ringName, city, state, zipCode, comments, userId, user);
    }
}