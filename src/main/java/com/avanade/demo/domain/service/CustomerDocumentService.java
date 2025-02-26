package com.avanade.demo.domain.service;

import com.avanade.demo.application.dto.CustomerDocumentDTO;
import com.avanade.demo.application.port.input.CustomerDocumentUseCase;
import com.avanade.demo.application.port.output.CustomerOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDocumentService implements CustomerDocumentUseCase {

    @Autowired
    private CustomerOutput customerOutput;

    @Override
    public List<CustomerDocumentDTO> listCustomerDocumentsById(Long id) {
        return customerOutput.listCustomerDocumentsById(id);
    }
}
