package com.avanade.demo.domain.service;

import com.avanade.demo.application.dto.CreateCustomerDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.port.input.CreateCustomerUseCase;
import com.avanade.demo.application.port.output.CustomerOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerService implements CreateCustomerUseCase {

    @Autowired
    private CustomerOutput customerOutput;

    @Override
    public CustomerDTO createCustomer(CreateCustomerDTO customer) {
        return customerOutput.createCustomer(customer);
    }

}