package com.tsc.brewery.web.controller;

import com.tsc.brewery.web.model.CustomerDto;
import com.tsc.brewery.web.service.CustomerService;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto savedCustomerDto = this.customerService.saveNewCustomer(customerDto);
         return new ResponseEntity<CustomerDto>(savedCustomerDto, HttpStatus.OK);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("customerId") UUID customerId, CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = this.customerService.updateCustomer(customerId, customerDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + updatedCustomerDto.getCustomerId().toString());
        return new ResponseEntity<CustomerDto>(headers, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable("customerId") UUID customerId) {
        CustomerDto deleteCustomerDto = this.customerService.deleteCustomer(customerId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + customerId.toString());

        return new ResponseEntity<>(deleteCustomerDto, headers, HttpStatus.NO_CONTENT);
    }
}
