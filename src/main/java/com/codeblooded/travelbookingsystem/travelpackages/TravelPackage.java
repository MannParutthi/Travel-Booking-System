package com.codeblooded.travelbookingsystem.travelpackages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor // Lombok annotation to generate constructor with all the attributes
@Data // Lombok annotation to generate getters and setters
public class TravelPackage {
    private static final AtomicInteger count = new AtomicInteger(20000);
    private final int id = count.incrementAndGet();
    private String destinationCity;
    private String destinationCountry;
    private int noOfDays;
    private int noOfNights;
    private String hotelName;
    private int price;
    private List<String> activitiesIncluded; // eg: breakfast, city tour, transportation card, discount on museums, etc

}