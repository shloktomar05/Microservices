package com.springboot.rating.RatingService.services;

import com.springboot.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //Create Rating
    Rating createRating(Rating rating);


    //get All Rating
    List<Rating> getAllRating();


    //get All rating by hotel
    List<Rating> getAllRatingByHotelId(String hotelId);


    // get all rating by userid
    List<Rating> getAllRatingByUserId(String userId);
}
