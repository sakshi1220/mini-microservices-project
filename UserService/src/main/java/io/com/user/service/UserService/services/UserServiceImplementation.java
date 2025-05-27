package io.com.user.service.UserService.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.com.user.service.UserService.entities.Hotel;
import io.com.user.service.UserService.entities.Rating;
import io.com.user.service.UserService.entities.User;
import io.com.user.service.UserService.exceptions.ResourceNotFoundException;
import io.com.user.service.UserService.externalService.HotelService;
import io.com.user.service.UserService.repositories.UserRepository;


@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImplementation.class);
    @Override
    public User saveUser(io.com.user.service.UserService.entities.User user) {
       String randomId= UUID.randomUUID().toString();
       user.setUserId(randomId);
       return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
       return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Given Id not found on server " + userId));
        Rating[] ratingsOfUser=restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+ user.getUserId() , Rating[].class);
        List<Rating> ratings=Arrays.stream(ratingsOfUser).toList();
        logger.info("{} ",ratings);
        List<Rating> ratingList=ratings.stream().map(rating->{
            // ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class); 
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
    
}
