package com.efant.efant.controllers;

import com.efant.efant.model.entities.User;
import com.efant.efant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService us){
        userService=us;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) throws Exception {
        return userService.getUserById(id);
    }



    @PostMapping("/users")
    @ResponseStatus(value= HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        user = userService.createUser(user);
        return user;
    }


    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) throws Exception{
        // validate
        if (!id.equals(user.getUser_id())){
            throw new Exception("ID in path and ID in body are not the same");
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
