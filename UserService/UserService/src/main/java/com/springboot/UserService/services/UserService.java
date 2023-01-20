package com.springboot.UserService.services;

import com.springboot.UserService.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    //create user
    User saveUser(User user);

    //List all user
    List<User> getAllUsers();

    //get User by id
    User getUser(String userId);

    //delete
    void deleteUser(String userId);

    //update
    User UpdateUser(User user, String userId);
}
