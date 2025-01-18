package com.grocery.booking.controller;

import com.grocery.booking.domian.User;
import com.grocery.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }
}
