package com.codeblooded.travelbookingsystem.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    private long id;

    private String paymentMethod;
    private double amount;
}
