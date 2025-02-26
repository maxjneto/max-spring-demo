package com.avanade.demo.application.controller;

import com.avanade.demo.application.dto.CreateCustomerDTO;
import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.domain.service.CreateCustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreateCustomerController {

    private static final Logger logger = LogManager.getLogger(CreateCustomerController.class);

    @Autowired
    private CreateCustomerService createCustomerService;

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@RequestBody @Validated CreateCustomerDTO customer) {
        CustomerDTO createdCustomer = createCustomerService.createCustomer(customer);
        logger.info("Created customer with name: " + createdCustomer.name());
        return ResponseEntity.ok(createdCustomer);
    }
}