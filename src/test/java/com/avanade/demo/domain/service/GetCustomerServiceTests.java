package com.avanade.demo.domain.service;

import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.application.port.output.CustomerOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetCustomerServiceTests {

    @Mock
    private CustomerOutput customerOutput;

    @InjectMocks
    private GetCustomerService getCustomerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCustomerById_returnsCustomer_whenValidId() {
        Long customerId = 1L;
        CustomerDTO customerDTO = new CustomerDTO(customerId, "John Doe", "Classic", null, null);

        when(customerOutput.getCustomerById(customerId)).thenReturn(customerDTO);

        CustomerDTO result = getCustomerService.getCustomerById(customerId);

        assertNotNull(result);
        assertEquals(customerId, result.id());
        assertEquals("John Doe", result.name());
    }

    @Test
    void getCustomerById_returnsNull_whenInvalidId() {
        Long invalidCustomerId = -1L;

        when(customerOutput.getCustomerById(invalidCustomerId)).thenReturn(null);

        CustomerDTO result = getCustomerService.getCustomerById(invalidCustomerId);

        assertNull(result);
    }

    @Test
    void getCustomerByName_returnsCustomer_whenValidName() {
        String customerName = "John Doe";
        CustomerDTO customerDTO = new CustomerDTO(1L, customerName, "Classic", null, null);

        when(customerOutput.getCustomerByName(customerName)).thenReturn(customerDTO);

        CustomerDTO result = getCustomerService.getCustomerByName(customerName);

        assertNotNull(result);
        assertEquals(customerName, result.name());
    }

    @Test
    void getCustomerByName_returnsNull_whenInvalidName() {
        String invalidCustomerName = "Invalid Name";

        when(customerOutput.getCustomerByName(invalidCustomerName)).thenReturn(null);

        CustomerDTO result = getCustomerService.getCustomerByName(invalidCustomerName);

        assertNull(result);
    }
}
