package com.springboot.hotel.HotelService.service.Impl;

import com.springboot.hotel.HotelService.entities.Hotel;
import com.springboot.hotel.HotelService.exception.ResourceNotFoundException;
import com.springboot.hotel.HotelService.repositories.HotelRepository;
import com.springboot.hotel.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public Hotel createHotel(Hotel hotel) {
        String HotelId = UUID.randomUUID().toString();
        hotel.setId(HotelId);
        Hotel newHotel = this.hotelRepository.save(hotel);
        return newHotel;
    }

    @Override
    public List<Hotel> allHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel with given id not found !!"));
    }
}
