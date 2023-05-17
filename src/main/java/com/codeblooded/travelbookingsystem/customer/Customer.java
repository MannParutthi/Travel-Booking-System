package com.codeblooded.travelbookingsystem.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor // Lombok's annotation to generate constructor with all the attributes
@Data // Lombok annotation to generate getters and setters
public class Customer {
    private static final AtomicInteger count = new AtomicInteger(10000);
    private final int id = count.incrementAndGet();
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String email;
}
