package com.codeblooded.travelbookingsystem.travelpackages.hotels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Hotel>> getAllHotels() {
        Iterable<Hotel> hotels = hotelRepository.findAll();
        return ResponseEntity.ok(hotels);
    }
}
