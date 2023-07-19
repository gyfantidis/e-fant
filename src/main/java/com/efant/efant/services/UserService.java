package com.efant.efant.services;

import com.efant.efant.exeptions.EfantException;
import com.efant.efant.model.entities.User;
import com.efant.efant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // methods

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new EfantException("USER_NOT_FOUND", "User not exists with id: " + id, HttpStatus.NOT_FOUND));
    }

    public User createUser(User user) throws Exception {

        if (user.getUser_id() != null) {
            throw new EfantException("NEW_USER_ID_IS_NOT_NULL", "User id must be null", HttpStatus.BAD_REQUEST);
        }

        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new EfantException("USER_EMAIL_ALREADY_EXISTS", "User with email " + user.getEmail() + " already exists.", HttpStatus.BAD_REQUEST);
        }

        user = userRepository.save(user);
        return user;
    }


    public User updateUser(User user) throws Exception {
        Long userId = user.getUser_id();
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EfantException("USER_NOT_FOUND", "User not exists with id: " + userId, HttpStatus.NOT_FOUND));

        // Update the properties of the existingUser with the values from the updated user
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPhone(user.getPhone());
        existingUser.setCreatedAt(user.getCreatedAt());
        existingUser.setRoleId(user.getRoleId());


        // Save the updated user entity
        existingUser = userRepository.save(existingUser);

        return existingUser;
    }


    public void deleteUser(Long id) throws Exception {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            userRepository.deleteById(id);
        } else {
            throw new EfantException("USER_NOT_FOUND", "User not exists with id: " + id, HttpStatus.NOT_FOUND);
        }
    }


}
