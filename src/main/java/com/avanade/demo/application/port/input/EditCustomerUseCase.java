package com.avanade.demo.application.port.input;

import com.avanade.demo.application.dto.*;

public interface EditCustomerUseCase {

    CustomerDocumentDTO addDocument(AddContactOrDocumentDTO document);

    CustomerContactDTO addContact(AddContactOrDocumentDTO contact);

}
