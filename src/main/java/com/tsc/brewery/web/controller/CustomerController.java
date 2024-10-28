package com.tsc.brewery.web.controller;

import com.tsc.brewery.web.model.CustomerDto;
import com.tsc.brewery.web.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") UUID customerId) {
        return new ResponseEntity<CustomerDto>(customerService.getCustomerById(customerId),  HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomerDto> createCustomer(CustomerDto customerDto) {
        CustomerDto savedCustomerDto = this.customerService.saveNewCustomer(customerDto);
         return new ResponseEntity<CustomerDto>(savedCustomerDto, HttpStatus.OK);
    }
}
