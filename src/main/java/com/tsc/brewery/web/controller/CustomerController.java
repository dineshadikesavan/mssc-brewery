package com.tsc.brewery.web.controller;

import com.tsc.brewery.web.model.CustomerDto;
import com.tsc.brewery.web.service.CustomerService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@NotNull @PathVariable("customerId") UUID customerId) {
        return new ResponseEntity<CustomerDto>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomerDto> createCustomer(@NotNull @Valid @RequestBody CustomerDto customerDto) {
        log.debug("in createCustomer");
        val savedCustomerDto = this.customerService.saveNewCustomer(customerDto);
        return new ResponseEntity<CustomerDto>(savedCustomerDto, HttpStatus.OK);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@NotNull @PathVariable("customerId") UUID customerId, CustomerDto customerDto) {
        val updatedCustomerDto = this.customerService.updateCustomer(customerId, customerDto);
        val headers = new HttpHeaders();
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
