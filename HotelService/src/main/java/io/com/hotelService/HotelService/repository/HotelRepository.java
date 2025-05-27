package io.com.hotelService.HotelService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.com.hotelService.HotelService.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,String> {
    
}
