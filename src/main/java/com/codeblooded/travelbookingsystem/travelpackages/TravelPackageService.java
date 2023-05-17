package com.codeblooded.travelbookingsystem.travelpackages;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravelPackageService {
    private List<TravelPackage> packages = new ArrayList<>();
    public static final String PKG_ALREADY_EXISTS = "Package Already Exists";
    public static final String PKG_CREATED_SUCCESSFULLY = "Package Created Successfully !";
    public static final String TRAVEL_PACKAGE_NOT_FOUND = "Travel Package Not Found !";

    public String createTravelPackage(TravelPackage travelPackage) {
        if(packages.contains(travelPackage)) {
            return PKG_ALREADY_EXISTS;
        }
        packages.add(new TravelPackage(
                travelPackage.getDestinationCity(),
                travelPackage.getDestinationCountry(),
                travelPackage.getNoOfDays(),
                travelPackage.getNoOfNights(),
                travelPackage.getHotelName(),
                travelPackage.getPrice(),
                travelPackage.getActivitiesIncluded()));
        return PKG_CREATED_SUCCESSFULLY;
    }

    public List<TravelPackage> getAllTravelPackages() {
        return packages;
    }
}
