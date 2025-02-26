package com.avanade.demo.application.port.input;

import com.avanade.demo.application.dto.CustomerDocumentDTO;

import java.util.List;

public interface CustomerDocumentUseCase {

    List<CustomerDocumentDTO> listCustomerDocumentsById(Long id);

}
