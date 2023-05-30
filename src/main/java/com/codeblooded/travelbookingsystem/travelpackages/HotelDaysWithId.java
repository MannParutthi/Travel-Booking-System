package com.codeblooded.travelbookingsystem.travelpackages;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor // Lombok annotation to generate constructor with all the attributes
@Data // Lombok annotation to generate getters and setters
@Embeddable
public class HotelDaysWithId {
    private int hotelId;
    private int noOfDays;
}
