package com.tsc.brewery.web.service;

import com.tsc.brewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {

    public CustomerDto getCustomerById(UUID customerId);

    public CustomerDto saveNewCustomer(CustomerDto customerDto);
}
