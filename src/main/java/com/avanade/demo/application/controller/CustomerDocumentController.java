package com.avanade.demo.application.controller;

import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.domain.service.CustomerDocumentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerDocumentController {

    private static final Logger logger = LogManager.getLogger(CustomerDocumentController.class);

    @Autowired
    private CustomerDocumentService customerDocumentService;

    @GetMapping("/customer/documents/{id}")
    public List<CustomerDocumentDTO> getCustomerDocumentsById(@PathVariable Long id) {
        List<CustomerDocumentDTO> customerDocuments = customerDocumentService.listCustomerDocumentsById(id);
        logger.info("Recovered the documents of customer with id: " + id);
        return customerDocuments;
    }

}