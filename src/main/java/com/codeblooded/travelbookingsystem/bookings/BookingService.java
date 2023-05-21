package com.codeblooded.travelbookingsystem.bookings;

import com.codeblooded.travelbookingsystem.user.UserService;
import com.codeblooded.travelbookingsystem.travelpackages.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public String createBooking(Booking booking) {
        // travel id & user id exists ?
        boolean travelPackageExists = travelPackageService.getAllTravelPackages().stream().anyMatch(travelPackage -> travelPackage.getId() == booking.getTravelPackageId());
        boolean customerExists = userService.getAllUsers().stream().anyMatch(customer -> customer.getId() == booking.getUserId());

        if(!travelPackageExists) {
            return TravelPackageService.TRAVEL_PACKAGE_NOT_FOUND;
        }
        if(!customerExists) {
            return UserService.USER_NOT_FOUND;
        }

        if(bookings.contains(booking)) {
            return BOOKING_ALREADY_EXISTS;
        }
        bookings.add(new Booking(booking.getUserId(), booking.getTravelPackageId(), booking.getDepartureDate(), booking.getBookingStatus()));
        return BOOKING_CREATED_SUCCESSFULLY;
    }

    public String updateBooking(int bookingId, Booking booking) {
        for(Booking b : bookings) {
            if(b.getId() == bookingId) {
                b.setUserId(booking.getUserId());
                b.setTravelPackageId(booking.getTravelPackageId());
                b.setDepartureDate(booking.getDepartureDate());
                b.setBookingStatus(booking.getBookingStatus());
                return BOOKING_UPDATED_SUCCESSFULLY;
            }
        }
        return BOOKING_NOT_FOUND;
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }
}
