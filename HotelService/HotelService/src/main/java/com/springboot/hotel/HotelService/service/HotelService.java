package com.springboot.hotel.HotelService.service;

import com.springboot.hotel.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create hotel
    Hotel createHotel(Hotel hotel);

    //get all hotel
    List<Hotel> allHotel();

    //get Hotel by id
    Hotel getHotel(String id);


}
