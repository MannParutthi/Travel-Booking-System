package com.codeblooded.travelbookingsystem.customer;

import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        String response = customerService.createCustomer(customer);
        if(response == CustomerService.CX_ALREADY_EXISTS) {
            return new ResponseEntity<String>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<String>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Customer>> getAllCustomers() {
        return new ResponseEntity<Iterable<Customer>>(customerService.getAllCustomers(), HttpStatus.OK);
    }
}
