package com.RatingService.RatingService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RatingService.RatingService.entity.Rating;
import com.RatingService.RatingService.repository.RatingRepository;

@Service
public class RatingServiceImplementation implements RatingService{
    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating createrating(Rating rating) {
       return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllRatingsByUserId(String Id) {
        return ratingRepository.findByUserId(Id);
    }

    @Override
    public List<Rating> getAllRatingsByHotelId(String id) {
       return ratingRepository.findByHotelId(id);
    }
    
}
