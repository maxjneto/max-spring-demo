package com.avanade.demo.application.controller;

import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.domain.service.GetCustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetCustomerController {

    private static final Logger logger = LogManager.getLogger(GetCustomerController.class);

    @Autowired
    private GetCustomerService getCustomerService;

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomerById(@PathVariable long id) {
        CustomerDTO customer = getCustomerService.getCustomerById(id);
        logger.info("Found customer with id: " + id);
        return customer;
    }

    @GetMapping("/customer/name/{customerName}")
    public CustomerDTO getCustomerByName(@PathVariable String customerName) {
        CustomerDTO customer = getCustomerService.getCustomerByName(customerName);
        logger.info("Found customer with name: " + customerName);
        return customer;
    }
}