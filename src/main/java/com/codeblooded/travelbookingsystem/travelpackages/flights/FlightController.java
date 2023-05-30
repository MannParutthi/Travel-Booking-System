package com.codeblooded.travelbookingsystem.travelpackages.flights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Flight>> getAllFlights() {
        Iterable<Flight> flights = flightRepository.findAll();
        return ResponseEntity.ok(flights);
    }
}
