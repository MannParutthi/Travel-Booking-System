package com.codeblooded.travelbookingsystem.bookings;

import com.codeblooded.travelbookingsystem.payment.Payment;
import com.codeblooded.travelbookingsystem.travelpackages.TravelPackageRepository;
import com.codeblooded.travelbookingsystem.travelpackages.TravelPackageService;
import com.codeblooded.travelbookingsystem.user.User;
import com.codeblooded.travelbookingsystem.user.UserRepository;
import com.codeblooded.travelbookingsystem.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/bookings")
public class BookingsController {
    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/create")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody Booking booking) {

        if(!travelPackageRepository.existsById(booking.getTravelPackageId())) {
            return new ResponseEntity<>(new BookingResponse(TravelPackageService.TRAVEL_PACKAGE_NOT_FOUND, booking.getId()), HttpStatus.CONFLICT);
        }
        if(!userRepository.existsById(booking.getCustomerId())) {
            return new ResponseEntity<>(new BookingResponse(UserService.USER_NOT_FOUND, booking.getId()), HttpStatus.CONFLICT);
        }
        if (bookingsRepository.existsById(booking.getId())) {
            BookingResponse response = new BookingResponse(BookingService.BOOKING_ALREADY_EXISTS, booking.getId());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        // TODO: Payment
        Payment payment = new Payment();

        // TODO: Email

        bookingsRepository.save(booking);

        return new ResponseEntity<>(new BookingResponse(BookingService.BOOKING_CREATED_PAYMENT_PENDING, booking.getId()), HttpStatus.CREATED);
    }

    @PutMapping("/update/{bookingId}")
    public ResponseEntity<String> updateBooking(@PathVariable("bookingId") String bookingId, @RequestBody Booking booking) {

        if (!bookingsRepository.existsById(Long.valueOf(bookingId))) {
            return new ResponseEntity<String>(BookingService.BOOKING_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        booking.setId(Long.parseLong(bookingId));
        bookingsRepository.save(booking);

        // TODO: Email

        return new ResponseEntity<String>(BookingService.BOOKING_UPDATED_SUCCESSFULLY, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Booking>> getAllBookings() {
        Iterable<Booking> bookings = bookingsRepository.findAll();
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/confirm/{bookingId}")
    public ResponseEntity<String> confirmBooking(@PathVariable("bookingId") String bookingId) {
        Booking booking = bookingsRepository.findById(Long.valueOf(bookingId)).orElse(null);
        if(booking == null) {
            return new ResponseEntity<>(BookingService.BOOKING_NOT_FOUND + " for ID: " + bookingId, HttpStatus.NOT_FOUND);
        }

        booking.setBookingStatus(Booking.BookingStatus.CONFIRMED);
        return new ResponseEntity<>("Booking confirmed for ID: " + bookingId, HttpStatus.OK);
    }
}
