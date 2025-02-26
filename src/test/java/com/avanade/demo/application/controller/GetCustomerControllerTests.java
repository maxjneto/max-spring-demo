package com.avanade.demo.application.controller;

import com.avanade.demo.application.dto.CustomerDTO;
import com.avanade.demo.domain.service.GetCustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetCustomerControllerTests {

    @Mock
    private GetCustomerService getCustomerService;

    @InjectMocks
    private GetCustomerController getCustomerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCustomerById_returnsCustomer_whenValidId() {
        long customerId = 1L;
        CustomerDTO customerDTO = new CustomerDTO(customerId, "John Doe", "Classic", null, null);

        when(getCustomerService.getCustomerById(customerId)).thenReturn(customerDTO);

        CustomerDTO result = getCustomerController.getCustomerById(customerId);

        assertNotNull(result);
        assertEquals(customerId, result.id());
        assertEquals("John Doe", result.name());
    }

    @Test
    void getCustomerById_returnsNull_whenInvalidId() {
        long invalidCustomerId = -1L;

        when(getCustomerService.getCustomerById(invalidCustomerId)).thenReturn(null);

        CustomerDTO result = getCustomerController.getCustomerById(invalidCustomerId);

        assertNull(result);
    }

    @Test
    void getCustomerByName_returnsCustomer_whenValidName() {
        String customerName = "John Doe";
        CustomerDTO customerDTO = new CustomerDTO(1L, customerName, "Classic", null, null);

        when(getCustomerService.getCustomerByName(customerName)).thenReturn(customerDTO);

        CustomerDTO result = getCustomerController.getCustomerByName(customerName);

        assertNotNull(result);
        assertEquals(customerName, result.name());
    }

    @Test
    void getCustomerByName_returnsNull_whenInvalidName() {
        String invalidCustomerName = "Nome invalido";

        when(getCustomerService.getCustomerByName(invalidCustomerName)).thenReturn(null);

        CustomerDTO result = getCustomerController.getCustomerByName(invalidCustomerName);

        assertNull(result);
    }
}