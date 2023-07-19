package com.efant.efant.controllers;

import com.efant.efant.exeptions.EfantException;
import com.efant.efant.model.entities.User;
import com.efant.efant.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) throws Exception {
        return userService.getUserById(id);
    }


    @PostMapping("/users")
    @ResponseStatus(value = HttpStatus.CREATED)
    public User createUser(@RequestBody User user) throws Exception {
        user = userService.createUser(user);
        return user;
    }


    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) throws Exception {
        // validate
        if (!id.equals(user.getUser_id())) {
            throw new EfantException("USER_ID_MISMATCH", "ID in path and ID in body are not the same", HttpStatus.BAD_REQUEST);
        }
        user = userService.updateUser(user);

        return user;

    }


    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsers(@PathVariable Long id) throws Exception {
        userService.deleteUser(id);
    }
}
