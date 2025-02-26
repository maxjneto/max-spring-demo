package com.avanade.demo.application.port.input;

import com.avanade.demo.application.dto.CreateCustomerDTO;
import com.avanade.demo.application.dto.CustomerDTO;

public interface CreateCustomerUseCase {

    CustomerDTO createCustomer(CreateCustomerDTO customer);

}
