package com.springboot.hotel.HotelService.repositories;

import com.springboot.hotel.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
