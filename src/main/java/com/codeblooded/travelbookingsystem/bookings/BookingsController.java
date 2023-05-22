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
    public ResponseEntity<BookingResponse> createBooking(@RequestBody Booking booking) {
        BookingResponse response = bookingsService.createBooking(booking);
        if (BookingService.BOOKING_ALREADY_EXISTS.equals(response.getMessage())) {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @PutMapping("/update/{bookingId}")
    public ResponseEntity<String> updateBooking(@PathVariable("bookingId") String bookingId, @RequestBody Booking booking) {
        String response = bookingsService.updateBooking(Integer.parseInt(bookingId), booking);
        if(response == BookingService.BOOKING_NOT_FOUND) {
            return new ResponseEntity<String>(response, HttpStatus.OK);
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Booking>> getAllBookings() {
        return new ResponseEntity<Iterable<Booking>>(bookingsService.getAllBookings(), HttpStatus.OK);
    }

    @PutMapping("/confirm/{bookingId}")
    public ResponseEntity<String> confirmBooking(@PathVariable("bookingId") String bookingId) {
        String response = bookingsService.confirmBooking(Integer.parseInt(bookingId));
        if (BookingService.BOOKING_NOT_FOUND.equals(response)) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
