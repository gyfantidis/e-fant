package com.efant.efant.controllers;

import com.efant.efant.exeptions.EfantException;
import com.efant.efant.model.dtos.UserDetailsDTO;
import com.efant.efant.model.entities.User;
import com.efant.efant.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
    public User getUserById(@PathVariable Long id, Authentication authentication) throws Exception {
        User user = userService.getUserById(id);
        return user;
    }


    @PostMapping("/users")
    @ResponseStatus(value = HttpStatus.CREATED)
    public User createUser(@RequestBody User user) throws Exception {
        user = userService.createUser(user);
        return user;
    }


    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user, Authentication authentication) throws Exception {

        isTheSameUserOrAdmir(id.intValue(), authentication);

        // validate
        if (!id.equals(user.getId())) {
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


    private boolean isTheSameUserOrAdmir(Integer userId, Authentication authentication) throws EfantException{
        User authUser = ((UserDetailsDTO)authentication.getPrincipal()).getUser();
        if (!(userId.equals(authUser.getId())
//                authUser.getRole().equals("ADMIN")
        )){
            throw new EfantException("USER_NOT_AUTHORIZED", "User is not authorized to update this user", HttpStatus.UNAUTHORIZED);
        }

        return true;

    }
}
