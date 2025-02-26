package com.avanade.demo.domain.service;

import com.avanade.demo.application.dto.CustomerContactDTO;
import com.avanade.demo.application.port.input.CustomerContactUseCase;
import com.avanade.demo.application.port.output.CustomerOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerContactService implements CustomerContactUseCase {

    @Autowired
    private CustomerOutput customerOutput;

    @Override
    public List<CustomerContactDTO> listCustomerContactsById(Long id) {
        return customerOutput.listCustomerContactsById(id);
    }
}