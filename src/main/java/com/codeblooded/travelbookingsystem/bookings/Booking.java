package com.codeblooded.travelbookingsystem.bookings;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor // Lombok annotation to generate constructor with all the attributes
@Data // Lombok annotation to generate getters and setters
public class Booking {
    public enum BookingStatus { CONFIRMED, CANCELLED }

    private static final AtomicInteger count = new AtomicInteger(30000);
    private final int id = count.incrementAndGet();

    private int customerId;
    private int travelPackageId;
    private String departureDate;
    private BookingStatus bookingStatus = BookingStatus.CONFIRMED;
}

