package com.avanade.demo.application.port.output;

import com.avanade.demo.application.dto.*;

import java.util.List;

public interface CustomerOutput {

    CustomerDTO getCustomerById(Long id);

    CustomerDTO getCustomerByName(String name);

    CustomerDTO createCustomer(CreateCustomerDTO customer);

    List<CustomerContactDTO> listCustomerContactsById(Long id);

    List<CustomerDocumentDTO> listCustomerDocumentsById(Long id);

    CustomerDocumentDTO addDocument(AddContactOrDocumentDTO document);

    CustomerContactDTO addContact(AddContactOrDocumentDTO contact);

}
