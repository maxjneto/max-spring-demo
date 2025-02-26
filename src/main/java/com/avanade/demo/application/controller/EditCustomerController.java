package com.avanade.demo.application.controller;

import com.avanade.demo.application.dto.*;
import com.avanade.demo.domain.model.CustomerDocument;
import com.avanade.demo.domain.service.CreateCustomerService;
import com.avanade.demo.domain.service.EditCustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditCustomerController {

    private static final Logger logger = LogManager.getLogger(EditCustomerController.class);


    @Autowired
    private EditCustomerService editCustomerService;

    @PostMapping("/customer/document")
    public ResponseEntity<?> addDocument(@RequestBody @Validated AddContactOrDocumentDTO document) {
        CustomerDocumentDTO addedCustomerDocument = editCustomerService.addDocument(document);
        logger.info("Added document to customer with id: " + document.customerId());
        return ResponseEntity.ok(addedCustomerDocument);
    }

    @PostMapping("/customer/contact")
    public ResponseEntity<?> addContact(@RequestBody @Validated AddContactOrDocumentDTO contact) {
        CustomerContactDTO addedCustomerContact = editCustomerService.addContact(contact);
        logger.info("Added contact to customer with id: " + contact.customerId());
        return ResponseEntity.ok(addedCustomerContact);
    }

}
