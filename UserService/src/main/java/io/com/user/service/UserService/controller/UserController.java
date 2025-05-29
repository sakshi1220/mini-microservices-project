package io.com.user.service.UserService.controller;

import java.util.List;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.com.user.service.UserService.entities.User;
import io.com.user.service.UserService.services.UserService;
import io.com.user.service.UserService.services.UserServiceImplementation;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
        private Logger logger= LoggerFactory.getLogger(UserServiceImplementation.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser= userService.saveUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    int retryCount=1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name="RatingHotelBreaker", fallbackMethod = "ratingFallbackMethod")
    //** trying here with Retry **//


//    @Retry(name="RatingHotelService", fallbackMethod = "ratingFallbackMethod")
    @RateLimiter(name = "UserRateLimiter", fallbackMethod = "ratingFallbackMethod")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        logger.info("RetryCount: {} ",retryCount);
        retryCount++;
        User newUser= userService.getUser(userId);
        return  ResponseEntity.ok(newUser);
    }
    // creating fallback method for circuit breaker
    public ResponseEntity<User> ratingFallbackMethod(String userId, Exception ex){
        logger.info("Fallback is executed because service is down", ex.getMessage());
        User user=User.builder().email("sakshi89@gmail.com").name("Sakshi").about("Im a dummy user becuase service is down").build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
       List<User> newUser= userService.getAllUser();
        return  ResponseEntity.ok(newUser);
    }
}
