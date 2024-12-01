package com.tsc.brewery.web.service;

import com.tsc.brewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder().customerId(UUID.randomUUID())
                .customerName("Dinesh")
                .build();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        return CustomerDto.builder().customerId(UUID.randomUUID())
                .customerName(customerDto.getCustomerName())
                .gender(customerDto.getGender())
                .age(customerDto.getAge())
                .build();
    }

    @Override
    public CustomerDto updateCustomer(UUID customerId, CustomerDto customerDto) {
        return CustomerDto.builder()
                .customerId(customerId)
                .customerName(customerDto.getCustomerName())
                .gender(customerDto.getGender())
                .build();
    }

    @Override
    public CustomerDto deleteCustomer(UUID customerId) {
        return CustomerDto.builder()
                .customerName(null)
                .gender(null)
                .build();
    }
}
