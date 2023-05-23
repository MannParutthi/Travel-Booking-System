package com.codeblooded.travelbookingsystem.user;

import com.codeblooded.travelbookingsystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/customers")
public class UserController {

    // Class instance for emailService
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String response = userService.createUser(user);
        if(response == UserService.USER_ALREADY_EXISTS) {
            return new ResponseEntity<String>(response, HttpStatus.OK);
        }

        // Send welcome email to user
        String email_address = user.getEmail();
        emailService.sendAccountRegistrationEmail(email_address);

        return new ResponseEntity<String>(response, HttpStatus.CREATED);
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
