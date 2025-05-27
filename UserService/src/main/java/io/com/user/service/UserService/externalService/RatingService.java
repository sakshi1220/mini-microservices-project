package io.com.user.service.UserService.externalService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import io.com.user.service.UserService.entities.Rating;

@Service
@FeignClient(name="RATINGSERVICE")
public interface RatingService {
    @PostMapping("/ratings")
    public Rating createRating(Rating rating);

    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable String ratingId);

    @DeleteMapping("/delete/{ratingId}")
    public void deleRating(@PathVariable String ratingId);

    
}
