package com.codeblooded.travelbookingsystem.bookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/bookings")
public class BookingsController {

    @Autowired
    private BookingService bookingsService;

    @PostMapping("/create")
    public ResponseEntity<String> createBooking(@RequestBody Booking booking) {
        String response = bookingsService.createBooking(booking);
        if(response == BookingService.BOOKING_ALREADY_EXISTS) {
            return new ResponseEntity<String>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<String>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{bookingId}")
    public ResponseEntity<String> updateBooking(@PathVariable("bookingId") String bookingId, @RequestBody Booking booking) {
        String response = bookingsService.updateBooking(Integer.parseInt(bookingId), booking);
        if(response == BookingService.BOOKING_NOT_FOUND) {
            return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Booking>> getAllBookings() {
        return new ResponseEntity<Iterable<Booking>>(bookingsService.getAllBookings(), HttpStatus.OK);
    }
}
