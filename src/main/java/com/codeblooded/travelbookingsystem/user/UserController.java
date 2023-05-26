package com.codeblooded.travelbookingsystem.user;

import com.codeblooded.travelbookingsystem.service.EmailService;
import com.codeblooded.travelbookingsystem.travelpackages.TravelPackage;
import com.codeblooded.travelbookingsystem.travelpackages.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/customers")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String response = userService.createUser(user);
        if(response == UserService.USER_ALREADY_EXISTS) {
            return new ResponseEntity<String>(response, HttpStatus.OK);
        }

        return new ResponseEntity<String>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUserProfile(@PathVariable("userId") String userId, @RequestBody User user) {
        String response = userService.updateUserProfile(Integer.parseInt(userId), user);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody UserProfile userProfile) {
        User user = userService.loginUser(userProfile);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return new ResponseEntity<Iterable<User>>(userService.getAllUsers(), HttpStatus.OK);
    }
}
