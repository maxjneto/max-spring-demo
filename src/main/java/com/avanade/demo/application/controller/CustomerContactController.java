package com.avanade.demo.application.controller;

import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.domain.service.CustomerContactService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerContactController {

    private static final Logger logger = LogManager.getLogger(CustomerContactController.class);


    @Autowired
    private CustomerContactService customerContactService;

    @GetMapping("/customer/contacts/{id}")
    public List<CustomerContactDTO> getCustomerContactsById(@PathVariable Long id) {
        List<CustomerContactDTO> customerContacts = customerContactService.listCustomerContactsById(id);
        logger.info("Recovered the contacts of customer with id: " + id);
        return customerContacts;
    }

}
