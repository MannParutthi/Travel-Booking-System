package com.codeblooded.travelbookingsystem.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    private static final AtomicInteger count = new AtomicInteger(1000);
    private final int id = count.incrementAndGet();

    private String paymentMethod;
    private double amount;
}
