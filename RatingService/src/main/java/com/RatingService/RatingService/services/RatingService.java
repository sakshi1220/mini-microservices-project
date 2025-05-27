package com.RatingService.RatingService.services;

import java.util.List;

import com.RatingService.RatingService.entity.Rating;

public interface RatingService {
     Rating createrating(Rating rating);

     List<Rating> getAllRatings();

     List<Rating> getAllRatingsByUserId(String Id);

     List<Rating> getAllRatingsByHotelId(String id);
}
