package io.com.user.service.UserService.controller;

import java.util.List;

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

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser= userService.saveUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User newUser= userService.getUser(userId);
        return  ResponseEntity.ok(newUser);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
       List<User> newUser= userService.getAllUser();
        return  ResponseEntity.ok(newUser);
    }
}
