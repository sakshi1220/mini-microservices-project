package io.com.hotelService.HotelService.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.com.hotelService.HotelService.entity.Hotel;
import io.com.hotelService.HotelService.exception.ResourceNotFoundException;
import io.com.hotelService.HotelService.repository.HotelRepository;

@Service
public class HotelServiceImplementation implements HotelService{
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId= UUID.randomUUID().toString();
        hotel.setId(hotelId);
       return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) throws ResourceNotFoundException {
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel with given id not found"));
    }
    
}
