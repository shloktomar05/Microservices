package com.springboot.UserService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    private String ratingId;
    private String ussrId;
    private String hotelId;
    private int rating ;
    private String feedback;
    private Hotel hotel;

}
