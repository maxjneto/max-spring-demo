package com.avanade.demo.application.port.input;

import com.avanade.demo.application.dto.CustomerContactDTO;

import java.util.List;

public interface CustomerContactUseCase {

    List<CustomerContactDTO> listCustomerContactsById(Long id);
}
