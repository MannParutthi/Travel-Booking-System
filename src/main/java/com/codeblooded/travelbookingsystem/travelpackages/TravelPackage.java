package com.codeblooded.travelbookingsystem.travelpackages;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor // Lombok annotation to generate constructor with all the attributes
@Data // Lombok annotation to generate getters and setters
@Entity
@Table(name="TRAVEL_PACKAGE")
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "destinationCity", nullable = false)
    private String destinationCity;

    @Column(name = "destinationCountry", nullable = false)
    private String destinationCountry;

    @Column(name = "noOfDays", nullable = false)
    private int noOfDays;

    @Column(name = "price", nullable = false)
    private int price;

    public enum PackageType { PREMADE, CUSTOM }
    @Enumerated(EnumType.STRING)
    @Column(name = "packageType", nullable = false)
    private PackageType packageType;

    @Column(name = "flightId", nullable = false)
    private int flightId;

    @ElementCollection
    @CollectionTable(name = "ACTIVITIES", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "activityIdsIncluded", nullable = false)
    private List<Integer> activityIdsIncluded;

    @ElementCollection
    @CollectionTable(name = "hotel_days_with_id", joinColumns = @JoinColumn(name = "package_id"))
    @Column(name = "hotelDaysWithId", nullable = false)
    private List<HotelDaysWithId> hotelDaysWithId;
}