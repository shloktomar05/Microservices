package com.springboot.UserService.services.impl;

import com.springboot.UserService.entities.Hotel;
import com.springboot.UserService.entities.Rating;
import com.springboot.UserService.entities.User;
import com.springboot.UserService.exceptions.ResourceNotFoundException;
import com.springboot.UserService.repositories.UserRepository;
import com.springboot.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public User saveUser(User user) {
        //generate  unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() { return userRepository.findAll();  }

    @Override
    public User getUser(String userId) {
        User user =  userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("user with given Id is not found : "+ userId));

        // calling rating microservice to get rating into userservice
        //fetch rating by userId
        //http://localhost:8083/rating/users/1292dd0a-86c7-40b6-80d0-42e54441c70e

        Rating[] ratingforUser = restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+ user.getUserId(), Rating[].class);
        logger.info("{}",ratingforUser);

        List<Rating> ratings = Arrays.stream(ratingforUser).toList();

       // map every hotel to rating
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api calling hotel microservice and get hotels into our rating microservice
            //http://localhost:8082/hotel/5f8f70c4-cafc-4b5b-840a-965da94cc90d
            ResponseEntity<Hotel> getEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+ rating.getHotelId(), Hotel.class);
            Hotel hotel = getEntity.getBody();
            logger.info("Response Status code : {}",getEntity.getStatusCode());
            // set hotel to rating
            rating.setHotel(hotel);
            // return rating
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public void deleteUser(String userId) {
       User user1 =  userRepository.findById(userId).
               orElseThrow(()-> new ResourceNotFoundException("user with given Id is not found : "+ userId));
       this.userRepository.delete(user1);
    }

    @Override
    public User UpdateUser(User user, String userId) {
        User user1 = this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user with given Id is not found : "+ userId));


        return null;
    }


}
