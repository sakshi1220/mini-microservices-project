package io.com.hotelService.HotelService.services;

import java.util.List;

import io.com.hotelService.HotelService.entity.Hotel;
import io.com.hotelService.HotelService.exception.ResourceNotFoundException;

public interface HotelService {

    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel getHotel(String id) throws ResourceNotFoundException;
} 
