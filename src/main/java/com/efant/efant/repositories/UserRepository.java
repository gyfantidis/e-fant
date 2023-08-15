package com.efant.efant.repositories;

import com.efant.efant.model.entities.RestaurantCategories;
import com.efant.efant.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true,
            value = "select * from efant.users where email = :email " )
    public Optional<User> findByEmail(@Param("email") String email);



}
