package com.codeblooded.travelbookingsystem.bookings;

import com.codeblooded.travelbookingsystem.service.EmailService;
import com.codeblooded.travelbookingsystem.user.User;
import com.codeblooded.travelbookingsystem.user.UserService;
import com.codeblooded.travelbookingsystem.payment.Payment;
import com.codeblooded.travelbookingsystem.travelpackages.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private UserService userService;
    @Autowired
    private TravelPackageService travelPackageService;

    private List<Booking> bookings = new ArrayList<>();
    public static final String BOOKING_ALREADY_EXISTS = "Booking Already Exists";
    public static final String BOOKING_CREATED_SUCCESSFULLY = "Booking Created Successfully !";
    public static final String BOOKING_NOT_FOUND = "Booking Not Found !";
    public static final String BOOKING_UPDATED_SUCCESSFULLY = "Booking Updated Successfully !";
    public static final String BOOKING_CREATED_PAYMENT_PENDING = "Booking Created, Payment Pending";

    // Class instance for emailService
    private EmailService emailService;

    @Autowired
    public BookingService(EmailService emailService) {
        this.emailService = emailService;
    }

    public BookingResponse createBooking(Booking booking) {
        // travel id & customer id exists ?
        boolean travelPackageExists = travelPackageService.getAllTravelPackages().stream().anyMatch(travelPackage -> travelPackage.getId() == booking.getTravelPackageId());
        boolean customerExists = userService.getAllUsers().stream().anyMatch(customer -> customer.getId() == booking.getCustomerId());

        BookingResponse bookingResponse = new BookingResponse();
        if(!travelPackageExists) {
            bookingResponse.setMessage(TravelPackageService.TRAVEL_PACKAGE_NOT_FOUND);
            return bookingResponse;
        }
        if(!customerExists) {
            bookingResponse.setMessage(userService.USER_NOT_FOUND);
            return bookingResponse;
        }

        if(bookings.contains(booking)) {
            bookingResponse.setMessage(BOOKING_ALREADY_EXISTS);
            return bookingResponse;
        }
        Payment payment = new Payment();
        booking.setBookingStatus(Booking.BookingStatus.PENDING);
        Booking bookingToBeSaved = new Booking(booking.getCustomerId(), booking.getTravelPackageId(), booking.getDepartureDate(), payment.getId(), booking.getBookingStatus());
        bookings.add(bookingToBeSaved);
        bookingResponse.setMessage(BookingService.BOOKING_CREATED_PAYMENT_PENDING);
        bookingResponse.setBookingId(bookingToBeSaved.getId());

        // Send notification to user
        Optional<User> userProfile = userService.getAllUsers().stream().filter(customer -> customer.getId() == booking.getCustomerId()).findFirst();
        if(userProfile.isPresent()) {
            String emailAddress = userProfile.get().getEmail();
            emailService.sendBookingConfirmationEmail(emailAddress, booking.getCustomerId(), bookingToBeSaved.getId(), payment.getId(), booking.getTravelPackageId(), booking.getDepartureDate());
        }

        return bookingResponse;
    }

    public String updateBooking(int bookingId, Booking booking) {
        for(Booking b : bookings) {
            if(b.getId() == bookingId) {
                b.setCustomerId(booking.getCustomerId());
                b.setTravelPackageId(booking.getTravelPackageId());
                b.setDepartureDate(booking.getDepartureDate());
                b.setBookingStatus(booking.getBookingStatus());

                // Send notification to user
                Optional<User> userProfile = userService.getAllUsers().stream().filter(customer -> customer.getId() == booking.getCustomerId()).findFirst();
                if(userProfile.isPresent()) {
                    String emailAddress = userProfile.get().getEmail();
                    emailService.sendBookingUpdateEmail(emailAddress, booking.getCustomerId(), bookingId, booking.getTravelPackageId(), booking.getDepartureDate(), booking.getBookingStatus().toString());
                }

                return BOOKING_UPDATED_SUCCESSFULLY;
            }
        }

        return BOOKING_NOT_FOUND;
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    public String confirmBooking(int bookingId) {
        List<Booking> bookings = getAllBookings();

        Booking booking = null;
        for (Booking bookingValue : bookings) {
            if(bookingValue.getId()==bookingId)
                booking=bookingValue;
        }

        if (booking != null) {
            booking.setBookingStatus(Booking.BookingStatus.CONFIRMED);
            return "Booking confirmed for ID: " + bookingId;
        } else {
            return "Booking not found for ID: " + bookingId;
        }
    }
}
