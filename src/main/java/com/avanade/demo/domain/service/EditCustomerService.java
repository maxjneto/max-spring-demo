package com.avanade.demo.domain.service;

import com.avanade.demo.application.dto.AddContactOrDocumentDTO;
import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.application.port.input.EditCustomerUseCase;
import com.avanade.demo.application.port.output.CustomerOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditCustomerService implements EditCustomerUseCase {

    @Autowired
    CustomerOutput customerOutput;

    @Override
    public CustomerDocumentDTO addDocument(AddContactOrDocumentDTO document) {
        return customerOutput.addDocument(document);
    }

    @Override
    public CustomerContactDTO addContact(AddContactOrDocumentDTO contact) {
        return customerOutput.addContact(contact);
    }
}
