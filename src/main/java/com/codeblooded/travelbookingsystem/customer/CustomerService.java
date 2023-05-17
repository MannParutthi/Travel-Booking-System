package com.codeblooded.travelbookingsystem.customer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private List<Customer> customers = new ArrayList<>();
    public static final String CX_ALREADY_EXISTS = " Customer Already Exists";
    public static final String CX_CREATED_SUCCESSFULLY = "Customer Created Successfully !";
    public static final String CX_NOT_FOUND = "Customer Not Found !";

    public String createCustomer(Customer customer) {
        if(customers.contains(customer)) {
            return CX_ALREADY_EXISTS;
        }
        customers.add(new Customer(customer.getFirstName(), customer.getLastName(), customer.getDateOfBirth(), customer.getEmail()));
        return CX_CREATED_SUCCESSFULLY;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }
}
